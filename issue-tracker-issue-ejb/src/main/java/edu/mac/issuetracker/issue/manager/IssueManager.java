/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.issue.manager;

import edu.mac.issuetracker.issue.data.IssueDetailFacade;
import edu.mac.issuetracker.issue.data.IssueFacade;
import edu.mac.issuetracker.issue.data.TagColorFacade;
import edu.mac.issuetracker.issue.data.TagFacade;
import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.dto.IssueDetailDTO;
import edu.mac.issuetracker.issue.dto.TagColorDTO;
import edu.mac.issuetracker.issue.dto.TagDTO;
import edu.mac.issuetracker.issue.entity.Issue;
import edu.mac.issuetracker.issue.entity.IssueDetail;
import edu.mac.issuetracker.issue.entity.Tag;
import edu.mac.issuetracker.issue.entity.TagColor;
import edu.mac.issuetracker.issue.outputinfo.DeleteStatus;
import edu.mac.issuetracker.issueExtAssignment.entity.IssueExtAssignment;
import edu.mac.issuetracker.issueExtAssignment.manager.IssueExtAssignmentManager;
import edu.mac.issuetracker.staff.authenticate.Authenticate;
import edu.mac.issuetracker.staff.data.StaffFacade;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
//Exploses entity managers, converts Entity to DTO vice versa, 
@Stateless
@DeclareRoles({"admin", "user"})
public class IssueManager {

    @Inject
    private IssueFacade issueFacade;

    @Inject
    private StaffFacade staffFacade;

    @Inject
    private IssueDetailFacade issueDetailFacade;

    @Inject
    private TagFacade tagFacade;

    @Inject
    private IssueExtAssignmentManager issueExtAssignmentManager;

    @Inject
    private Authenticate authenticate;

    @Inject
    private TagColorFacade tagColorFacade;

    public Issue find(long staffId) {
        return issueFacade.find(staffId);
    }

    public List<Issue> getCurrentIssue() {
        return issueFacade.getAllIssue(false);
    }

    @RolesAllowed({"user", "admin"})
    public List<TagDTO> getAllTag() {
        List<Tag> tagList = tagFacade.findAll();
        List<TagDTO> tagDTOList = new ArrayList<>();
        for (Tag i : tagList) {
            tagDTOList.add(i.toDTO());
        }
        return tagDTOList;
    }

    @RolesAllowed({"user", "admin"})
    public List<TagColorDTO> getAllTagColor() {
        List<TagColor> tagColorList = tagColorFacade.findAll();
        List<TagColorDTO> tagColorDTOList = new ArrayList<>();
        for (TagColor i : tagColorList) {
            tagColorDTOList.add(i.toDTO());
        }
        return tagColorDTOList;
    }

    @RolesAllowed({"user", "admin"})
    public List<IssueDTO> getOpenIssues() {
        List<Issue> issueList = issueFacade.getIssueByStatus("Open");
        List<IssueDTO> issueDTOList = new ArrayList<>();
        IssueDTO tempIssueDTO = new IssueDTO();
        for (Issue i : issueList) {
            IssueExtAssignment issueExtAssignment = issueExtAssignmentManager.find(i.getIssueDetailPointer().getId());
            Long createdById = issueExtAssignment.getCreatedById();
            //Long teamId = issueExtAssignment.getTeamId();
            tempIssueDTO = i.toDTO();
            tempIssueDTO.setCreatedBy(staffFacade.find(createdById).toDTO());
            issueDTOList.add(tempIssueDTO);
        }
        return issueDTOList;
    }

    @RolesAllowed({"user", "admin"})
    public List<IssueDTO> getInProgressIssues() {
        List<Issue> issueList = issueFacade.getIssueByStatus("In Progress");
        List<IssueDTO> issueDTOList = new ArrayList<>();
        IssueDTO tempIssueDTO = new IssueDTO();
        for (Issue i : issueList) {
            IssueExtAssignment issueExtAssignment = issueExtAssignmentManager.find(i.getIssueDetailPointer().getId());
            Long createdById = issueExtAssignment.getCreatedById();
            //Long teamId = issueExtAssignment.getTeamId();
            tempIssueDTO = i.toDTO();
            tempIssueDTO.setCreatedBy(staffFacade.find(createdById).toDTO());
            issueDTOList.add(tempIssueDTO);
        }
        return issueDTOList;
    }

