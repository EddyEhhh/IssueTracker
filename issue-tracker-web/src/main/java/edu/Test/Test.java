/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.Test;

import edu.mac.issuetracker.issue.manager.IssueManager;
import edu.mac.issuetracker.staff.authenticate.Authenticate2FA;
import edu.mac.issuetracker.staff.entity.Staff;
import edu.mac.issuetracker.staff.resource.MailUtil;
import edu.mac.issuetracker.staff.resource.StaffDetailGenerator;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import java.awt.image.BufferedImage;

/**
 *
 * @author E
 */

@RequestScoped
@Named
public class Test {
    
    @EJB
    private IssueManager staffManager;
    
    @Inject
    private StaffDetailGenerator gen;
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private IssueManager issueManager;
    
    @EJB
    private MailUtil mailUtil;
    
    @Inject
    private Authenticate2FA auth2FA;
    
    private String output;
    
    private Staff currentStaff;
    
    private BufferedImage qrCode;
    
    public void execute() throws Exception{
        //String email = securityContext.getCallerPrincipal().getName();
        //setCurrentStaff(staffManager.getStaffTest(email));
        mailUtil.sendMail("malwarelabcoregrouptest@gmail.com", "Subject test", "Whats up");
        
        //ByteArrayInputStream out = new ByteArrayInputStream(bytes);
        //qrCode = ImageIO.read(out);
    }
    
    public byte[] test() throws Exception {
        String barCode = auth2FA.getGoogleAuthenticatorBarCode("QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK", "eddy@gmail.com", "Malware Analysis Center");
        System.out.print(barCode);
        byte[] bytes = auth2FA.getQRCode(barCode, 200, 200);
        return bytes;
    }
    
    public void testing(){
        System.out.print(issueManager.getCurrentIssue());
    }
    
    /**
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * @return the gen
     */
    public StaffDetailGenerator getGen() {
        return gen;
    }

    /**
     * @param gen the gen to set
     */
    public void setGen(StaffDetailGenerator gen) {
        this.gen = gen;
    }

    /**
     * @return the currentStaff
     */
    public Staff getCurrentStaff() {
        return currentStaff;
    }

    /**
     * @param currentStaff the currentStaff to set
     */
    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    /**
     * @return the qrCode
     */
    public BufferedImage getQrCode() {
        return qrCode;
    }

    /**
     * @param qrCode the qrCode to set
     */
    public void setQrCode(BufferedImage qrCode) {
        this.qrCode = qrCode;
    }
    
    
    
}
