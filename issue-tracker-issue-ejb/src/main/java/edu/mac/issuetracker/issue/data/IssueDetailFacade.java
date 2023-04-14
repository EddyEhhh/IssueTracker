package edu.mac.issuetracker.issue.data;

import edu.mac.issuetracker.issue.entity.IssueDetail;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class IssueDetailFacade extends AbstractFacade<IssueDetail> {
    
    @PersistenceContext(unitName = "issuePU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public IssueDetailFacade() {
        super(IssueDetail.class);
    }
}
