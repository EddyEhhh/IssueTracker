/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issueExtAssignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name="IssueExtAssignment")
@NamedQueries({
    @NamedQuery(name = "IssueExtAssignment.byIssueId", query = "select i from IssueExtAssignment i where i.issueId = :issueId   "),
    @NamedQuery(name = "IssueExtAssignment.byCreatedById", query = "select i from IssueExtAssignment i where i.createdById = :createdById"),
    @NamedQuery(name = "IssueExtAssignment.byTeamId", query = "select i from IssueExtAssignment i where i.teamId = :teamId")
})
public class IssueExtAssignment {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(name="issueId")
    private long issueId;
    
    @Column(name="createdById")
    private long createdById;
    
    @Column(name="teamId")
    private long teamId;

    public IssueExtAssignment() {
    }
    
    public IssueExtAssignment(long issueId) {
        this.issueId = issueId;
    }
    
    public IssueExtAssignment(long issueId, long createdById) {
        this.issueId = issueId;
        this.createdById = createdById;
    }
    
    public IssueExtAssignment(long issueId, long createdById, long teamId) {
        this.issueId = issueId;
        this.createdById = createdById;
        this.teamId = teamId;
    }
    
    
    //SETTERS AND GETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(long createdById) {
        this.createdById = createdById;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
