package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.Date;
import java.util.List;
/**
 * ServiceCheck facade interface
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public interface ServiceCheckFacade {
    /**
     * Create new vehicle.
     * 
     * @param s service check to be created
     * @throws DataAccessExceptionImpl
     */
    public void register(ServiceCheckCreateDTO s) throws DataAccessExceptionImpl;
    
    /**
     * Deletes given ServiceCheck
     * 
     * @param checkId id of service check to be deleted
     * @throws DataAccessExceptionImpl 
     */
    public void delete(Long checkId) throws DataAccessExceptionImpl;
    
    /**
     * Updates the report of service check
     * 
     * @param checkId id of service check to be updated 
     * @param report text of report
     * @throws DataAccessExceptionImpl 
     */
    public void updateReport(Long checkId, String report) throws DataAccessExceptionImpl;

	/**
	 * Updates the service check's date
	 *
	 * @param checkId id of service check to be updated
	 * @param serviceCheckDate service check's date
	 * @throws DataAccessExceptionImpl
	 */
	public void updateServiceCheckDate(Long checkId, Date serviceCheckDate) throws DataAccessExceptionImpl;


	/**
	 * Updates the service employee, who checked the vehicle
	 * @param checkId id of service check to be updated
	 * @param serviceEmployee name of the service employee
	 * @throws DataAccessExceptionImpl
	 */
	public void updateServiceEmployee(Long checkId, String serviceEmployee) throws DataAccessExceptionImpl;

	/**
	 * Updates the service check's status
	 * @param checkId id of service check to be updated
	 * @param status service check's new status
	 * @throws DataAccessExceptionImpl
	 */
	public void updateServiceCheckStatus(Long checkId, ServiceCheckStatus status) throws DataAccessExceptionImpl;

    /**
     * Get all service checks
     * 
     * @return List of service checks
     * @throws DataAccessExceptionImpl 
     */
    public List<ServiceCheckDTO> getAll() throws DataAccessExceptionImpl;
    
    /**
     * Get all service checks for single vehicle
     * 
     * @param vehicleId the id of vehicle
     * @return service checks for vehicle
     * @throws DataAccessExceptionImpl 
     */
    public List<ServiceCheckDTO> getAllByVehicle(Long vehicleId) throws DataAccessExceptionImpl;

	/**
	 * Get all service checks with given status
	 * @param status status of service checks to be found
	 * @return found service checks
	 * @throws DataAccessExceptionImpl
	 */
	public List<ServiceCheckDTO> getAllByServiceCheckStatus(ServiceCheckStatus status) throws DataAccessExceptionImpl;
}