    @RolesAllowed({"user", "admin"})
    public List<IssueDTO> getResolvedIssues() {
        List<Issue> issueList = issueFacade.getIssueByStatus("Resolved");
        List<IssueDTO> issueDTOList = new ArrayList<>();
        IssueDTO tempIssueDTO = new IssueDTO();
        for (Issue i : issueList) {
            IssueExtAssignment issueExtAssignment = issueExtAssignmentManager.find(i.getIssueDetailPointer().getId());
            Long createdById = issueExtAssignment.getCreatedById();
            //Long teamId = issueExtAssignment.getTeamId();
            tempIssueDTO = i.toDTO();
            tempIssueDTO.setCreatedBy(staffFacade.find(createdById).toDTO());
            issueDTOList.add(tempIssueDTO);
        }
        return issueDTOList;
    }

    @RolesAllowed({"user", "admin"})
    public String createIssue(IssueDTO issueCreateDTO) {
        Issue issue = issueCreateDTO.toIssue();
        issueFacade.create(issue);
        
        IssueExtAssignment issueExtAssignment = new IssueExtAssignment();
        IssueDetail issueDetail = issue.getIssueDetailPointer();

        issueExtAssignment.setIssueId(issueDetail.getId());
        Long currentUserId = authenticate.getCurrentSession().getId();
        issueExtAssignment.setCreatedById(currentUserId);

        //NO CREATE TEAM YET
        issueExtAssignmentManager.create(issueExtAssignment);
        return "SUCCESS";
    }

    @RolesAllowed({"user", "admin"})
    public String updateIssue(IssueDTO selectedIssue) {
        //Get current issue
        Issue issue = issueFacade.getIssue(selectedIssue.getUuid());

        //Get current issue detail
        IssueDetail currentIssueDetailPointer = issue.getIssueDetailPointer();

        //Create new issue detail and set the parent to the current issue detail
        IssueDetailDTO newIssueDetailDTO = new IssueDetailDTO();
        newIssueDetailDTO = selectedIssue.getIssueDetailDTO();
        IssueDetail newIssueDetail = newIssueDetailDTO.toIssueDetail();
        newIssueDetail.setParentIssueDetail(currentIssueDetailPointer);
        issueDetailFacade.create(newIssueDetail);

        //Set current issue detail child to new issue detail
        List<IssueDetail> childIssueDetail = new ArrayList<>();
        childIssueDetail.add(newIssueDetail);
        currentIssueDetailPointer.setChildIssueDetail(childIssueDetail);
        issueDetailFacade.edit(currentIssueDetailPointer);

        //Update issue detail pointer of the issue to the new issue detail
        issue.setIssueDetailPointer(newIssueDetail);

        //Update current issue customer detail
        issue.setIssueCustomerDetail(selectedIssue.getIssueCustomerDetailDTO().toIssueCustomerDetail());

        issueFacade.edit(issue);
        
        IssueExtAssignment issueExtAssignment = new IssueExtAssignment();
        IssueDetail issueDetail = issue.getIssueDetailPointer();

        issueExtAssignment.setIssueId(issueDetail.getId());
        Long currentUserId = authenticate.getCurrentSession().getId();
        issueExtAssignment.setCreatedById(currentUserId);

        //NO CREATE TEAM YET
        issueExtAssignmentManager.create(issueExtAssignment);

        return "SUCCESS";
    }

    @RolesAllowed("admin")
    public DeleteStatus archive(IssueDTO issueDTO) {
        String uuid = issueDTO.getUuid();
        try {
            Issue issue = issueFacade.getIssue(uuid);
            issue.setArchive(true);
            issueFacade.edit(issue);
        } catch (Exception e) {
            return DeleteStatus.ERROR;
        }
        return DeleteStatus.SUCCESS;
    }

    @RolesAllowed("admin")
    public List<IssueDTO> getArchived() {
        List<Issue> issues = issueFacade.getAllIssue(true);
        List<IssueDTO> issuesDTO = new ArrayList<>();
        IssueDTO tempIssueDTO = new IssueDTO();
        for (Issue eachIssue : issues) {
            IssueExtAssignment issueExtAssignment = issueExtAssignmentManager.find(eachIssue.getIssueDetailPointer().getId());
            Long createdById = issueExtAssignment.getCreatedById();
            //Long teamId = issueExtAssignment.getTeamId();
            tempIssueDTO = eachIssue.toDTO();
            tempIssueDTO.setCreatedBy(staffFacade.find(createdById).toDTO());
            issuesDTO.add(tempIssueDTO);
        }
        return issuesDTO;
    }

    public IssueDTO findBySuuid(String suuid) {
        Issue issue = issueFacade.getIssue(suuid);
        IssueDTO issueDTO = issue.toDTO();
        return issueDTO;
    }
}
