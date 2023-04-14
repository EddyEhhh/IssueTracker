/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffUpdateDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Sam
 */
@Named
@RequestScoped
public class UpdateUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private StaffManager staffManager;

    @Inject
    private FacesContext facesContext;

    private StaffUpdateDTO staffUpdateDTO = new StaffUpdateDTO();

    public void updateUser() throws IOException {
        switch (staffManager.updateUser(staffUpdateDTO)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User updated successfully"));
                break;
            case "FAILURE":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No Update Inputs"));
                break;
            case "WRONG_CURRENT_EMAIL":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong current email"));
                break;
        }
    }

    public StaffUpdateDTO getStaffUpdateDTO() {
        return staffUpdateDTO;
    }

    public void setStaffUpdateDTO(StaffUpdateDTO staffUpdateDTO) {
        this.staffUpdateDTO = staffUpdateDTO;
    }
}
