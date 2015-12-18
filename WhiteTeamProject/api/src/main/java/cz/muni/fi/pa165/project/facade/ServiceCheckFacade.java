package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.Date;
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
    void createServiceCheck(ServiceCheckCreateDTO s) throws DataAccessException;
    
    /**
     * Deletes given ServiceCheck
     * 
     * @param checkId id of service check to be deleted
     * @throws DataAccessExceptionImpl 
     */
    void deleteServiceCheck(Long checkId) throws DataAccessException;
    
    /**
     * Updates the report of service check
     * 
     * @param checkId id of service check to be updated 
     * @param report text of report
     * @throws DataAccessExceptionImpl 
     */
    void updateReport(Long checkId, String report) throws DataAccessException;

	/**
	 * Updates the service check's date
	 *
	 * @param checkId id of service check to be updated
	 * @param serviceCheckDate planned date of service check
	 * @throws DataAccessExceptionImpl
	 */
	void updateServiceCheckDate(Long checkId, Date serviceCheckDate) throws DataAccessException;


	/**
	 * Updates the service employee, who checked the vehicle
	 * @param checkId id of service check to be updated
	 * @param serviceEmployee name of the service employee
	 * @throws DataAccessExceptionImpl
	 */
	void updateServiceEmployee(Long checkId, String serviceEmployee) throws DataAccessException;

	/**
	 * Updates the service check's status
	 * @param checkId id of service check to be updated
	 * @param status service check's new status
	 * @throws DataAccessExceptionImpl
	 */
	void updateServiceCheckStatus(Long checkId, ServiceCheckStatus status) throws DataAccessException;

	/**
	 * Gets service check by id
	 *
	 * @param id service check id
	 * @return service check with given id
	 * @throws DataAccessExceptionImpl
	 */
	ServiceCheckDTO get(Long id) throws DataAccessException;

    /**
     * Gets all service checks
     * 
     * @return collection of all service checks
     * @throws DataAccessExceptionImpl 
     */
    Collection<ServiceCheckDTO> getAll() throws DataAccessException;
    
    /**
     * Gets all service checks for single vehicle
     * 
     * @param vehicleDTO vehicle which service checks to be found
     * @return service checks for vehicle
     * @throws DataAccessExceptionImpl 
     */
    Collection<ServiceCheckDTO> getAllByVehicle(VehicleDTO vehicleDTO) throws DataAccessException;

	/**
	 * Gets all service checks with given status
	 * @param status status of service checks to be found
	 * @return collection of all service checks with given status
	 * @throws DataAccessExceptionImpl
	 */
	Collection<ServiceCheckDTO> getAllByServiceCheckStatus(ServiceCheckStatus status) throws DataAccessException;

	/**
	 * Gets all service checks with given time interval.
	 * @param startDate start date of interval
	 * @param endDate end date of interval
	 * @return collection of all service checks with given interval
	 * @throws DataAccessException
	 */
	Collection<ServiceCheckDTO> getAllByTimeInterval(Date startDate, Date endDate) throws DataAccessException;
}
