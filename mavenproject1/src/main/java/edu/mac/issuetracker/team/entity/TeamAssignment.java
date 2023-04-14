/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.team.entity;

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
@Table(name="issueTeam")
@NamedQueries({
    @NamedQuery(name = "Team.all", query = "select i from Team i order by i.id"),
    @NamedQuery(name = "Team.byLeadId", query = "select i from Team i where leadId = :leadId")
})
public class TeamAssignment {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="leadId")
    private Long leadId;
    
    @Column(name="collaboratorsId")
    private List<Long> collaboratorsId;

    
    public TeamAssignment(Long leadId, List<Long> collaboratorsId) {
        this.leadId = leadId;
        this.collaboratorsId = collaboratorsId;
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

    /**
     * @return the leadId
     */
    public Long getLeadId() {
        return leadId;
    }

    /**
     * @param leadId the leadId to set
     */
    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    /**
     * @return the collaboratorsId
     */
    public List<Long> getCollaboratorsId() {
        return collaboratorsId;
    }

    /**
     * @param collaboratorsId the collaboratorsId to set
     */
    public void setCollaboratorsId(List<Long> collaboratorsId) {
        this.collaboratorsId = collaboratorsId;
    }
    
}
