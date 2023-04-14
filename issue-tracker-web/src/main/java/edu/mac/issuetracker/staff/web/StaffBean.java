/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffUpdateDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * @author Jaibun Thana
 */
@Named
@SessionScoped
public class StaffBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private StaffManager staffManager;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private StaffUpdateDTO staffUpdateDTO = new StaffUpdateDTO();

    @PostConstruct
    public void initialize() {
        email = staffManager.getCurrentUser().getEmail();
        firstName = staffManager.getCurrentUser().getFirstName();
        lastName = staffManager.getCurrentUser().getLastName();
        role = staffManager.getCurrentUser().getRole();
    }

    public void changePassword() throws IOException {
        switch (staffManager.changePassword(staffUpdateDTO)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Password has been changed"));
                break;
            case "ERROR1":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong current password"));
                break;
            case "ERROR2":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "New Password does not match"));
                break;
            default:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Oops something went wrong!"));
                break;
        }
    }

    //logout code
    public void logout() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext()
            .invalidateSession();
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest()).logout();       
        FacesContext.getCurrentInstance().getExternalContext()
            .redirect("/login.xhtml");
    }

    public boolean isAdmin() {
        return securityContext.isCallerInRole("admin");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public StaffUpdateDTO getStaffUpdateDTO() {
        return staffUpdateDTO;
    }

    public void setStaffUpdateDTO(StaffUpdateDTO staffUpdateDTO) {
        this.staffUpdateDTO = staffUpdateDTO;
    }
}
