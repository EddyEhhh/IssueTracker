/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.IssueDTO;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "issue")
@NamedQueries({
    @NamedQuery(name = "Issue.all", query = "select i from Issue i where i.archive = :isArchive"),
    @NamedQuery(name = "Issue.byStatus", query = "select i from Issue i JOIN i.issueDetailPointer d where d.status = :status and i.archive = :isArchive"),
    @NamedQuery(name = "Issue.byUuid", query = "select i from Issue i where i.uuid = :uuid")
   
})
public class Issue {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private IssueTeam team;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "issueDetailPointerId", referencedColumnName = "id")
    private IssueDetail issueDetailPointer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "relatedIssueDetailId", referencedColumnName = "id")
    private List<IssueDetail> relatedIssueDetailId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "issueCustomerDetailId", referencedColumnName = "id")
    private IssueCustomerDetail issueCustomerDetail;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="relatedIssueDetailId", referencedColumnName = "id")
//    private List<Long> relatedIssueDetail;
//    private Long issueParentId;
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "createTimestamp")
    private Date createTimestamp;

    @Column(name = "inProgressTimestamp")
    private Date inProgresTimestamp;

    @Column(name = "resolvedTimestamp")
    private Date resolvedTimestamp;

    @Column(name = "closedTimestamp")
    private Date closedTimestamp;

    @Column(name = "isArchive")
    private boolean archive;

    @PrePersist
    private void onCreate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        createTimestamp = date;
    }

    public Issue() {
    }

    //New issue
    public Issue(IssueTeam team, IssueDetail issueDetailPointer, IssueCustomerDetail issueCustomerDetail, String uuid, String title, String description) {
        this.team = team;
        this.issueDetailPointer = issueDetailPointer;
        this.issueCustomerDetail = issueCustomerDetail;
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
    }

    public IssueDTO toDTO() {
        IssueDTO issueDTO = new IssueDTO(this.team.toDTO(), this.issueDetailPointer.toDTO(), this.issueCustomerDetail.toDTO(), this.uuid, this.title, this.description, this.getCreateTimestamp());
        return issueDTO;
    }

    public IssueTeam getTeam() {
        return team;
    }

    public void setTeam(IssueTeam team) {
        this.team = team;
    }

    public IssueDetail getIssueDetailPointer() {
        return issueDetailPointer;
    }

    public void setIssueDetailPointer(IssueDetail issueDetailPointer) {
        this.issueDetailPointer = issueDetailPointer;
    }

    public IssueCustomerDetail getIssueCustomerDetail() {
        return issueCustomerDetail;
    }

    public void setIssueCustomerDetail(IssueCustomerDetail issueCustomerDetail) {
        this.issueCustomerDetail = issueCustomerDetail;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uid) {
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

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getClosedTimestamp() {
        return closedTimestamp;
    }

    public void setClosedTimestamp(Date closedTimestamp) {
        this.closedTimestamp = closedTimestamp;
    }

    public Date getResolvedTimestamp() {
        return resolvedTimestamp;
    }

    public void setResolvedTimestamp(Date resolvedTimestamp) {
        this.resolvedTimestamp = resolvedTimestamp;
    }

    public Date getInProgresTimestamp() {
        return inProgresTimestamp;
    }

    public void setInProgresTimestamp(Date inProgresTimestamp) {
        this.inProgresTimestamp = inProgresTimestamp;
    }

    public List<IssueDetail> getRelatedIssueDetailId() {
        return relatedIssueDetailId;
    }

    public void setRelatedIssueDetailId(List<IssueDetail> relatedIssueDetailId) {
        this.relatedIssueDetailId = relatedIssueDetailId;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
