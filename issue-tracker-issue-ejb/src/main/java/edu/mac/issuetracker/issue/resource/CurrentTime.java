/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.resource;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jaibun Thana
 */

@Stateless
@Named("currentTime")
public class CurrentTime {
    
    public String getCurrentDateTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String datetime = ft.format(cal.getTime());
        return datetime;
    }
}
