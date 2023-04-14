/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.IssueTagDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "issuetag")
public class IssueTag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private IssueDetail issueDetail;

    @Column(name = "title")
    private String title;

    @Column(name = "hexColor")
    private String hexColor;

    public IssueTag() {
    }

    public IssueTag(String title, String hexColor) {
        this.title = title;
        this.hexColor = hexColor;
    }

    public IssueTagDTO toDTO() {
        IssueTagDTO issueTagDTO = new IssueTagDTO(this.title, this.hexColor);
        return issueTagDTO;
    }

    public IssueDetail getIssueDetail() {
        return issueDetail;
    }

    public void setIssueDetail(IssueDetail issueDetail) {
        this.issueDetail = issueDetail;
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
