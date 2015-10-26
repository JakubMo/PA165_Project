package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;

import java.util.List;

/**
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */
public interface EmployeeDao {

	//TODO javadoc

	public List<Employee> getAllEmployees();

	public Employee getEmployee(Long id);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public void insertEmployee(Employee employee);
}
