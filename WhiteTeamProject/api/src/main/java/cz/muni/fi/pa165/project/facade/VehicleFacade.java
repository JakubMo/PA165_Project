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
     * Deletes vehicle.
     * 
     * @param id id of vehicle to be deleted
     * @throws DataAccessExceptionImpl 
     */
    public void deleteVehicle(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle by model
     * 
     * @param model model of vehicle to find
     * @return List of found found vehicle
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByModel(String model) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle by brand
     * 
     * @param brand manufacturer of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByBrand(String brand) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle with lower mileage than given
     * 
     * @param mileage max mileage of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByMileage(Long mileage) throws DataAccessExceptionImpl;
    
    /**
     * Get all vehicles
     * 
     * @return List of all vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAll() throws DataAccessExceptionImpl;
}
