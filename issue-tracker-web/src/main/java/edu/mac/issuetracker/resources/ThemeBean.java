/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.resources;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author tohyu
 */
@SessionScoped
@Named("themeBean")
public class ThemeBean implements Serializable {

    //default theme 
    private String selectedTheme = "saga";
    private String selectedLogo = "applogo_black.png";

    public void changeTheme(ValueChangeEvent e) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //refresh the current page
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        //change the value of the variable
        selectedTheme = e.getNewValue().toString();
        if (selectedTheme.equals("arya")) {
            selectedLogo = "applogo_white.png";
        } else if (selectedTheme.equals("saga")) {
            selectedLogo = "applogo_black.png";
        }
    }

    public String getSelectedLogo() {
        return selectedLogo;
    }

    public void setSelectedLogo(String selectedLogo) {
        this.selectedLogo = selectedLogo;
    }

    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

}
