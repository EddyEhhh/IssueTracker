/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.authenticate;

import edu.mac.issuetracker.staff.data.StaffFacade;
import edu.mac.issuetracker.staff.entity.Staff;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.PasswordHash;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Jaibun Thana
 */

@Stateless
@Named("authenticate")
public class Authenticate {
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private StaffFacade facade;
    
    @Inject
    private Pbkdf2PasswordHash passwordHasher;
    
    public boolean isAuthenticated(){
        return (securityContext.getCallerPrincipal() != null);
    }
    
    public Staff getCurrentSession(){
        String email = securityContext.getCallerPrincipal().getName();
        return facade.getStaff(email);
    }
    
    public boolean isCredentialValid(String email, String password){
        String accountPassword = facade.getStaff(email).getPassword();
        return passwordHasher.verify(password.toCharArray(), accountPassword);
    }
    
    public boolean isFirstLogin(String email){
        return facade.getStaff(email).isFirstLogin();
    }
    
    public void firstLogin(String email){
        if(isFirstLogin(email)){
            Staff staff = facade.getStaff(email);
            staff.setFirstLogin(false);
            facade.edit(staff);
        }
    }
    
    public String passwordHasher(String password){
        return passwordHasher.generate(password.toCharArray());
    }
    
}
