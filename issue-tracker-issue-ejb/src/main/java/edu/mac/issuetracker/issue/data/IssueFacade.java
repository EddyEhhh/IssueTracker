package edu.mac.issuetracker.issue.data;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.entity.Issue;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class IssueFacade extends AbstractFacade<Issue> {
    
    @Inject
    private IssueManager issueManager;
    
    @PersistenceContext(unitName = "issuePU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public IssueFacade() {
        super(Issue.class);
    }
            
    public List<Issue> getAllIssue(boolean isArchive){
        return getEntityManager().createNamedQuery("Issue.all", Issue.class)
                .setParameter("isArchive", isArchive)
                .getResultList();
    }
    
    public List<Issue> getIssueByStatus(String status){
        return getEntityManager().createNamedQuery("Issue.byStatus", Issue.class)
                .setParameter("status", status)
                .setParameter("isArchive", false)
                .getResultList();
    }
    
    //StaffFacades
    public Issue getIssue(String uuid){
        return getEntityManager().createNamedQuery("Issue.byUuid", Issue.class)
                 .setParameter("uuid", uuid)
                 .getSingleResult();
    }
}
