package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
@Repository(value = "vehicleDao")
public class VehicleDaoImpl implements VehicleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Vehicle> getAll() throws DataAccessException {
        try {
            List<Vehicle> result = new ArrayList<>();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehicle.class));
            Query q = em.createQuery(cq);
            result = q.getResultList();
            return result;
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while getting all vehicles",e);
        }
    }

    @Override
    public Vehicle get(long id) throws DataAccessException {
        try {
            Vehicle result = null;
            result = em.find(Vehicle.class, id);
            return result;
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while getting vehicle by id",e);
        }
    }

    @Override
    public void update(Vehicle vehicle) throws DataAccessException {
        try {
            em.merge(vehicle);
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while updating vehicle",e);
        }
    }

    @Override
    public void delete(Vehicle vehicle) throws DataAccessException {
        try {
            Vehicle remove = em.getReference(Vehicle.class, vehicle.getVin());
            em.remove(remove);
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while deleting vehicle", e);
        }
    }

    @Override
    public void create(Vehicle vehicle) throws DataAccessException {
        try {
            em.persist(vehicle);
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while creeating vehicle",e);
        }
    }
}
