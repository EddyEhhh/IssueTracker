/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.IssueTeam;
import java.util.List;

/**
 *
 * @author Sam
 */
public class IssueTeamDTO {

    private Long leadAccountId;

    private List<Long> collaboratorListAccountId;

    public IssueTeamDTO() {
    }

    public IssueTeamDTO(Long leadAccountId, List<Long> collaboratorListAccountId) {
        this.leadAccountId = leadAccountId;
        this.collaboratorListAccountId = collaboratorListAccountId;
    }

    public IssueTeam toIssueTeam() {
        IssueTeam issueTeam = new IssueTeam(this.leadAccountId, this.collaboratorListAccountId);
        return issueTeam;
    }

    public Long getLeadAccountId() {
        return leadAccountId;
    }

    public void setLeadAccountId(Long leadAccountId) {
        this.leadAccountId = leadAccountId;
    }

    public List<Long> getCollaboratorListAccountId() {
        return collaboratorListAccountId;
    }

    public void setCollaboratorListAccountId(List<Long> collaboratorListAccountId) {
        this.collaboratorListAccountId = collaboratorListAccountId;
    }
}
