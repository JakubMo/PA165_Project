package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.HibernateErrorException;

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
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public void create(Employee employee) throws HibernateErrorException;

    /**
     * Get all employees.
     *
     * @return list of all employees
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public List<Employee> getAll() throws HibernateErrorException;

    /**
     * Get employee with given id.
     *
     * @param id id of employee to be found
     * @return found employee
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public Employee get(Long id) throws HibernateErrorException;

    /**
     * Update given employee.
     *
     * @param employee employee to be updated
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public void update(Employee employee) throws HibernateErrorException;

    /**
     * Delete given employee.
     *
     * @param employee employee to be deleted
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public void delete(Employee employee) throws HibernateErrorException;
}
