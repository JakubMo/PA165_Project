package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
public class VehicleDaoImpl implements VehicleDao {

	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		vehicles = session.createCriteria(Vehicle.class).list();

		session.getTransaction().commit();

		return vehicles;
	}

	public Vehicle getVehicle(String vin) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		Vehicle vehicle = session.get(Vehicle.class, vin);

		session.getTransaction().commit();

		return vehicle;
	}

	public void updateVehicle(Vehicle vehicle) {
		if(vehicle == null){
			throw new IllegalArgumentException("Vehicle is null!");
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.update(vehicle);

		session.getTransaction().commit();
	}

	public void deleteVehicle(Vehicle vehicle) {
		if(vehicle == null){
			throw new IllegalArgumentException("Vehicle is null!");
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.delete(vehicle);

		session.getTransaction().commit();
	}

	public void insertVehicle(Vehicle vehicle) {
		if(vehicle == null){
			throw new IllegalArgumentException("Vehicle is null!");
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.save(vehicle);

		session.getTransaction().commit();
	}
}
