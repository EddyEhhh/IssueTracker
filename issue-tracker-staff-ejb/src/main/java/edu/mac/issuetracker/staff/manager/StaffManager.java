/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.manager;

import edu.mac.issuetracker.staff.authenticate.Authenticate;
import edu.mac.issuetracker.staff.authenticate.Authenticate2FA;
import edu.mac.issuetracker.staff.data.StaffFacade;
import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.dto.StaffUpdateDTO;
import edu.mac.issuetracker.staff.entity.Staff;
import edu.mac.issuetracker.staff.interceptor.RegisterInterceptor;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.security.enterprise.SecurityContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jaibun Thana
 */
//Exploses entity managers, converts Entity to DTO vice versa, 
@Stateless
@DeclareRoles({"admin", "user"})
public class StaffManager {

    @Inject
    private StaffFacade facade;

    @Inject
    private Authenticate2FA auth2FA;

    @Inject
    private Authenticate auth;

    @Inject
    private SecurityContext securityContext;

    public Staff find(long staffId) {
        return facade.find(staffId);
    }

    @RolesAllowed("admin")
    public List<StaffDTO> getAllStaff() {
        List<Staff> staff = facade.findAll();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for(int i = 0; i < staff.size(); i++){
            StaffDTO tempReportValue = staff.get(i).toDTO();
            staffDTOList.add(tempReportValue);
        }
        return staffDTOList;
    }

    
    public boolean isAuthenticated() {
        return auth.isAuthenticated();
    }

    public boolean isFirstLogin(String email) {
        return auth.isFirstLogin(email);
    }

    public boolean checkInputTOTP(String totp, String email) {
        String secretKey = facade.getStaff(email).getSecretKey2FA();
        String currentTOTP = auth2FA.getTOTPCode(secretKey);
        if (totp.equals(currentTOTP)) {
            auth.firstLogin(email);
            return true;
        } else {
            return false;
        }
    }

    
    public boolean isCredentialValid(String email, String password) {
        return auth.isCredentialValid(email, password);
    }

    public byte[] getAuthQR(String email, String issuer) throws Exception {
        String secretKey = facade.getStaff(email).getSecretKey2FA();
        return auth2FA.getAuthQR(email, secretKey, issuer);
    }

    public StaffDTO getCurrentUser() {
        String email = securityContext.getCallerPrincipal().getName();
        return getStaff(email);
    }

    
    public StaffDTO getStaff(String email) {
        Staff staff = facade.getStaff(email);
        return staff.toDTO();
    }
    
    @RolesAllowed("admin")
    @Interceptors(RegisterInterceptor.class)
    public String register(StaffDTO staffRegisterDTO) throws IOException {
        Staff staff = staffRegisterDTO.toStaff(); //DTO to Object
        //Gen secretkey
        staff.setSecretKey2FA(auth2FA.generateSecretKey());
        //persist new staff
        facade.create(staff);
        return "SUCCESS";
    }
    
    @RolesAllowed({"user", "admin"})
    public String changePassword(StaffUpdateDTO staffUpdateDTO) throws IOException {
        // check if current password correct
        String email = securityContext.getCallerPrincipal().getName();
        Boolean credentialChecker = isCredentialValid(email, staffUpdateDTO.getCurrentPassword());
        if (credentialChecker) {
            // check if new password matches
            if (staffUpdateDTO.getNewPassword().equals(staffUpdateDTO.getConfirmNewPassword())) {
                Staff currentStaff = facade.getStaff(email);
                currentStaff.setPassword(auth.passwordHasher(staffUpdateDTO.getConfirmNewPassword()));
                facade.edit(currentStaff);
                return "SUCCESS";
            } else {
                return "ERROR2";
            }
        } else {
            return "ERROR1";
        }
    }

    @RolesAllowed("admin")
    public String updateUser(StaffUpdateDTO staffUpdateDTO) throws IOException {
        // current email validator
        boolean emailExist = facade.emailExist(staffUpdateDTO.getCurrentEmail());
        if (emailExist) {
            // in future, use java reflection api for flexibility
            // check if input empty > if not empty > staff.set()
            String email = securityContext.getCallerPrincipal().getName();
            Staff updatedStaff = facade.getStaff(email);
            if (StringUtils.isNotEmpty(staffUpdateDTO.getNewEmail())) {
                updatedStaff.setEmail(staffUpdateDTO.getNewEmail());
            }
            if (StringUtils.isNotEmpty(staffUpdateDTO.getFirstName())) {
                updatedStaff.setFirstName(staffUpdateDTO.getFirstName());
            }
            if (StringUtils.isNotEmpty(staffUpdateDTO.getLastName())) {
                updatedStaff.setLastName(staffUpdateDTO.getLastName());
            }
            if (StringUtils.isNotEmpty(staffUpdateDTO.getRole())) {
                updatedStaff.setRole(staffUpdateDTO.getRole());
            }
            if (StringUtils.isEmpty(staffUpdateDTO.getNewEmail()) && StringUtils.isEmpty(staffUpdateDTO.getFirstName()) && StringUtils.isEmpty(staffUpdateDTO.getLastName()) && StringUtils.isEmpty(staffUpdateDTO.getRole())) {
                return "FAILURE";
            }
            facade.edit(updatedStaff);
            return "SUCCESS";
        } 
        else {
            return "WRONG_CURRENT_EMAIL";
        }
    }
    
    @RolesAllowed("admin")
    public String deleteUser(StaffDTO staffDTO){
        boolean emailExist = facade.emailExist(staffDTO.getEmail());
        if (emailExist) {
            Staff deleteStaff = facade.getStaff(staffDTO.getEmail());
            facade.remove(deleteStaff);
            return "SUCCESS";
        }
        else{
            return "FAILURE";
        }
    }

//     public Staff getStaffTest(String email){
//        Staff staff = facade.getStaff(email);
//        return staff;
//    }
//    public void find
//    
//    public void login(){
//        
//    }
}
