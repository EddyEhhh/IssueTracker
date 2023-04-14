/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Sam
 */
@Named
@ViewScoped
public class IssueUpdateBean implements Serializable {

    @EJB
    private IssueManager issueManager;

    @Inject
    private FacesContext facesContext;

    private IssueDTO selectedIssue;

    @Inject
    private NewTagDisplayBean newTagDisplayBean;
    
    public void init(IssueDTO issueDTO) {
        selectedIssue = issueDTO;
//        System.out.print(issueTagDTO.get(0).getTitle());
        newTagDisplayBean.setTagList(selectedIssue.getIssueDetailDTO().getIssueTagListDTO());
    }

    public void archiveIssue() {
        switch (issueManager.archive(selectedIssue)) {
            case SUCCESS:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Issue archived"));
                break;
            case ERROR:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Oops something went wrong"));
                break;
        }
    }

    public void updateIssue() throws IOException {
        switch (issueManager.updateIssue(selectedIssue)) {
            case "SUCCESS":
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Issue Updated"));
                PrimeFaces.current().executeScript("PF('manageIssueDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:issueList");
                break;
            default:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error Occured"));
                break;
        }
    }

    public NewTagDisplayBean getNewTagDisplayBean() {
        return newTagDisplayBean;
    }

    public void setNewTagDisplayBean(NewTagDisplayBean newTagDisplayBean) {
        this.newTagDisplayBean = newTagDisplayBean;
    }

    public IssueDTO getSelectedIssue() {
        return selectedIssue;
    }

    public void setSelectedIssue(IssueDTO selectedIssue) {
        this.selectedIssue = selectedIssue;
    }
}
