/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

/**
 *
 * @author Sam
 */
public class TagColorDTO {

    private String colorName;

    private String hexColor;

    public TagColorDTO() {
    }

    public TagColorDTO(String colorName, String hexColor) {
        this.colorName = colorName;
        this.hexColor = hexColor;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}
