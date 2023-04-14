/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issueExtAssignment.manager;

import edu.mac.issuetracker.issueExtAssignment.data.IssueExtAssignmentFacade;
import edu.mac.issuetracker.issueExtAssignment.entity.IssueExtAssignment;
import edu.mac.issuetracker.issueExtAssignment.outputInfo.DeleteMappingStatus;
import edu.mac.issuetracker.issueExtAssignment.outputInfo.MappingStatus;
import edu.mac.issuetracker.issueExtAssignment.outputInfo.UpdateMappingStatus;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class IssueExtAssignmentManager {
    
    @Inject
    private IssueExtAssignmentFacade issueExtAssignmentFacade;
    
    public IssueExtAssignment find(Long id){
        IssueExtAssignment issueExtAssignment = new IssueExtAssignment(id);
        return issueExtAssignmentFacade.getMappingByIssueId(issueExtAssignment);
    }
    
    public MappingStatus create(IssueExtAssignment issueExtAssignment){
        if(issueExtAssignmentFacade.checkMappingByIssueId(issueExtAssignment)){
            return MappingStatus.EXIST;
        }
        try{
        issueExtAssignmentFacade.create(issueExtAssignment);
        }catch (Exception e){
            return MappingStatus.ERROR;
        }
        return MappingStatus.SUCCESS;
    }
    
    public DeleteMappingStatus delete(IssueExtAssignment issueExtAssignment){
        if(!issueExtAssignmentFacade.checkMappingByIssueId(issueExtAssignment)){
            return DeleteMappingStatus.ERROR_NOT_FOUND;
        }
        try{
        IssueExtAssignment prepareIssueExtAssignment = issueExtAssignmentFacade.getMappingByIssueId(issueExtAssignment);
        issueExtAssignmentFacade.remove(prepareIssueExtAssignment);
        }catch (Exception e){
            return DeleteMappingStatus.ERROR;
        }
        return DeleteMappingStatus.SUCCESS;
    }
    
    public UpdateMappingStatus update(IssueExtAssignment issueExtAssignment){
        if(!issueExtAssignmentFacade.checkMappingByIssueId(issueExtAssignment)){
            return UpdateMappingStatus.ERROR_NOT_FOUND;
        }
        try{
        IssueExtAssignment prepareIssueExtAssignment = issueExtAssignmentFacade.getMappingByIssueId(issueExtAssignment);
        issueExtAssignmentFacade.edit(prepareIssueExtAssignment);
        }catch (Exception e){
            return UpdateMappingStatus.ERROR;
        }
        return UpdateMappingStatus.SUCCESS;
    }
}
