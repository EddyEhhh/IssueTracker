/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.TagColorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Sam
 */
@Entity
@Table(name = "tagcolor")
public class TagColor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "colorname")
    private String colorName;

    @Column(name = "hexcolor")
    private String hexColor;

    public TagColor() {
    }

    public TagColor(String colorName, String hexColor) {
        this.colorName = colorName;
        this.hexColor = hexColor;
    }

    public TagColorDTO toDTO() {
        TagColorDTO tagColorDTO = new TagColorDTO(this.colorName, this.hexColor);
        return tagColorDTO;
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
