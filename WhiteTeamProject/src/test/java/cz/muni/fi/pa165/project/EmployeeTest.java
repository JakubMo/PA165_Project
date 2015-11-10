package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import java.math.BigDecimal;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Employee entity.
 *
 * @author Marek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class EmployeeTest {
    
    @Autowired
    @Qualifier(value = "employeeDao")
    private EmployeeDao employeeDao;
        
    private Employee prepareEmployee1() {
        Employee employee1 = new Employee();
        employee1.setFirstname("Jozko");
        employee1.setLastname("Mrkvicka");
        employee1.setEmail("jozkomrkvicka@firma.mail");
        employee1.setPhoneNumber("0987 654 321");
        employee1.setRole("user");
        employee1.setCredit(new BigDecimal("1230.50"));
        employee1.setCategory(Category.SILVER);
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
    
    @Test
    public void deleteEmployeeTest() throws DataAccessExceptionImpl {        
        Employee employee1 = prepareEmployee1();
        Employee employee2 = prepareEmployee2();
        
        employeeDao.create(employee1);
        employeeDao.create(employee2);
        
        List<Employee> employees = employeeDao.getAll();
        assertEquals(employees.size(), 2);
        
        employeeDao.delete(employee1);
        
        List<Employee> emps = employeeDao.getAll();
        assertEquals(emps.size(), 1);
        assertEquals(emps.get(0), employee2);
        
        employeeDao.delete(employee2);
        
        List<Employee> list = employeeDao.getAll();
        assertEquals(list.size(), 0);
    }
}
