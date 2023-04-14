package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Sam
 */
@Named
@RequestScoped
public class IssueDisplayBean {

    @EJB
    private IssueManager issueManager;

    private List<IssueDTO> openIssues;

    private List<IssueDTO> inProgressIssues;

    private List<IssueDTO> resolvedIssues;

    @PostConstruct
    public void init() {
        openIssues = issueManager.getOpenIssues();
        inProgressIssues = issueManager.getInProgressIssues();
        resolvedIssues = issueManager.getResolvedIssues();
        changePriorityIcon(openIssues);
        changePriorityIcon(inProgressIssues);
        changePriorityIcon(resolvedIssues);
        changeTimeLeft(openIssues);
        changeTimeLeft(inProgressIssues);
        changeTimeLeft(resolvedIssues);
        
    }
    
    public void timeLeft(List<IssueDTO> issues){
        for(IssueDTO eachIssue : issues){
            LocalDateTime lt = LocalDateTime.now();
            String timeLeft = Long.toString(ChronoUnit.MINUTES.between(lt, eachIssue.getIssueDetailDTO().getEndDate()));
            eachIssue.getIssueDetailDTO().setTimeLeft(timeLeft);
        }
    }

    public void changePriorityIcon(List<IssueDTO> issueList) {
        for (IssueDTO issueDTO : issueList) {
            switch (issueDTO.getIssueDetailDTO().getPriority()) {
                case "Low":
                    issueDTO.getIssueDetailDTO().setPriorityIcon("pi pi-angle-down");
                    break;
                case "Medium":
                    issueDTO.getIssueDetailDTO().setPriorityIcon("pi pi-angle-up");
                    break;
                case "High":
                    issueDTO.getIssueDetailDTO().setPriorityIcon("pi pi-angle-double-up");
                    break;
            }
        }
    }

    public void changeTimeLeft(List<IssueDTO> issueList) {
        for (IssueDTO issueDTO : issueList) {
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = issueDTO.getIssueDetailDTO().getEndDate();
            Duration diff = Duration.between(start, end);
            Long diffHours = diff.toHours();
            Long diffDays = diff.toDays();
            if (diffHours < 0) {
                issueDTO.getIssueDetailDTO().setTimeLeft("0 hours left");
            } else if (diffHours > 24 && diffHours < 49) {
                issueDTO.getIssueDetailDTO().setTimeLeft(diffDays + " day left");
            } else if (diffHours > 48) {
                issueDTO.getIssueDetailDTO().setTimeLeft(diffDays + " days left");
            } else if (diffHours == 1) {
                issueDTO.getIssueDetailDTO().setTimeLeft(diffHours + " hour left");
            } else {
                issueDTO.getIssueDetailDTO().setTimeLeft(diffHours + " hours left");
            }
        }
    }

    public List<IssueDTO> getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(List<IssueDTO> openIssues) {
        this.openIssues = openIssues;
    }

    public List<IssueDTO> getInProgressIssues() {
        return inProgressIssues;
    }

    public void setInProgressIssues(List<IssueDTO> inProgressIssues) {
        this.inProgressIssues = inProgressIssues;
    }

    public List<IssueDTO> getResolvedIssues() {
        return resolvedIssues;
    }

    public void setResolvedIssues(List<IssueDTO> resolvedIssues) {
        this.resolvedIssues = resolvedIssues;
    }
}
