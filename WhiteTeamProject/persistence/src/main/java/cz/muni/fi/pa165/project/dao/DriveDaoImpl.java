package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;

/**
 * Provides implementation of {@link DriveDao} interface.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/27/2015
 */
@Repository
public class DriveDaoImpl implements DriveDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("drive is null");
        }
        
        try {
            em.persist(drive);
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while creating new drive", ex);
        }
    }
    
    @Override
    public void update(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("drive is null");
        }
        
        try {
            em.merge(drive);
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while updating drive", ex);
        }
    }
    
    @Override
    public List<Drive> getAll() throws DataAccessException {
        try {
            List<Drive> result = new ArrayList<>();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Drive.class));
            Query q = em.createQuery(cq);
            result = q.getResultList();
            return result;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting all drives", ex);
        }
    }
    
    @Override
    public List<Drive> getAllByVehicle(Vehicle vehicle) throws DataAccessException {
        if(vehicle == null) {
            throw new IllegalArgumentException("vehicle is null");
        }
        
        try {
            List<Drive> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT c "
                    + "FROM cz.muni.fi.pa165.project.entity.Drive c "
                    + "WHERE c.vehicle = :vehicle");
            q.setParameter("vehicle", vehicle);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting drives by vehicle", ex);
        }
    }
    
    @Override
    public List<Drive> getAllByEmployee(Employee employee) throws DataAccessException {
        if(employee == null) {
            throw new IllegalArgumentException("employee is null");
        }
        
        try {
            List<Drive> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT c "
                    + "FROM cz.muni.fi.pa165.project.entity.Drive c "
                    + "WHERE c.employee = :employee");
            q.setParameter("employee", employee);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting all by employee", ex);
        }
    }
    
    @Override
    public List<Drive> getAllDrivesByTimeInterval(Date startDate, Date endDate) throws DataAccessException {
        try{
            List<Drive> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT c "
                    + "FROM cz.muni.fi.pa165.project.entity.Drive c "
                    + "WHERE (c.endDate >= :startDate AND c.startDate <= :endDate) "
                    + "AND (c.driveStatus = :status)");
            q.setParameter("startDate", startDate, TemporalType.TIMESTAMP);
            q.setParameter("endDate", endDate, TemporalType.TIMESTAMP);
            q.setParameter("status", DriveStatus.APPROVED);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting all by time interval", ex);
        }
    }
    
    @Override
    public Drive get(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        try {
            Drive result = null;
            result = em.find(Drive.class, id);
            return result;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting drive by id", ex);
        }
    }
    
    @Override
    public void delete(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("drive is null");
        }
        
        try {
            Drive remove = em.getReference(Drive.class, drive.getId());
            em.remove(remove);
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while deleting drive", ex);
        }
    }
}
