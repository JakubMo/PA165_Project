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
import java.util.Date;
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
            throw new DataAccessExceptionImpl("error while getting all vehicles", e);
        }
    }

    @Override
    public Vehicle get(long id) throws DataAccessException {
        try {
            Vehicle result = null;
            result = em.find(Vehicle.class, id);
            return result;
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while getting vehicle by id", e);
        }
    }

    @Override
    public Vehicle getByVin(String vin) throws DataAccessException {
        if (vin == null || vin.equals("")) {
            return null;
        }
        try {
            Vehicle result = null;
            Query q = em.createQuery("SELECT v "
                    + "FROM Vehicle v "
                    + "WHERE v.vin = :vin");
            q.setParameter("vin", vin);
            result = (Vehicle) q.getSingleResult();
            return result;
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while getting vehicle by vin", e);
        }
    }

    @Override
    public void update(Vehicle vehicle) throws DataAccessException {
        try {
            em.merge(vehicle);
        } catch (Exception e) {
            throw new DataAccessExceptionImpl("error while updating vehicle", e);
        }
    }

    @Override
    public void delete(Vehicle vehicle) throws DataAccessException {
        try {
            Vehicle remove = em.getReference(Vehicle.class, vehicle.getId());
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
            throw new DataAccessExceptionImpl("error while creeating vehicle", e);
        }
    }

    @Override
    public List<Vehicle> getAllByModel(String model) throws DataAccessException {
        if ((model == null) || (model.equals(""))) {
            return getAll();
        }
        try {
            List<Vehicle> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT v "
                    + "FROM Vehicle v "
                    + "WHERE v.model = :model");
            q.setParameter("model", model);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting vehicles by mileage", ex);
        }
    }

    @Override
    public List<Vehicle> getAllByBrand(String brand) throws DataAccessException {
        if ((brand == null) || (brand.equals(""))) {
            return getAll();
        }
        try {
            List<Vehicle> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT v "
                    + "FROM Vehicle v "
                    + "WHERE v.brand = :brand");
            q.setParameter("brand", brand);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting vehicles by mileage", ex);
        }
    }

    @Override
    public List<Vehicle> getAllByMileage(Long mileage) throws DataAccessException {
        try {
            List<Vehicle> results = new ArrayList<>();
            Query q = em.createQuery(
                    "SELECT v "
                    + "FROM Vehicle v "
                    + "WHERE v.mileage <= :mileage");
            q.setParameter("mileage", mileage);
            results = q.getResultList();
            return results;
        } catch (Exception ex) {
            throw new DataAccessExceptionImpl("error while getting vehicles by mileage", ex);
        }
    }

}
