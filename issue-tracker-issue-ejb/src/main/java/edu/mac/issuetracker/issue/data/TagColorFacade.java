/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.data;

import edu.mac.issuetracker.issue.entity.TagColor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Sam
 */
@Stateless
public class TagColorFacade extends AbstractFacade<TagColor>{
        
    @PersistenceContext(unitName = "issuePU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TagColorFacade() {
        super(TagColor.class);
    }
}
