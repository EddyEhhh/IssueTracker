/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.IOException;

/**
 *
 * @author Jaibun Thana
 */
@Named
@RequestScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

//    @EJB
//    private StaffDetailGenerator detailGen;
//    public void test(){
//        System.out.print(detailGen.passwordGenerator());
//    }
    /**
     * Creates a new instance of StaffManagedBean
     */
    @EJB
    private StaffManager staffManager;

    @Inject
    private FacesContext facesContext;

    private StaffDTO staffRegisterDTO = new StaffDTO();

    //Setters and Getters
    public void register() throws IOException {
        switch (staffManager.register(staffRegisterDTO)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User registered"));
                break;
            case "EMAIL_EXIST":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User already exist"));
                break;
            case "EMAIL_FAILED":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to create user: Emailer failed"));
                break;
            case "FAILURE":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to create user"));
                break;
        }
    }

    //getters and setters
    public StaffDTO getStaffRegisterDTO() {
        return staffRegisterDTO;
    }

    public void setStaffRegisterDTO(StaffDTO staffRegisterDTO) {
        this.staffRegisterDTO = staffRegisterDTO;
    }

}

//    public void test(){
//        System.out.println(staffRegisterDTO.getEmail());
//    }
//    public void test2(){
//        Staff staff = new Staff();
//        staff.setEmail("test");
//        //staff.setFirstName("test");
//        //staff.setLastName("test");
//        //staff.setProfilePicture(null);
//        //staff.setFirstLogin(true);
//        //staff.setDelete(false);
//        //staff.setRole("roletest");
//        staff.setPassword("testpass");
//        staffManager.test(staff);
//    }
//    public void test2(){
//        Staff staff = new Staff();
//        staff.setEmail("t");
//        staff.setLastName("testin");
//        staff.setRole("te");
//        staff.setPassword("test");
//        staffManager.test(staff);
//        //temptest = staffRegisterDTO.getfirstName();
//    }
