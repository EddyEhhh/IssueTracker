/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tohyu
 */
@Named("viewUserBean")
@RequestScoped
public class ViewUserBean implements Serializable {

    @Inject
    private StaffManager staffManager;

    private List<StaffDTO> staff;

    private List<StaffDTO> filteredStaff;

    @PostConstruct
    public void init() {
        staff = staffManager.getAllStaff();
        Collections.reverse(staff);
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (StringUtils.isBlank(filterText)) {
            return true;
        }

        StaffDTO staff = (StaffDTO) value;
        return staff.getFirstName().toLowerCase().contains(filterText)
                || staff.getLastName().toLowerCase().contains(filterText)
                || staff.getEmail().toLowerCase().contains(filterText)
                || staff.getRole().toLowerCase().contains(filterText);
    }

    public List<StaffDTO> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffDTO> staff) {
        this.staff = staff;
    }

    public List<StaffDTO> getFilteredStaff() {
        return filteredStaff;
    }

    public void setFilteredStaff(List<StaffDTO> filteredStaff) {
        this.filteredStaff = filteredStaff;
    }
}
