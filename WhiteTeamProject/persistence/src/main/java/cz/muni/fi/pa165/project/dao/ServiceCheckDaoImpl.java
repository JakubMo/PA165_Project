package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.dao.DataAccessException;
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
    public void create(ServiceCheck serviceCheck) throws DataAccessException {
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            em.persist(serviceCheck);
        }
        catch(Exception ex) {
            throw new DataAccessExceptionImpl("error while creating service check", ex);
        }
    }

    @Override
    public void update(ServiceCheck serviceCheck) throws DataAccessException {        
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            em.merge(serviceCheck);
        }
        catch(Exception ex) {
            throw new DataAccessExceptionImpl("error while updating service check", ex);
        }
    }

    @Override
    public void delete(ServiceCheck serviceCheck) throws DataAccessException {
        if(serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        
        try {
            ServiceCheck remove = em.getReference(ServiceCheck.class, serviceCheck.getId());
            em.remove(remove);
        }
        catch(Exception ex) {
            throw new DataAccessExceptionImpl("error while deleting service check", ex);
        }
    }

    @Override
    public ServiceCheck get(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        try {
            ServiceCheck result = em.find(ServiceCheck.class, id);
            return result;
        }
        catch(Exception ex) {
            throw new DataAccessExceptionImpl("error while getting service check by id", ex);
        }
    }

    @Override
    public List<ServiceCheck> getAll() throws DataAccessException {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceCheck.class));
            Query q = em.createQuery(cq);
            List<ServiceCheck> result = q.getResultList();
            return result;
        }
        catch(Exception ex) {
            throw new DataAccessExceptionImpl("error while getting all service checks", ex);
        }
    }    

    @Override
    public List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws DataAccessException {
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
            throw new DataAccessExceptionImpl("error while getting service checks by vehicle", ex);
        }
    }

    @Override
    public List<ServiceCheck> getAllByTimeInterval(Date startDate, Date endDate) throws DataAccessException {
        if(startDate == null){
            throw new IllegalArgumentException("startDate is null!");
        }
		if(endDate == null){
			throw new IllegalArgumentException("endDate is null!");
		}
		if(endDate.before(startDate)){
			throw new IllegalArgumentException("endDate is before startDate!");
		}

		try{
			List<ServiceCheck> result = new ArrayList<>();
			Query q = this.em.createQuery(
					"SELECT c FROM cz.muni.fi.pa165.project.entity.ServiceCheck c " +
							"WHERE (c.serviceCheckDate >= :startDate AND c.serviceCheckDate <= :endDate)");
			q.setParameter("startDate", startDate, TemporalType.DATE);
			q.setParameter("endDate", endDate, TemporalType.DATE);
			result = q.getResultList();
			return result;
		} catch (Exception ex){
			throw new DataAccessExceptionImpl("error while getting service checks in time interval", ex);
		}
    }

	@Override
	public List<ServiceCheck> getAllByStatus(ServiceCheckStatus status) throws DataAccessException {
		if(status == null){
			throw new IllegalArgumentException("status is null!");
		}

		try{
			List<ServiceCheck> result = new ArrayList<>();
			Query q = this.em.createQuery("SELECT c FROM cz.muni.fi.pa165.project.entity.ServiceCheck c " +
					"WHERE c.status = :status");
			q.setParameter("status", status);
			result = q.getResultList();
			return result;
		} catch (Exception e){
			throw new DataAccessExceptionImpl("error while getting service checks with given status", e);
		}
	}
}
