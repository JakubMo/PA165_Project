package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;

import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
public interface VehicleDao {

	/**
	 * List all vehicles
	 * @return list with vehicles
	 */
	List<Vehicle> findAll();

	/**
	 * Get vehicle by id
	 *
	 * @param vin vehicle's vin
	 * @return vehicle
	 */
	Vehicle findByVin(String vin);

	/**
	 * Update vehicle
	 * @param vehicle vehicle to be updated
	 */
	void update(Vehicle vehicle);

	/**
	 * Remove vehicle
	 * @param vehicle vehicle to be removed
	 */
	void delete(Vehicle vehicle);

	/**
	 * Insert new vehicle
	 * @param vehicle vehicle to be inserted
	 */
	void insert(Vehicle vehicle);
}
