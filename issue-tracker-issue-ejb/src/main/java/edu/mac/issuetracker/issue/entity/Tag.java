/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.TagDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "tag")
@NamedQueries({
    @NamedQuery(name = "Tag.byTitle", query = "select i from Tag i where i.title = :title"),})
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "colorId", referencedColumnName = "id")
    private TagColor tagColor;

    @Column(name = "title")
    private String title;

    public Tag() {
    }

    public Tag(String title, TagColor tagColor) {
        this.title = title;
        this.tagColor = tagColor;
    }

    public TagDTO toDTO() {
        TagDTO tagDTO = new TagDTO(this.title, this.tagColor);
        return tagDTO;
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
