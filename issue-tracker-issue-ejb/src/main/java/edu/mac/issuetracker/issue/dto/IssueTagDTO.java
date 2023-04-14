/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.IssueTag;

/**
 *
 * @author Sam
 */
public class IssueTagDTO {

    private String title;

    private String hexColor;
    
    public IssueTagDTO(){}

    public IssueTagDTO(String title, String hexColor) {
        this.title = title;
        this.hexColor = hexColor;
    }

    public IssueTag toIssueTag() {
        IssueTag issueTag = new IssueTag(this.title, this.hexColor);
        return issueTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}
