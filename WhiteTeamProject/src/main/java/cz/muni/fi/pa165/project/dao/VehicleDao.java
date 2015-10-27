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
	List<Vehicle> getAllVehicles();

	/**
	 * Get vehicle by id
	 *
	 * @param vin vehicle's vin
	 * @return vehicle
	 */
	Vehicle getVehicle(String vin);

	/**
	 * Update vehicle
	 * @param vehicle updated vehicle
	 */
	void updateVehicle(Vehicle vehicle);

	/**
	 * Remove vehicle from DB
	 * @param vehicle vehicle to remove
	 */
	void deleteVehicle(Vehicle vehicle);

	/**
	 * Insert new vehicle
	 * @param vehicle vehicle to insert
	 */
	void insertVehicle(Vehicle vehicle);
}
