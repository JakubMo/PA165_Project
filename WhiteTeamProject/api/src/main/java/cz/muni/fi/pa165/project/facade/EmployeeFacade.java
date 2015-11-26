package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.Collection;

/**
 * Employee facade interface.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */
public interface EmployeeFacade {

	/**
	 * Register new employee.
	 *
	 * @param employeeDTO         employee to be created.
	 * @param unencryptedPassword unencrypted password of employee.
	 * @throws DataAccessExceptionImpl
	 */
	void register(EmployeeDTO employeeDTO, String unencryptedPassword) throws DataAccessExceptionImpl;

	/**
	 * Get all employees.
	 *
	 * @return list of all employees.
	 * @throws DataAccessExceptionImpl
	 */
	Collection<EmployeeDTO> getAll() throws DataAccessExceptionImpl;

	/**
	 * Get employee with given email.
	 *
	 * @param email email of employee to be found.
	 * @return found employee.
	 * @throws DataAccessExceptionImpl
	 */
	EmployeeDTO getByEmail(String email) throws DataAccessExceptionImpl;

	/**
	 * Get employees with given last name.
	 *
	 * @param lastName last name of employees to be found.
	 * @return found employees.
	 * @throws DataAccessExceptionImpl
	 */
	Collection<EmployeeDTO> getAllByLastName(String lastName) throws DataAccessExceptionImpl;

	/**
	 * Update given employee.
	 *
	 * @param employeeDTO employee to be updated
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	void update(EmployeeDTO employeeDTO) throws DataAccessExceptionImpl;

	/**
	 * Delete given employee.
	 *
	 * @param employeeDTO employee to be deleted
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	void delete(EmployeeDTO employeeDTO) throws DataAccessExceptionImpl;

	/**
	 * Authenticates employee.
	 *
	 * @param employeeAuthenticateDTO employee to be authenticated.
	 * @return true if hashed password matches the records.
	 */
	boolean authenticate(EmployeeAuthenticateDTO employeeAuthenticateDTO);
}
