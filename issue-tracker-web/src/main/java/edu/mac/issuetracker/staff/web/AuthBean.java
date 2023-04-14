/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.web;

import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import java.io.IOException;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jaibun Thana
 */
@Named
@RequestScoped
public class AuthBean implements Serializable {
    
    @Inject
    private StaffManager staffManager;
    
    @Inject
    private FacesContext facesContext;
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private LoginBean loginBean;
    
    private ExternalContext getExternalContext(){
        return facesContext.getExternalContext();
    }
    
    private boolean authRendered = false;
    
    private boolean firstAuthRendered = false;
    
    private boolean authLoginButtonRendered = false;
    
    private boolean authButtonRendered = false;
    
    private Credential credential;
    
    private String otp;
    
    private String email;
    
    private byte[] qrCodeImage;
    
    private void renderAuth(String email) throws Exception{
        if(staffManager.isFirstLogin(email)){
                this.qrCodeImage = staffManager.getAuthQR(email, "MAC");
                renderFirstAuthPanel();
            }
        renderAuthPanel();
    }
    
    //render and display Auth panel for auth.xhtml
    private void renderAuthPanel(){
        this.setAuthRendered(true);
        System.out.print("AUTHPANEL");
        PrimeFaces.current().ajax().update("authPanel");
        PrimeFaces.current().executeScript("PF('authDialog').show();");
//        Map<String, Object> options = new HashMap<>();
//        options.put("closable", false);
//        options.put("id", "authDialog");
//        options.put("widgetVar", "authDialog");
//        options.put("minHeight", 40);
//        options.put("width", "350");
//        options.put("showEffect", "fade");
//        options.put("modal", true);
//        options.put("draggable", false);
//        options.put("header", "#{msg['auth.twoFactorAuthentication']}");
//        PrimeFaces.current().dialog().openDynamic("/template/overlay/auth", options, null);
    }
    
    //render QRcode and message
    private void renderFirstAuthPanel(){
        this.setFirstAuthRendered(true);
        PrimeFaces.current().ajax().update("firstAuthPanel");
        
    }
    
    //Verify credentials, output 2FA
    public void prepareAuth2FALogin(StaffDTO staffDTO) throws Exception{
        String email = staffDTO.getEmail();
        String password = staffDTO.getPassword();
        if(staffManager.isCredentialValid(email, password)){
            //valid credential
            renderAuth(email);
        }else{
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
        }
    }
    
    public void test(){
        System.out.print("This is authBean");
    }
    
    //Verify2FA code and proceed with auth
    public void process2FA() throws IOException{
        String otp = getOtp();
        String email;
        System.out.print(otp);
        if(staffManager.isAuthenticated()){
            email = securityContext.getCallerPrincipal().getName();
        }else{
            email = loginBean.getStaffLoginDTO().getEmail();
            System.out.print("email" + email);
        }
        System.out.print("PROCESS Bool: "+staffManager.checkInputTOTP(otp, email));
        if(staffManager.checkInputTOTP(otp, email)){
            if(staffManager.isAuthenticated()){
                System.out.print("Correct OTP"); //If OTP is correct proceed. Future implementation for priviledge escalation
            }else{ //OTP for login
                loginBean.authenticate();
            }
        }else{
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid otp", null));
        }
    }

    public boolean isAuthRendered() {
        return authRendered;
    }

    public void setAuthRendered(boolean authRendered) {
        this.authRendered = authRendered;
    }

    public boolean isFirstAuthRendered() {
        return firstAuthRendered;
    }

    public void setFirstAuthRendered(boolean firstAuthRendered) {
        this.firstAuthRendered = firstAuthRendered;
    }

    public boolean isAuthLoginButtonRendered() {
        return authLoginButtonRendered;
    }

    public void setAuthLoginButtonRendered(boolean authLoginButtonRendered) {
        this.authLoginButtonRendered = authLoginButtonRendered;
    }

    public boolean isAuthButtonRendered() {
        return authButtonRendered;
    }

    public void setAuthButtonRendered(boolean authButtonRendered) {
        this.authButtonRendered = authButtonRendered;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(byte[] qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }
}
