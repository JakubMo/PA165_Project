package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.util.List;

/**
 * Employee DAO interface
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */
public interface EmployeeDao {

	/**
	 * Create new employee.
	 *
	 * @param employee employee to be created
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	void create(Employee employee) throws DataAccessExceptionImpl;

	/**
	 * Get all employees.
	 *
	 * @return list of all employees
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	List<Employee> getAll() throws DataAccessExceptionImpl;

	/**
	 * Get employee with given id.
	 *
	 * @param id id of employee to be found
	 * @return found employee
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	Employee get(Long id) throws DataAccessExceptionImpl;

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
	 * @return found employees
	 * @throws DataAccessExceptionImpl
	 */
	List<Employee> getAllByLastName(String lastName) throws DataAccessExceptionImpl;

	/**
	 * Update given employee.
	 *
	 * @param employee employee to be updated
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	void update(Employee employee) throws DataAccessExceptionImpl;

	/**
	 * Delete given employee.
	 *
	 * @param employee employee to be deleted
	 * @throws cz.muni.fi.pa165.project.util.DataAccessExceptionImpl
	 */
	void delete(Employee employee) throws DataAccessExceptionImpl;
}
