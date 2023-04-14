/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.resource;

import edu.mac.issuetracker.staff.data.StaffFacade;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author E
 */

@Stateless
@Named("staffDetailGenerator")
public class StaffDetailGenerator {
    
    @Inject
    private StaffFacade facade;
    
    //Random password with min: 1 upper, 1 lower, 1 symbol, 1 number.
    public String passwordGenerator(){
        //Possible values
        char[] LowerAlpha = (new String("abcdefghijklmnopqrstuvwxyz")).toCharArray();
        char[] UpperAlpha = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ")).toCharArray();
        char[] Num = (new String("0123456789")).toCharArray();
        char[] Symbol = (new String("~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
        //
        //All possible values
        char[] Chars = ArrayUtils.addAll(ArrayUtils.addAll(LowerAlpha, UpperAlpha), ArrayUtils.addAll(Num, Symbol));
        char[][] Array = {LowerAlpha, UpperAlpha, Num, Symbol};
        //
        //Gen a upper, lower, symbol, number to fit min requirements.
        String minimumRequirement = "";
        for (int i = 0; i < Array.length; i++){
            String randomItem = RandomStringUtils.random(1, Array[i]);
            minimumRequirement += randomItem;
        }
        //
        //Gen fully random 12 char string + minimumRequirement
        String randomString = RandomStringUtils.random(12, 0, Chars.length-1, false, false, Chars, new SecureRandom()) + minimumRequirement;
        //
        //Shuffle string
        List<String> randomList = Arrays.asList(randomString.split(""));
        Collections.shuffle(randomList);
        String shuffledString = "";
        for(String item : randomList){
            shuffledString += item;
        }
        return(shuffledString);
    }
    
    //Image generator
    
    public String profilePictureGenerator(String text) throws IOException{
        int length = 200;
        
        BufferedImage image = new BufferedImage(length, length, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        
        Random ran = new Random();
        //HSB colors 
        float hue = ran.nextFloat(); //range 360: [0-1.0]
        float saturation = 0.30f; //range 0-1
        float brightness = 0.75f; //range 0-1
                
        
        //create Background
        graphics.setColor(Color.getHSBColor(hue, saturation, brightness));
        graphics.fillRect(0, 0, length, length);
        
        //Add text to image
        float textBrightness = brightness - 0.25f;
        Font font = new Font("Arial", Font.PLAIN, 90); //
        FontMetrics fontMetrics = graphics.getFontMetrics(font); 
        float centerX = (length - fontMetrics.stringWidth(text)) / 2; //text X axis cetner. Different letters have differnt width
        float centerY = (fontMetrics.getAscent() + (length - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);  //text Y axis cetner. Assuming all letters have same height
        graphics.setColor(Color.getHSBColor(hue, saturation, textBrightness)); //text color
        graphics.setFont(font); //text font
        graphics.drawString(text, centerX, centerY); //add text to image

        graphics.dispose();
        String fileFormat = "jpg";
        String fileName = timeBasedId() + "." + fileFormat;
        File file = new File(fileName);
        ImageIO.write(image, fileFormat, file);
        return fileName;
    }
    
    public String timeBasedId(){
        Date current = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String datetime = ft.format(current);
        String value = Integer.toString(facade.count() % 100);
        if (value.length() < 2){value = "0" + value;}
        return datetime + value;
    }
    
}
