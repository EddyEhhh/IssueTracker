/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueCustomerDetailDTO;
import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.dto.IssueDetailDTO;
import edu.mac.issuetracker.issue.dto.IssueTeamDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sam
 */
@Named
@RequestScoped
public class IssueCreateBean {

    @EJB
    private IssueManager issueManager;

    @Inject
    private FacesContext facesContext;

    @Inject
    private NewTagDisplayBean newTagDisplayBean;

    private IssueDTO issueCreateDTO;

    private Date minDateTime;

    // for dropdown view
    private List<String> status;

    private List<String> priority;

    private List<String> progressStage;

    private List<String> operatingSystem;

    private List<String> antivirus;

    private List<String> team;

    @PostConstruct
    public void init() {
        dropdownView();
        Date today = new Date();
        minDateTime = new Date(today.getTime());
        issueCreateDTO = new IssueDTO();
        issueCreateDTO.setIssueTeamDTO(new IssueTeamDTO());
        issueCreateDTO.setIssueDetailDTO(new IssueDetailDTO());
        issueCreateDTO.setIssueCustomerDetailDTO(new IssueCustomerDetailDTO());
    }

    public void createIssue() throws IOException {
        // front end List<Tag> to List<IssueTagDTO>
        /*List<IssueTagDTO> issueTagListDTO = new ArrayList<>();
        for (IssueTagDTO tag : newTagDisplayBean.getTagList()) {
            issueTagListDTO.add(tag.toIssueTagDTO());
        }*/
        issueCreateDTO.getIssueDetailDTO().setIssueTagListDTO(newTagDisplayBean.getTagList());
        switch (issueManager.createIssue(issueCreateDTO)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Issue Created"));
                break;
            default:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error Occured"));
                break;
        }
    }

    public void dropdownView() {
        status = new ArrayList<>();
        status.add("Open");
        status.add("In Progress");
        status.add("Resolved");

        priority = new ArrayList<>();
        priority.add("Low");
        priority.add("Medium");
        priority.add("High");

        progressStage = new ArrayList<>();
        progressStage.add("Not Started");
        progressStage.add("Preparation");
        progressStage.add("Identification");
        progressStage.add("Containment");
        progressStage.add("Acquisition");
        progressStage.add("Eradication");
        progressStage.add("Analysis");
        progressStage.add("Investigation");
        progressStage.add("Reporting");

        operatingSystem = new ArrayList<>();
        operatingSystem.add("Windows 10");
        operatingSystem.add("Windows 8");
        operatingSystem.add("Windows 7");

        antivirus = new ArrayList<>();
        antivirus.add("Installed");
        antivirus.add("Not Installed");
        antivirus.add("Not Sure");

        team = new ArrayList<>();
        team.add("Andrew Leng");
        team.add("Benjamin Ho");
        team.add("Clarence Tan");
        team.add("Cheryl Toh");
        team.add("Jaibun Thana");
        team.add("Samuel Chan");
        team.add("Tan Yi Ching");
        team.add("Toh Yun Zhen");
        team.add("Tay Jing Xun");
        team.add("Yeo Wan Lin");
    }

    public IssueDTO getIssueCreateDTO() {
        return issueCreateDTO;
    }

    public void setIssueCreateDTO(IssueDTO issueCreateDTO) {
        this.issueCreateDTO = issueCreateDTO;
    }

    public Date getMinDateTime() {
        return minDateTime;
    }

    public void setMinDateTime(Date minDateTime) {
        this.minDateTime = minDateTime;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getPriority() {
        return priority;
    }

    public void setPriority(List<String> priority) {
        this.priority = priority;
    }

    public List<String> getProgressStage() {
        return progressStage;
    }

    public void setProgressStage(List<String> progressStage) {
        this.progressStage = progressStage;
    }

    public List<String> getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(List<String> operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public List<String> getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(List<String> antivirus) {
        this.antivirus = antivirus;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }
}
