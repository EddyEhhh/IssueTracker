/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.authenticate;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

/**
 *
 * @author Jaibun Thana
 */
@Named
@RequestScoped
public class Role {
    
    @Inject
    private SecurityContext securityContext;
    
    private boolean admin;
    
    public boolean isAdmin() {
        admin = securityContext.isCallerInRole("admin");
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
