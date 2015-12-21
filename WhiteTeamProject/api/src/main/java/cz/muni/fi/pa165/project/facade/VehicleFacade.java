package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import java.util.Date;

import java.util.List;

/**
 * Vehicle facade interface.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public interface VehicleFacade {
    
    /**
     * Creates new vehicle
     * @param v vehicle to be created
     * @throws DataAccessExceptionImpl 
     */
    public void createVehicle(VehicleCreateDTO v) throws DataAccessExceptionImpl;
    
    /**
     * Updates service check interval for vehicle.
     * @param vehicleId vehicle to update
     * @param interval new service check interval interval (max. 12 months)
     * @throws DataAccessExceptionImpl 
     */
    public void updateServiceCheckInterval(Long vehicleId, int interval) throws DataAccessExceptionImpl;
    
    /**
     * Updates maximum mileage for vehicle.
     * @param vehicleId vehicle to update
     * @param max new maximum mileage
     * @throws DataAccessExceptionImpl 
     */
    public void updateMaxMileage(Long vehicleId, Long max) throws DataAccessExceptionImpl;
    
    /**
     * Deletes vehicle.
     * @param id id of vehicle to be deleted
     * @throws DataAccessExceptionImpl 
     */
    public void deleteVehicle(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles by model 
     * @param model model of vehicle to find
     * @return List of found found vehicle
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByModel(String model) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles by brand
     * @param brand manufacturer of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByBrand(String brand) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicles with lower mileage than given 
     * @param mileage max mileage of vehicle to find
     * @return List of found vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllByMileage(Long mileage) throws DataAccessExceptionImpl;
    
    /**
     * Gets all vehicles
     * @return List of all vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAll() throws DataAccessExceptionImpl;
    
    /**
     * Finds all free vehicles for specified time interval
     * @param startDate from when the vehicle is needed
     * @param endDate until when the vehicle is needed
     * @return List of free vehicles
     * @throws DataAccessExceptionImpl 
     */
    public List<VehicleDTO> getAllFreeInDate(Date startDate, Date endDate) throws DataAccessExceptionImpl;
    
    /**
     * Finds vehicle by given ID.
     * 
     * @param id vehicle ID
     * @return vehicle with given ID
     * @throws DataAccessExceptionImpl 
     */
    public VehicleDTO getById(Long id) throws DataAccessExceptionImpl;
    
    /**
     * Find vehicle by given VIN.
     * 
     * @param vin vehicle VIN
     * @return vehicle with given VIN
     * @throws DataAccessExceptionImpl 
     */
    public VehicleDTO getByVin(String vin) throws DataAccessExceptionImpl;
}
