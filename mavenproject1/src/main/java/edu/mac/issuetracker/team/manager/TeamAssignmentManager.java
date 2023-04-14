/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.team.manager;

import edu.mac.issuetracker.team.data.TeamAssignmentFacade;
import edu.mac.issuetracker.team.entity.TeamAssignment;
import edu.mac.issuetracker.team.outputInfo.MappingStatus;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class TeamAssignmentManager {
    
    @Inject
    private TeamAssignmentFacade teamAssignmentFacade;
    
//    public MappingStatus create(TeamAssignment teamAssignment){
//        if(!teamAssignmentFacade.mappingExist(teamAssignment)){
//            try{
//                ownershipAssignmentFacade.create(ownershipAssignment);
//                return MappingStatus.SUCCESS;
//            } catch (Exception e){
//                return MappingStatus.FAILURE;
//            }
//        }else{
//            return MappingStatus.EXIST;
//        }
//    }
    
}
