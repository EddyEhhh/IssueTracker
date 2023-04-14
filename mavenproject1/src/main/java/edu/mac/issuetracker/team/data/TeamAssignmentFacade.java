/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.team.data;

import edu.mac.issuetracker.team.entity.TeamAssignment;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author tohyu
 */
@Stateless
public class TeamAssignmentFacade extends AbstractFacade<TeamAssignment> {
     @PersistenceContext(unitName = "ownershipAssignmentPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public TeamAssignmentFacade(){
        super(TeamAssignment.class);
    }
    
//    public OwnershipAssignment getOwnershipAssignment(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        long secondaryId = ownershipAssignment.getSecondaryId();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        return getEntityManager().createNamedQuery("OwnershipAssignment.byAll", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .setParameter("secondaryId", secondaryId)
//                .setParameter("secondaryType", secondaryType)
//                .getSingleResult();
//    }
//    
//    public boolean mappingExist(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        long secondaryId = ownershipAssignment.getSecondaryId();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        
//        return !getEntityManager().createNamedQuery("OwnershipAssignment.byAll", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .setParameter("secondaryId", secondaryId)
//                .setParameter("secondaryType", secondaryType)
//                .getResultList().isEmpty();
//    }
//    
//    public OwnershipAssignment getMappingByPrimaryId(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        return getEntityManager().createNamedQuery("OwnershipAssignment.byPrimaryId", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .setParameter("secondaryType", secondaryType)
//                .getSingleResult();
//    }
//    
//    public OwnershipAssignment getMappingBySecondaryId(OwnershipAssignment ownershipAssignment){
//        String primaryType = ownershipAssignment.getPrimaryType();
//        long secondaryId = ownershipAssignment.getSecondaryId();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        
//        return getEntityManager().createNamedQuery("OwnershipAssignment.bySecondaryId", OwnershipAssignment.class)
//                .setParameter("secondaryId", secondaryId)
//                .setParameter("secondaryType", secondaryType)
//                .setParameter("primaryType", primaryType)
//                .getSingleResult();
//    }
//    
//    public List<OwnershipAssignment> getAllMappingByPrimaryId(OwnershipAssignment ownershipAssignment){
//        long primaryId = ownershipAssignment.getPrimaryId();
//        String primaryType = ownershipAssignment.getPrimaryType();
//        String secondaryType = ownershipAssignment.getSecondaryType();
//        return getEntityManager().createNamedQuery("OwnershipAssignment.byPrimaryId", OwnershipAssignment.class)
//                .setParameter("primaryId", primaryId)
//                .setParameter("primaryType", primaryType)
//                .setParameter("secondaryType", secondaryType)
//                .getResultList();
//    }
//    
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
