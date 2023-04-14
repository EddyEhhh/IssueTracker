/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.user.temppackage;

import edu.mac.issuetracker.staff.authenticate.Authenticate;
import edu.mac.issuetracker.staff.data.StaffFacade;
import edu.mac.issuetracker.staff.entity.Staff;
import edu.mac.issuetracker.staff.authenticate.Authenticate2FA;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

/**
 *
 * @author E
 */

@Startup
@Singleton
public class InitializeData {
    
    @Inject
    private Authenticate authenticate;
    
    @Inject
    private StaffFacade facade;
    
    @Inject
    private Authenticate2FA auth2FA;
    
    @PostConstruct
    public void init(){
        if(facade.count()<=0){
            
        String password = authenticate.passwordHasher("Password0!");
        Staff staff = new Staff("eddy@gmail.com", "E", "Leon Eddy", password, null, false, false, "admin", "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK");
        facade.create(staff);
        
        password = authenticate.passwordHasher("Password0!");
        staff = new Staff("jasmine@gmail.com", "Jamine", "Ruby", password, null, false, false, "user", "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK");
        facade.create(staff);
        
        password = authenticate.passwordHasher("Password0!");
        staff = new Staff("first@gmail.com", "Micheal", "Beak", password, null, true, false, "user", "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK");
        facade.create(staff);
        
        }
//        String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
//        String lastCode = null;
//        while (true) {
//            String code = auth2FA.getTOTPCode(secretKey);
//            if (!code.equals(lastCode)) {
//                System.out.println(code);
//            }
//            lastCode = code;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {};
//        }
    }
    
}
