package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;

import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
public interface VehicleDao {

	/**
	 * List all vehicles
	 * @return list with vehicles
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
	 */
	List<Vehicle> findAll() throws HibernateErrorException;

	/**
	 * Get vehicle by id
	 *
	 * @param vin vehicle's vin
	 * @return vehicle
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
	 */
	Vehicle findByVin(String vin) throws HibernateErrorException;

	/**
	 * Update vehicle
	 * @param vehicle vehicle to be updated
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
	 */
	void update(Vehicle vehicle) throws HibernateErrorException;

	/**
	 * Remove vehicle
	 * @param vehicle vehicle to be removed
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
	 */
	void delete(Vehicle vehicle) throws HibernateErrorException;

	/**
	 * Insert new vehicle
	 * @param vehicle vehicle to be inserted
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
	 */
	void insert(Vehicle vehicle) throws HibernateErrorException;
}
