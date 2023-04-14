/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.interceptor;

import edu.mac.issuetracker.staff.authenticate.Authenticate;
import edu.mac.issuetracker.staff.data.StaffFacade;
import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.resource.MailUtil;
import edu.mac.issuetracker.staff.resource.StaffDetailGenerator;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

/**
 *
 * @author E
 */
@Register
@Interceptor
public class RegisterInterceptor {

    @Inject
    private MailUtil mailUtil;

    @Inject
    private StaffDetailGenerator staffDetailGenerator;

    @Inject
    private StaffFacade facade;

    @Inject
    private Authenticate authenticate;

    public RegisterInterceptor() {
    }

    @AroundInvoke
    public Object registerUser(InvocationContext ctx) throws Exception {
        //get staffDTO parameter
        Object[] parameters = ctx.getParameters();
        StaffDTO staffDTO = (StaffDTO) parameters[0];
        //get staffDTO variables
        String email = staffDTO.getEmail();
        if (facade.emailExist(email)) { 
            return "EMAIL_EXIST";
        } else {
            String recepientName = staffDTO.getFirstName() + " " + staffDTO.getLastName();
            //set staffDTO password
            String password = staffDetailGenerator.passwordGenerator();
//        System.out.print("Password: " + password);
//        System.out.print("Email: " + recepientEmail);
            staffDTO.setPassword(password);
            staffDTO.setPassword(authenticate.passwordHasher(password));
            //Gen profile
            String initials = staffDTO.getInitial();
            staffDTO.setProfilePicture(staffDetailGenerator.profilePictureGenerator(initials));
            //Set parameter to updated staffDTO password
            parameters[0] = staffDTO;
            ctx.setParameters(parameters);
            //Email
            String applicationName = "MAC Issue Tracker";
            String websiteURL = "localhost:8080";
            String emailSubject = String.format("%1$s: new account", applicationName);
            String emailText = String.format("Hi %1$s,\n\n "
                    + "A new %2$s account has been created for you.\n"
                    + "Please confirm your new account at by logging on to\n"
                    + "%3$s\n\n"
                    + "Please login with this email and temporary password:\n"
                    + "%4$s\n"
                    + "Thank you",
                     recepientName, applicationName, websiteURL, password);
            if (!mailUtil.sendMail(email, emailSubject, emailText)){return "EMAIL_FAILED";}
        }
        try {
            return ctx.proceed();
        } catch (Exception e) {
            return "FAILURE";
        }
    }

}
