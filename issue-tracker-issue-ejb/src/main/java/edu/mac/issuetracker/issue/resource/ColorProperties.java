/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.resource;

import jakarta.ejb.Stateless;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class ColorProperties {

    public String relativeLuminance(String hex) {
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4), 16);
        double[] rgb = {r, g, b};
        List<Double> l = new ArrayList<>();
        for (double i : rgb) {
            i = i / 255;
            if (i <= 0.03928) {
                i = i / 12.92;
            } else {
                i = pow(((i + 0.055) / 1.055), 2.4);
            }
            l.add(i);
        }
        double luminance = 0.2126 * l.get(0) + 0.7152 * l.get(1) + 0.0722 * l.get(2);
        if (luminance > 0.179) {
            return "light";
        } else {
            return "dark";
        }
    }

}
