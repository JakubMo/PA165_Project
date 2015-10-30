package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
public class VehicleDaoImpl implements VehicleDao {

    public List<Vehicle> findAll() throws HibernateErrorException {
        try {
            List<Vehicle> vehicles;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            vehicles = session.createCriteria(Vehicle.class).list();

            session.getTransaction().commit();

            return vehicles;
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    public Vehicle findByVin(String vin) throws HibernateErrorException {
        if (vin == null) {
            throw new IllegalArgumentException("Vin is null!");
        }
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            Vehicle vehicle = (Vehicle) session.get(Vehicle.class, vin);

            session.getTransaction().commit();

            return vehicle;
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    public void update(Vehicle vehicle) throws HibernateErrorException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is null!");
        }
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.update(vehicle);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    public void delete(Vehicle vehicle) throws HibernateErrorException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is null!");
        }
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.delete(vehicle);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    public void insert(Vehicle vehicle) throws HibernateErrorException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is null!");
        }
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.save(vehicle);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }
}
