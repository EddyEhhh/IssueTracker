/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.resource;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author E
 */

@Stateless
@Named("mailUtil")
public class MailUtil {
    
    
    public boolean sendMail(String recepient, String subject, String text) throws Exception{
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        
        String senderMail = "someEmail@gmail.com";
        String senderPassword = "**********!";
        
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderMail, senderPassword);
                    }
                });
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recepient)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.print("Email sent");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        
        
        
    }
    
}
