package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * An service that defines a service access to the {@link ServiceCheck} entity
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 25.11.2015
 */
public interface ServiceCheckService {

	/**
	 * Create new vehicle.
	 * @param serviceCheck service check to be created
	 * @throws DataAccessException
	 */
	void createServiceCheck(ServiceCheck serviceCheck) throws DataAccessException;

	/**
	 * Deletes given ServiceCheck
	 * @param serviceCheck service check to be deleted
	 * @throws DataAccessException
	 */
	void deleteServiceCheck(ServiceCheck serviceCheck) throws DataAccessException;

	/**
	 * Updates the report of service check
	 * @param serviceCheck service check to be updated
	 * @param report text of service check's report
	 * @throws DataAccessException
	 */
	void updateReport(ServiceCheck serviceCheck, String report) throws DataAccessException;

	/**
	 * Updates the service check's date
	 * @param serviceCheck service check to be updated
	 * @param serviceCheckDate planned date of service check
	 * @throws DataAccessException
	 */
	void updateServiceCheckDate(ServiceCheck serviceCheck, Date serviceCheckDate) throws DataAccessException;

	/**
	 * Updates the service employee, who checked the vehicle
	 * @param serviceCheck service check to be updated
	 * @param serviceEmployee service's employee
	 * @throws DataAccessException
	 */
	void updateServiceEmployee(ServiceCheck serviceCheck, String serviceEmployee) throws DataAccessException;

	/**
	 * Updates the service check's status
	 * @param serviceCheck service check to be updated
	 * @param serviceCheckStatus service check's new status
	 * @throws DataAccessException
	 */
	void updateServiceCheckStatus(ServiceCheck serviceCheck, ServiceCheckStatus serviceCheckStatus) throws DataAccessException;

	/**
	 * Gets all service checks
	 * @return list of all service checks
	 * @throws DataAccessException
	 */
	List<ServiceCheck> getAll() throws DataAccessException;

	/**
	 * Gets all service checks with given vehicle
	 * @return list of all service checks with given vehicle
	 * @throws DataAccessException
	 */
	List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws DataAccessException;

	/**
	 * Gets all service checks with given status
	 * @return list of all service checks with given status
	 * @throws DataAccessException
	 */
	List<ServiceCheck> getAllByServiceCheckStatus(ServiceCheckStatus serviceCheckStatus) throws DataAccessException;

	/**
	 * Get all service checks with given time interval
	 * @param startTime start date of interval
	 * @param endTime end date of interval
	 * @return list of all service checks with given interval
	 * @throws DataAccessException
	 */
	List<ServiceCheck> getAllByTimeInterval(Date startTime, Date endTime) throws DataAccessException;
}
