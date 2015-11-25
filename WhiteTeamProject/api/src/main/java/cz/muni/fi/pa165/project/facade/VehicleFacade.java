package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.List;

/**
 * Vehicle facade interface.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public interface VehicleFacade {
    
    /**
     * Create new vehicle
     * 
     * @param v vehicle to be created
     * @throws DataAccessExceptionImpl 
     */
    public void createVehicle(VehicleCreateDTO v) throws DataAccessExceptionImpl;
    
    /**
     * Updates vin for selected vehicle
     * 
     * @param vehicleId vehicle to update
     * @param vin new VIN, has to be 17 characters long!
     * @throws DataAccessExceptionImpl 
     */
    public void updateVin(Long vehicleId, String vin) throws DataAccessExceptionImpl;
    
    /**
     * Updates service check interval for vehicle.
     * 
     * @param vehicleId vehicle to update
     * @param interval new service check interval interval
     * @throws DataAccessExceptionImpl 
     */
    public void updateServiceCheckInterval(Long vehicleId, int interval) throws DataAccessExceptionImpl;
    
    /**
     * Updates maximum mileage for vehicle.
     * 
     * @param vehicleId vehicle to update
     * @param max new maximum mileage
     * @throws DataAccessExceptionImpl 
     */
    public void updateMaxMileage(Long vehicleId, Long max) throws DataAccessExceptionImpl;
    
    /**
     * Updates vehicle mileage.
     * 
     * @param vehicleId vehicle to update
     * @param mileage new mileage
     * @throws DataAccessExceptionImpl 
     */
    public void updateMileage(Long vehicleId, Long mileage) throws DataAccessExceptionImpl;
    
    /**
     * Deletes vehicle.
     * 
     * @param id id of vehicle to be deleted
     * @throws DataAccessExceptionImpl 
     */
    public void deleteVehicle(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle by Id
     * 
     * @param id id of vehicle to find
     * @return found vehicle
     * @throws DataAccessExceptionImpl 
     */
    public VehicleDTO getVehicleById(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle by vin
     * 
     * @param vin vin of vehicle to find
     * @return found vehicle
     * @throws DataAccessExceptionImpl 
     */
    public VehicleDTO getVehicleByVin(String vin) throws DataAccessExceptionImpl;
    
    /**
     * Get all vehicles
     * 
     * @return List of all vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllVehicles() throws DataAccessExceptionImpl;
}
