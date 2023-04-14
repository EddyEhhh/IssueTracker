/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.IssueDetailDTO;
import edu.mac.issuetracker.issue.dto.IssueTagDTO;
import edu.mac.issuetracker.issue.validator.IssuePriority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import edu.mac.issuetracker.issue.validator.IssueStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "issueDetail")
@NamedQueries({
    @NamedQuery(name = "IssueDetail.all", query = "select i from IssueDetail i order by i.id")
})
public class IssueDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private IssueDetail parentIssueDetail;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "childIssue", referencedColumnName = "id")
    private List<IssueDetail> childIssueDetail;

    @Column(name = "comment")
    private String comment;

    @IssueStatus
    @Column(name = "status")
    private String status;

    @IssuePriority
    @Column(name = "priority")
    private String priority;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "issueDetail_id", referencedColumnName = "id")
    private List<IssueTag> issueTagList;

    @Column(name = "progressLevel")
    private int progressLevel;

    @Column(name = "progressStage")
    private String progressStage;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "timestamp")
    private Date timestamp;

    @PrePersist
    private void onCreate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        timestamp = date;
    }
    
    public IssueDetail() {
    }

    //For toEntity
    public IssueDetail(String comment, String status, String priority, List<IssueTag> issueTagList, int progressLevel, String progressStage, LocalDateTime endDate) {
        this.comment = comment;
        this.status = status;
        this.priority = priority;
        this.issueTagList = issueTagList;
        this.progressLevel = progressLevel;
        this.progressStage = progressStage;
        this.endDate = endDate;
    }

    //First IssueDetail No Parent, No Child
    public IssueDetail(String comment, String priority, List<IssueTag> issueTagList, LocalDateTime endDate) {
        this.comment = comment;
        this.status = "Open";
        this.priority = priority;
        this.issueTagList = issueTagList;
        this.progressLevel = 0;
        this.progressStage = "Not Started";
        this.endDate = endDate;
    }

    //Subsequent IssueDetail: No Child
    public IssueDetail(IssueDetail parentIssueDetail, String comment, String status, String priority, List<IssueTag> issueTagList, int progressLevel, String progressStage, LocalDateTime endDate) {
        this.parentIssueDetail = parentIssueDetail;
        this.comment = comment;
        this.status = status;
        this.priority = priority;
        this.issueTagList = issueTagList;
        this.progressLevel = progressLevel;
        this.progressStage = progressStage;
        this.endDate = endDate;
    }

    public IssueDetailDTO toDTO() {
        List<IssueTagDTO> issueTagDTOList = new ArrayList<>();
        for (IssueTag issueTag : issueTagList) {
            issueTagDTOList.add(issueTag.toDTO());
        }
        IssueDetailDTO issueDetailDTO = new IssueDetailDTO(this.comment, this.status, this.priority, issueTagDTOList, this.progressLevel, this.progressStage, this.endDate);
        return issueDetailDTO;
    }

    public Long getId() {
        return id;
    }

    public IssueDetail getParentIssueDetail() {
        return parentIssueDetail;
    }

    public void setParentIssueDetail(IssueDetail parentIssueDetail) {
        this.parentIssueDetail = parentIssueDetail;
    }

    public List getChildIssueDetail() {
        return childIssueDetail;
    }

    public void setChildIssueDetail(List childIssueDetail) {
        this.childIssueDetail = childIssueDetail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<IssueTag> getIssueTagList() {
        return issueTagList;
    }

    public void setIssueTagList(List<IssueTag> issueTagList) {
        this.issueTagList = issueTagList;
    }

    public int getProgressLevel() {
        return progressLevel;
    }

    public void setProgressLevel(int progressLevel) {
        this.progressLevel = progressLevel;
    }

    public String getProgressStage() {
        return progressStage;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
