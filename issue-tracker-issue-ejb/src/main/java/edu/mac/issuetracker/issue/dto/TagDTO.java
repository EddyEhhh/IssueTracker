/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.TagColor;

/**
 *
 * @author Sam
 */
public class TagDTO {

    private String title;

    private TagColor tagColor;

    public TagDTO() {
    }

    public TagDTO(String title, TagColor tagColor) {
        this.title = title;
        this.tagColor = tagColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TagColor getTagColor() {
        return tagColor;
    }

    public void setTagColor(TagColor tagColor) {
        this.tagColor = tagColor;
    }
}
