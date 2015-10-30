package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;

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
	 */
	public void create(Employee employee);

	/**
	 * Get all employees.
	 *
	 * @return list of all employees
	 */
	public List<Employee> findAll();

	/**
	 * Get employee with given id.
	 *
	 * @param id id of employee to be found
	 * @return found employee
	 */
	public Employee findById(Long id);

	/**
	 * Update given employee.
	 *
	 * @param employee employee to be updated
	 */
	public void update(Employee employee);

	/**
	 * Delete given employee.
	 *
	 * @param employee employee to be deleted
	 */
	public void delete(Employee employee);
}
