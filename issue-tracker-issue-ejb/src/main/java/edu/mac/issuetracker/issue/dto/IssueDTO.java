/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.Issue;
import edu.mac.issuetracker.staff.dto.StaffDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

/**
 *
 * @author Sam
 */
public class IssueDTO {

    private IssueTeamDTO issueTeamDTO;

    private IssueDetailDTO issueDetailDTO;

    private IssueCustomerDetailDTO issueCustomerDetailDTO;

    private String uuid;

    @NotBlank(message = "Field cannot be empty")
    private String title;

    @NotBlank(message = "Field cannot be empty")
    private String description;
    
    private Date createTimestamp;
    
    private StaffDTO createdBy;

    public IssueDTO() {
    }

    public IssueDTO(IssueTeamDTO issueTeamDTO, IssueDetailDTO issueDetailDTO, IssueCustomerDetailDTO issueCustomerDetailDTO, String uuid, String title, String description) {
        this.issueTeamDTO = issueTeamDTO;
        this.issueDetailDTO = issueDetailDTO;
        this.issueCustomerDetailDTO = issueCustomerDetailDTO;
        this.uuid = uuid;
        this.title = title;
        this.description = description;
    }
    
    public IssueDTO(IssueTeamDTO issueTeamDTO, IssueDetailDTO issueDetailDTO, IssueCustomerDetailDTO issueCustomerDetailDTO, String uuid, String title, String description, Date createTimestamp) {
        this.issueTeamDTO = issueTeamDTO;
        this.issueDetailDTO = issueDetailDTO;
        this.issueCustomerDetailDTO = issueCustomerDetailDTO;
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.createTimestamp = createTimestamp;
    }
    

    public Issue toIssue() {
        Issue issue = new Issue(this.issueTeamDTO.toIssueTeam(), this.issueDetailDTO.toIssueDetail(), this.issueCustomerDetailDTO.toIssueCustomerDetail(), this.uuid, this.title, this.description);
        return issue;
    }

    
    /**
     * @return the createdBy
     */
    public StaffDTO getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(StaffDTO createdBy) {
        this.createdBy = createdBy;
    }
    
    
    public IssueTeamDTO getIssueTeamDTO() {
        return issueTeamDTO;
    }

    public void setIssueTeamDTO(IssueTeamDTO issueTeamDTO) {
        this.issueTeamDTO = issueTeamDTO;
    }

    public IssueDetailDTO getIssueDetailDTO() {
        return issueDetailDTO;
    }

    public void setIssueDetailDTO(IssueDetailDTO issueDetailDTO) {
        this.issueDetailDTO = issueDetailDTO;
    }

    public IssueCustomerDetailDTO getIssueCustomerDetailDTO() {
        return issueCustomerDetailDTO;
    }

    public void setIssueCustomerDetailDTO(IssueCustomerDetailDTO issueCustomerDetailDTO) {
        this.issueCustomerDetailDTO = issueCustomerDetailDTO;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createTimestamp
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @param createTimestamp the createTimestamp to set
     */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
