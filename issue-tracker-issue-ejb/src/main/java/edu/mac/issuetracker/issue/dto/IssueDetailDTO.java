/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.IssueDetail;
import edu.mac.issuetracker.issue.entity.IssueTag;
import edu.mac.issuetracker.issue.validator.IssuePriority;
import edu.mac.issuetracker.issue.validator.IssueStatus;
import edu.mac.issuetracker.staff.dto.StaffDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sam
 */
public class IssueDetailDTO {

    private IssueDetailDTO parentIssueDetail;

    private List<IssueDetailDTO> childIssueDetail;

    private String comment;

    @IssueStatus
    private String status;

    @IssuePriority
    private String priority;

    private String priorityIcon;

    private List<IssueTagDTO> issueTagListDTO;

    @NotNull(message = "Field cannot be empty")
    private int progressLevel;

    @NotBlank(message = "Field cannot be empty")
    private String progressStage;

    @NotNull(message = "Field cannot be empty")
    private LocalDateTime endDate;

    private String timeLeft;
    
    private StaffDTO leadStaff;
    
    private List<StaffDTO> contributerStaff;

    public IssueDetailDTO() {
    }

    //First IssueDetail No Parent, No Child
    public IssueDetailDTO(String comment, String status, String priority, List<IssueTagDTO> issueTagListDTO, int progressLevel, String progressStage, LocalDateTime endDate) {
        this.comment = comment;
        this.status = status;
        this.priority = priority;
        this.issueTagListDTO = issueTagListDTO;
        this.progressLevel = progressLevel;
        this.progressStage = progressStage;
        this.endDate = endDate;
    }

    //Subsequent IssueDetail: No Child
    public IssueDetailDTO(IssueDetailDTO parentIssueDetail, String comment, String status, String priority, List<IssueTagDTO> issueTagListDTO, int progressLevel, String progressStage, LocalDateTime endDate) {
        this.parentIssueDetail = parentIssueDetail;
        this.comment = comment;
        this.status = status;
        this.priority = priority;
        this.issueTagListDTO = issueTagListDTO;
        this.progressLevel = progressLevel;
        this.progressStage = progressStage;
        this.endDate = endDate;
    }

    public IssueDetailDTO(String priorityIcon, String timeLeft) {
        setPriorityIcon(priorityIcon);
        setTimeLeft(timeLeft);
    }

    public IssueDetail toIssueDetail() {
        List<IssueTag> issueTagList = new ArrayList<>();
        for (IssueTagDTO issueTagDTO : issueTagListDTO) {
            issueTagList.add(issueTagDTO.toIssueTag());
        }
        if (this.status == null && this.progressStage == null && this.progressLevel == 0) {
            IssueDetail issueDetail = new IssueDetail(this.comment, this.priority, issueTagList, this.endDate);
            return issueDetail;
        } else {
            IssueDetail issueDetail = new IssueDetail(this.comment, this.status, this.priority, issueTagList, this.progressLevel, this.progressStage, this.endDate);
            return issueDetail;
        }
    }

    public IssueDetailDTO getParentIssueDetail() {
        return parentIssueDetail;
    }

    public void setParentIssueDetail(IssueDetailDTO parentIssueDetail) {
        this.parentIssueDetail = parentIssueDetail;
    }

    public List<IssueDetailDTO> getChildIssueDetail() {
        return childIssueDetail;
    }

    public void setChildIssueDetail(List<IssueDetailDTO> childIssueDetail) {
        this.childIssueDetail = childIssueDetail;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityIcon() {
        return priorityIcon;
    }

    public void setPriorityIcon(String priorityIcon) {
        this.priorityIcon = priorityIcon;
    }

    public List<IssueTagDTO> getIssueTagListDTO() {
        return issueTagListDTO;
    }

    public void setIssueTagListDTO(List<IssueTagDTO> issueTagListDTO) {
        this.issueTagListDTO = issueTagListDTO;
    }

    public int getProgressLevel() {
        return progressLevel;
    }

    public String getProgressStage() {
        return progressStage;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProgressLevel(int progressLevel) {
        this.progressLevel = progressLevel;
    }

    public void setProgressStage(String progressStage) {
        this.progressStage = progressStage;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
