/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffDTO;
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
public class DeleteUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private StaffManager staffManager;

    @Inject
    private FacesContext facesContext;

    private StaffDTO staffDTO = new StaffDTO();

    public void deleteUser() throws IOException {
        switch (staffManager.deleteUser(staffDTO)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User deleted successfully"));
                break;
            case "FAILURE":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid email"));
                break;
        }
    }

    public StaffDTO getStaffDTO() {
        return staffDTO;
    }

    public void setStaffDTO(StaffDTO staffDTO) {
        this.staffDTO = staffDTO;
    }
}
