package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 * Provides implementation of {@link ServiceCheckDao} interface.
 *
 * @author Marek
 */
@Repository(value = "serviceCheckDao")
public class ServiceCheckDaoImpl implements ServiceCheckDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(ServiceCheck serviceCheck) throws HibernateErrorException {
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            em.persist(serviceCheck);
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void update(ServiceCheck serviceCheck) throws HibernateErrorException {        
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            em.merge(serviceCheck);
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void delete(ServiceCheck serviceCheck) throws HibernateErrorException {
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            ServiceCheck remove = em.getReference(ServiceCheck.class, serviceCheck.getId());
            em.remove(remove);
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public ServiceCheck get(Long id) throws HibernateErrorException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        try {
            ServiceCheck result = em.find(ServiceCheck.class, id);
            return result;
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public List<ServiceCheck> getAll() throws HibernateErrorException {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceCheck.class));
            Query q = em.createQuery(cq);
            List<ServiceCheck> result = q.getResultList();
            return result;
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }    

    @Override
    public List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws HibernateErrorException {
        if(vehicle == null) {
            throw new IllegalArgumentException("vehicle is null");
        }
        
        try {
            Query q = em.createQuery("SELECT c FROM ServiceCheck c WHERE c.vehicle = :vehicle");
            q.setParameter("vehicle", vehicle);
            List<ServiceCheck> result = q.getResultList();
            return result;
        }
        catch(Exception ex) {
            throw new HibernateErrorException(ex);
        }
    }
}
