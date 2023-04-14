package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tohyu
 */
@Named("issueDetailBean")
@ViewScoped
public class IssueDetailBean implements Serializable {

    @Inject
    private IssueManager issueManager;

    private IssueDTO selectedIssue;

    private ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    private HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();

    @PostConstruct
    public void init() {
        try{
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //this is to request the 'id' which is the variable at the end on the url 
        String suuid = req.getParameter("id");
        //find the report by the 'id'
        selectedIssue = issueManager.findBySuuid(suuid);
        }catch (Exception e){}
    }

    public IssueDTO getSelectedIssue() {
        return selectedIssue;
    }

    public void setSelectedIssue(IssueDTO selectedIssue) {
        this.selectedIssue = selectedIssue;
    }
}
