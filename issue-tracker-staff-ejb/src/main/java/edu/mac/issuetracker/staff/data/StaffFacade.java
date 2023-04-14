package edu.mac.issuetracker.staff.data;

import edu.mac.issuetracker.staff.entity.Staff;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Jaibun Thana
 */
@Stateless
public class StaffFacade extends AbstractFacade<Staff> {
    
    @PersistenceContext(unitName = "staffPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public StaffFacade() {
        super(Staff.class);
    }
    
    //StaffFacades
    public Staff getStaff(String email){
        return getEntityManager().createNamedQuery("Staff.byEmail", Staff.class)
                 .setParameter("email", email)
                 .getSingleResult();
    }
    
    //Returns if email exist
    public boolean emailExist(String email){
        return !getEntityManager().createNamedQuery("Staff.byEmail", Staff.class)
                 .setParameter("email", email)
                 .getResultList().isEmpty();
    }
    
}
