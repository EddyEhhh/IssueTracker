/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.web;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tohyu
 */
@Named("closedIssueBean")
@RequestScoped
public class ClosedIssueBean {

    @Inject
    private IssueManager issueManager;

    private List<IssueDTO> archivedIssue;

    private List<IssueDTO> filteredArchivedIssue;

    @PostConstruct
    public void init() {
        archivedIssue = issueManager.getArchived();
        Collections.reverse(archivedIssue);
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (StringUtils.isBlank(filterText)) {
            return true;
        }

        IssueDTO issue = (IssueDTO) value;
        return issue.getUuid().toLowerCase().contains(filterText)
                || issue.getTitle().toLowerCase().contains(filterText)
                || issue.getDescription().toLowerCase().contains(filterText)
                || issue.getIssueDetailDTO().getEndDate().toString().contains(filterText);
    }

    public List<IssueDTO> getArchivedIssue() {
        return archivedIssue;
    }

    public void setArchivedIssue(List<IssueDTO> archivedIssue) {
        this.archivedIssue = archivedIssue;
    }

    public List<IssueDTO> getFilteredArchivedIssue() {
        return filteredArchivedIssue;
    }

    public void setFilteredArchivedIssue(List<IssueDTO> filteredArchivedIssue) {
        this.filteredArchivedIssue = filteredArchivedIssue;
    }
}
