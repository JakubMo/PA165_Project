package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Employee} entity.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/22/15.
 */
public interface EmployeeService {

	/**
	 * Register new employee with given unencrypted password.
	 *
	 * @param employee            employee to be registered.
	 * @param unencryptedPassword unencrypted password of the employee.
	 */
	void registerEmployee(Employee employee, String unencryptedPassword) throws DataAccessExceptionImpl;

	/**
	 * Gets employee with given id.
	 *
	 * @param id id of employee to be found.
	 * @return employee with given id.
	 */
	Employee get(Long id);

	/**
	 * Get all employees.
	 *
	 * @return list of all employees
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	List<Employee> getAll() throws DataAccessExceptionImpl;

	/**
	 * Get employee with given email.
	 *
	 * @param email email of employee to be found.
	 * @return found employee.
	 * @throws DataAccessExceptionImpl
	 */
	Employee getByEmail(String email) throws DataAccessExceptionImpl;

	/**
	 * Get employees with given last name.
	 *
	 * @param lastName last name of employees to be found.
	 * @return found employees.
	 * @throws DataAccessExceptionImpl
	 */
	List<Employee> getAllByLastName(String lastName) throws DataAccessExceptionImpl;

	/**
	 * Authenticates employee.
	 *
	 * @param employee employee to be authenticated.
	 * @param password employee password.
	 * @return true if hashed password matches the records.
	 */
	boolean authenticate(Employee employee, String password);
}
