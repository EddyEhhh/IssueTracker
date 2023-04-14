/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issueExtAssignment.data;

import edu.mac.issuetracker.issueExtAssignment.entity.IssueExtAssignment;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author tohyu
 */
@Stateless
public class IssueExtAssignmentFacade extends AbstractFacade<IssueExtAssignment> {
     @PersistenceContext(unitName = "issueExtAssignmentPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public IssueExtAssignmentFacade(){
        super(IssueExtAssignment.class);
    }
    
    public IssueExtAssignment getMappingByIssueId(IssueExtAssignment issueExtAssignment){
        long issueId = issueExtAssignment.getIssueId();
        return getEntityManager().createNamedQuery("IssueExtAssignment.byIssueId", IssueExtAssignment.class)
                .setParameter("issueId", issueId)
                .getSingleResult();
    }
    
    public boolean checkMappingByIssueId(IssueExtAssignment issueExtAssignment){
        long issueId = issueExtAssignment.getIssueId();
        return !getEntityManager().createNamedQuery("IssueExtAssignment.byIssueId", IssueExtAssignment.class)
                .setParameter("issueId", issueId)
                .getResultList().isEmpty();
    }
    
    public List<IssueExtAssignment> getAllMappingByCreatedById(IssueExtAssignment issueExtAssignment){
        long createdById = issueExtAssignment.getCreatedById();
        return getEntityManager().createNamedQuery("IssueExtAssignment.byCreatedById", IssueExtAssignment.class)
                .setParameter("createdById", createdById)
                .getResultList();
    }
    
//    public List<OwnershipAssignment> getAllMappingByPrimaryOnly(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        return getEntityManager().createNamedQuery("OwnershipAssignment.byPrimaryOnly", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .getResultList();
//    }
//    
//    public int countAllMappingByPrimaryId(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        return getEntityManager().createNamedQuery("OwnershipAssignment.byPrimaryId", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .setParameter("secondaryType", secondaryType)
//                .getResultList().size();
//    }
//    
//    public List<OwnershipAssignment> getAllMappingBySecondaryId(OwnershipAssignment ownershipAssignment){
//        String primaryType = ownershipAssignment.getPrimaryType();
//        long secondaryId = ownershipAssignment.getSecondaryId();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        
//        return getEntityManager().createNamedQuery("OwnershipAssignment.bySecondaryId", OwnershipAssignment.class)
//                .setParameter("secondaryId", secondaryId)
//                .setParameter("secondaryType", secondaryType)
//                .setParameter("primaryType", primaryType)
//                .getResultList();
//    }
//    
//    public int countAllMappingBySecondaryId(OwnershipAssignment ownershipAssignment){
//        String primaryType = ownershipAssignment.getPrimaryType();
//        long secondaryId = ownershipAssignment.getSecondaryId();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        
//        return getEntityManager().createNamedQuery("OwnershipAssignment.bySecondaryId", OwnershipAssignment.class)
//                .setParameter("secondaryId", secondaryId)
//                .setParameter("secondaryType", secondaryType)
//                .setParameter("primaryType", primaryType)
//                .getResultList().size();
//    }
    
}
