/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.resources;

import edu.mac.issuetracker.staff.web.StaffBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author tohyu
 */
@Named("idleMonitor")
@RequestScoped
public class IdleMonitor {
    
    @Inject
    private StaffBean staffBean;
    
    public void timeout() throws Exception {
        System.out.print("Your Session Has Ended");
        staffBean.logout();
    }
    
    public void onActive() {
        System.out.print("User back online");
    }
    
}
