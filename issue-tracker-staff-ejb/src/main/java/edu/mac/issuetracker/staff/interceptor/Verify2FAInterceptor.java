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
@Verify2FA
@Interceptor
public class Verify2FAInterceptor {

    @Inject
    private Authenticate authenticate;

    public Verify2FAInterceptor() {

    }

//    @AroundInvoke
//    public Object registerUser(InvocationContext ctx) throws Exception {
//        //get staffDTO parameter
//        Object[] parameters = ctx.getParameters();
//        StaffDTO staffDTO = (StaffDTO) parameters[0];
//
//          
//    }

}
