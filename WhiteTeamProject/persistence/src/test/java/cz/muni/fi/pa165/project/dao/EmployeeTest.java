package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.PersistenceLayerContext;
import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.Test;

/**
 * Tests for Employee entity.
 *
 * @author Marek
 */

@ContextConfiguration(classes = {PersistenceLayerContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private EmployeeDao employeeDao;
        
        @PersistenceContext
        private EntityManager em;

	private Employee prepareEmployee1() {
		Employee employee1 = new Employee();
		employee1.setFirstname("Jozko");
		employee1.setLastname("Mrkvicka");
		employee1.setEmail("jozkomrkvicka@firma.mail");
		employee1.setPhoneNumber("0987 654 321");
		employee1.setRole("user");
		employee1.setCredit(new BigDecimal("1230.50"));
		employee1.setCategory(Category.SILVER);
		employee1.setPasswordHash(":$#GFDMO$ADSF78");
		return employee1;
	}

	private Employee prepareEmployee2() {
		Employee employee2 = new Employee();
		employee2.setFirstname("Janko");
		employee2.setLastname("Hrasko");
		employee2.setEmail("hraskoj@firma.mail");
		employee2.setPhoneNumber("0963 852 741");
		employee2.setRole("admin");
		employee2.setCredit(new BigDecimal("45700.00"));
		employee2.setCategory(Category.PLATINUM);
		employee2.setPasswordHash("$#&5^%5DDF><{}DSFA486");
		return employee2;
	}

	@Test
	public void createEmployeeTest() throws DataAccessExceptionImpl {
		List<Employee> list = employeeDao.getAll();
		assertEquals(list.size(), 0);

		Employee employee1 = prepareEmployee1();
		Employee employee2 = prepareEmployee2();

		employeeDao.create(employee1);
		employeeDao.create(employee2);

		List<Employee> employees = employeeDao.getAll();
		assertEquals(employees.size(), 2);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void createNullEmployeeTest() {
		employeeDao.create(null);
	}

	@Test
	public void getEmployeesTest() throws DataAccessExceptionImpl {
		Employee employee1 = prepareEmployee1();
		Employee employee2 = prepareEmployee2();

		employeeDao.create(employee1);
		employeeDao.create(employee2);

		List<Employee> employees = employeeDao.getAll();
		Employee emp1 = employeeDao.get(employee1.getId());
		Employee emp2 = employeeDao.get(employee2.getId());

		assertEquals(employees.size(), 2);
		assertEquals(emp1, employee1);
		assertEquals(emp2, employee2);
	}

	@Test
	public void getEmployeeByEmail() throws DataAccessExceptionImpl {
		Employee employee1 = prepareEmployee1();
		Employee employee2 = prepareEmployee2();

		employeeDao.create(employee1);

		Employee result1 = employeeDao.getByEmail(employee1.getEmail());
		Employee result2 = null;
		try {
			result2 = employeeDao.getByEmail(employee2.getEmail());
			fail();
		} catch (DataAccessExceptionImpl e) {
		}

		assertEquals(employee1, result1);
		assertEquals(null, result2);

		employeeDao.create(employee2);

		result1 = employeeDao.getByEmail(employee1.getEmail());
		result2 = employeeDao.getByEmail(employee2.getEmail());

		assertEquals(employee1, result1);
		assertEquals(employee2, result2);
	}

	@Test
	public void getEmployeesByLastName() throws DataAccessExceptionImpl{
		Employee employee1 = prepareEmployee1();
		Employee employee2 = prepareEmployee2();

		employeeDao.create(employee1);
		employeeDao.create(employee2);

		List<Employee> employees = employeeDao.getAll();
		Employee emp1 = employeeDao.get(employee1.getId());
		Employee emp2 = employeeDao.get(employee2.getId());

		assertEquals(employees.size(), 2);
		assertEquals(emp1, employee1);
		assertEquals(emp2, employee2);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void getEmployeeNullIdTest() {
		employeeDao.get(null);
	}

	@Test
	public void updateEmployeeTest() throws DataAccessExceptionImpl {
		Employee employee2 = prepareEmployee2();
		employeeDao.create(employee2);

		String updatedEmail = "newemail@firma.email";
		String updatedPhoneNumber = "0179 324 865";

		employee2.setEmail(updatedEmail);
		employee2.setPhoneNumber(updatedPhoneNumber);

		employeeDao.update(employee2);

		Employee result = employeeDao.get(employee2.getId());

		assertEquals(result.getEmail(), updatedEmail);
		assertEquals(result.getPhoneNumber(), updatedPhoneNumber);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void updateNullEmployeeTest() {
		employeeDao.update(null);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void updateDeletedEmployeeTest() {
		Employee employee1 = prepareEmployee1();

		employeeDao.create(employee1);
		employeeDao.delete(employee1);

		String updatedPhoneNumber = "0179 324 865";
		employee1.setPhoneNumber(updatedPhoneNumber);

		employeeDao.update(employee1);
	}

	@Test
	public void deleteEmployeeTest() throws DataAccessExceptionImpl {
		Employee employee1 = prepareEmployee1();
		Employee employee2 = prepareEmployee2();

		employeeDao.create(employee1);

		List<Employee> employees1 = employeeDao.getAllByLastName(employee1.getLastname());
		assertEquals(employees1.size(), 1);

		employeeDao.create(employee2);

		employees1 = employeeDao.getAllByLastName(employee1.getLastname());
		assertEquals(employees1.size(), 1);
		assertEquals(employees1.get(0), employee1);

		List<Employee> employees2 = employeeDao.getAllByLastName(employee2.getLastname());
		assertEquals(employees2.size(), 1);
		assertEquals(employees2.get(0), employee2);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void deleteNullEmployeeTest() {
		employeeDao.delete(null);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void deleteTwiceEmployeeTest() {
		Employee employee1 = prepareEmployee1();

		employeeDao.create(employee1);
		employeeDao.delete(employee1);
		employeeDao.delete(employee1);
	}
}
