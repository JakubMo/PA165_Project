package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Vehicle;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
public interface VehicleDao {

	/**
	 * List all vehicles
	 * @return list with vehicles
     * @throws DataAccessException
	 */
	List<Vehicle> getAll() throws DataAccessException;

	/**
	 * Get vehicle by id
	 *
	 * @param id - vehicle's id
	 * @return vehicle
     * @throws DataAccessException
	 */
	Vehicle get(long id) throws DataAccessException;

	/**
	 * Update vehicle
	 * @param vehicle vehicle to be updated
     * @throws DataAccessException
	 */
	void update(Vehicle vehicle) throws DataAccessException;

	/**
	 * Remove vehicle
	 * @param vehicle vehicle to be removed
     * @throws DataAccessException
	 */
	void delete(Vehicle vehicle) throws DataAccessException;

	/**
	 * Insert new vehicle
	 * @param vehicle vehicle to be inserted
     * @throws DataAccessException
	 */
	void create(Vehicle vehicle) throws DataAccessException;
}
