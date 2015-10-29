package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.dao.EmployeeDaoImpl;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.HibernateUtil;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDaoImpl();

		Employee employee = new Employee();
		//employee.setId(0L);
		employee.setFirstname("New");
		employee.setLastname("Employee");
		employee.setEmail("fake@email.com");
		employee.setPhoneNumber("55-448-221");
		employee.setRole("role");
		employee.setCredit(new BigDecimal(10000));

		employeeDao.create(employee);
		//employee.setId(1L);
		//employeeDao.insertEmployee(employee);
		employee.setFirstname("Updated");
		employeeDao.update(employee);
                Long id = employee.getId();
		System.out.println(employeeDao.findById(id).toString());

		HibernateUtil.getSessionFactory().close();
	}
}
