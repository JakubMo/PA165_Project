package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import java.util.Date;
import java.util.List;

/**
 * An interface that defines a service access to the {@link Vehicle} entity.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/24/2015
 */
public interface VehicleService {
    
    /**
     * Creates new vehicle
     * @param vehicle vehicle to be created
     * @throws DataAccessExceptionImpl 
     */
    public void createVehicle(Vehicle vehicle) throws DataAccessExceptionImpl;
    
    /**
     * Deletes vehicle.
     * @param vehicle vehicle to be deleted
     * @throws DataAccessExceptionImpl 
     */
    public void deleteVehicle(Vehicle vehicle) throws DataAccessExceptionImpl;
    
    /**
     * Updates service check interval for vehicle.
     * @param vehicle vehicle to update
     * @param interval new service check interval interval (max 12 months)
     * @throws DataAccessExceptionImpl 
     */
    public void updateServiceCheckInterval(Vehicle vehicle, int interval) throws DataAccessExceptionImpl;
    
    /**
     * Updates maximum mileage for vehicle.
     * 
     * @param vehicle vehicle to update
     * @param maxMileage new maximum mileage (min 100 000.)
     * @throws DataAccessExceptionImpl 
     */
    public void updateMaxMileage(Vehicle vehicle, long maxMileage) throws DataAccessExceptionImpl;
    
    /**
     * Updates driven mileage of vehicle
     * @param vehicle vehicle to update
     * @param mileage recently driven mileage. This value will be summed to vehicle's mileage.
     * @throws DataAccessExceptionImpl 
     */
    public void updateMileage(Vehicle vehicle, long mileage) throws DataAccessExceptionImpl;
    
    /**
     * Gets vehicle by ID
     * @param id id of vehicle to find
     * @return found vehicle entity
     * @throws DataAccessExceptionImpl 
     */
    public Vehicle getById(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Gets vehicle by VIN
     * @param vin vin of vehicle to find
     * @return found vehicle entity
     * @throws DataAccessExceptionImpl 
     */
    public Vehicle getByVin(String vin) throws DataAccessExceptionImpl;
    
    /**
     * Gets all vehicles
     * @return List of all vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<Vehicle> getAll() throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles by model
     * @param model model of vehicle to find
     * @return List of found found vehicle
     * @throws DataAccessExceptionImpl
     */
    public List<Vehicle> getAllByModel(String model) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles by brand
     * @param brand manufacturer of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<Vehicle> getAllByBrand(String brand) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles with lower mileage than given
     * @param mileage max mileage of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<Vehicle> getAllByMileage(Long mileage) throws DataAccessExceptionImpl;
    
    /**
     * Finds all free vehicles for specified time interval
     * @param startDate from when the vehicle is needed
     * @param endDate until when the vehicle is needed
     * @return List of free vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<Vehicle> getAllFreeInDate(Date startDate, Date endDate) throws DataAccessExceptionImpl;
    
}
