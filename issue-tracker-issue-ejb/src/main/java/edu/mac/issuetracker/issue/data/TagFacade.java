package edu.mac.issuetracker.issue.data;

import edu.mac.issuetracker.issue.entity.Tag;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class TagFacade extends AbstractFacade<Tag> {

    @PersistenceContext(unitName = "issuePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }

    public Tag getTagByTitle(String title) {
        return getEntityManager().createNamedQuery("Tag.byTitle", Tag.class)
                .setParameter("title", title)
                .getSingleResult();
    }
}
