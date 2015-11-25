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
	 * List all vehicles by certain model.
	 * @param model model of vehicles to find, if null or empty, all vehicles
	 * are returned
	 * @return list with found vehicles
     * @throws DataAccessException
	 */
	List<Vehicle> getAllByModel(String model) throws DataAccessException;
	
	/**
	 * List all vehicles by manufacturer
	 * @param brand manufacturer of vehicles to find, if null or empty, all vehicles
	 * are returned
	 * @return list with found vehicles
     * @throws DataAccessException
	 */
	List<Vehicle> getAllByBrand(String brand) throws DataAccessException;
	
	/**
	 * List all vehicles with lower mileage than given
	 * @param mileage max mileage of vehicles to find, if null or equal to 0, all vehicles
	 * are returned
	 * @return list with found vehicles
     * @throws DataAccessException
	 */
	List<Vehicle> getAllByMileage(Long mileage) throws DataAccessException;
	
	/**
	 * Get vehicle by id
	 *
	 * @param id - vehicle's id
	 * @return vehicle
         * @throws DataAccessException
	 */
	Vehicle get(long id) throws DataAccessException;

        /**
	 * Get vehicle by VIN
	 *
	 * @param vin - vehicle's VIN
	 * @return vehicle
         * @throws DataAccessException
	 */
	Vehicle getByVin(String vin) throws DataAccessException;
        
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
