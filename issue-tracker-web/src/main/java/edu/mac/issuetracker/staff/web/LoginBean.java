/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jaibun Thana
 */
@Named
@SessionScoped
public class LoginBean extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;

    private StaffDTO staffLoginDTO = new StaffDTO();

//    public void login(){
//        staffManager.register(getStaffLoginDTO());
//    }
    @Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private StaffManager staffManager;

    @Inject
    private AuthBean authBean;

    private boolean testRendered = false;

    //Init login
    public void login() throws Exception {
        boolean authEnabled = false; //Hardcoded.
        if (authEnabled) {
            authBean.prepareAuth2FALogin(getStaffLoginDTO()); //prepare OTP
        } else {
            authenticate();
            //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Oops something went wrong!", null));
        }
    }

    //AUTHENTICATE to application
    public void authenticate() throws IOException {
        switch (processAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/page/user/dashboard.xhtml");
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Credentials"));
                break;
            default:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Oops something went wrong!"));
                break;
        }
    }

    private AuthenticationStatus processAuthentication() {
        ExternalContext ec = getExternalContext();
        Credential credential = staffLoginDTO.toCredential();
        return securityContext.authenticate((HttpServletRequest) ec.getRequest(), (HttpServletResponse) ec.getResponse(), AuthenticationParameters.withParams().credential(credential));
    }

    //Getters and Setters 
    public StaffDTO getStaffLoginDTO() {
        return staffLoginDTO;
    }

    public void setStaffLoginDTO(StaffDTO staffLoginDTO) {
        this.staffLoginDTO = staffLoginDTO;
    }

    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }

    public void test() {
        System.out.print("This is loginBean");
    }

    public void renderTest() {
        this.testRendered = true;
        PrimeFaces.current().ajax().update("testing");
    }

    public boolean isTestRendered() {
        return testRendered;
    }

    public void setTestRendered(boolean testRendered) {
        this.testRendered = testRendered;
    }
}

//    public void test() throws IOException{
//        getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/page/user/dashboard.xhtml");
//    }

