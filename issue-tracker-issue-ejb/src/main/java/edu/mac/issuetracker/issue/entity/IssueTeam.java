/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.IssueTeamDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "issueTeam")
@NamedQueries({
    @NamedQuery(name = "IssueTeam.all", query = "select i from IssueTeam i order by i.id")
})
public class IssueTeam {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "leadAccountId")
    private Long leadAccountId;

    @Column(name = "collaboratorListAccountId")
    private List<Long> collaboratorListAccountId;

    public IssueTeam() {
    }

    public IssueTeam(Long leadAccountId, List<Long> collaboratorListAccountId) {
        this.leadAccountId = leadAccountId;
        this.collaboratorListAccountId = collaboratorListAccountId;
    }

    public IssueTeamDTO toDTO() {
        IssueTeamDTO issueTeamDTO = new IssueTeamDTO(this.leadAccountId, this.collaboratorListAccountId);
        return issueTeamDTO;
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
