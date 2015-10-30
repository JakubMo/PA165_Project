package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.dao.EmployeeDaoImpl;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Tests for Employee entity.
 *
 * @author Marek
 */
public class EmployeeTest {
    
    private Employee employee1;
    private Employee employee2;
    
    @BeforeClass
    public void initialize() {
        employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstname("Jozko");
        employee1.setLastname("Mrkvicka");
        employee1.setEmail("jozkomrkvicka@firma.mail");
        employee1.setPhoneNumber("0987 654 321");
        employee1.setRole("user");
        employee1.setCredit(new BigDecimal("1230.50"));
        employee1.setCategory(Category.SILVER);
        
        employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstname("Janko");
        employee2.setLastname("Hrasko");
        employee2.setEmail("hraskoj@firma.mail");
        employee2.setPhoneNumber("0963 852 741");
        employee2.setRole("admin");
        employee2.setCredit(new BigDecimal("45700.00"));
        employee2.setCategory(Category.PLATINUM);
    }
    
    @Test(priority = 1)
    public void employeeCreateTest() throws HibernateErrorException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        List<Employee> list = session.createQuery("select e from Employee e").list();
        
        session.getTransaction().commit();
        
        assertEquals(list.size(), 0);
        
        employeeDao.create(employee1);
        employeeDao.create(employee2);
        
        if(!session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        session.getTransaction().begin();
        
        List<Employee> employees = session.createQuery("select e from Employee e").list();
        
        session.getTransaction().commit();
        
        assertEquals(employees.size(), 2);
    }
    
    @Test(priority = 2)
    public void employeeFindTest() throws HibernateErrorException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        
        List<Employee> employees = employeeDao.findAll();       
        Employee emp1 = employeeDao.findById(employee1.getId());
        Employee emp2 = employeeDao.findById(employee2.getId());
        
        assertEquals(employees.size(), 2);   
        assertEquals(emp1, employee1);
        assertEquals(emp2, employee2);
    }
    
    @Test(priority = 3)
    public void employeeUpdateTest() throws HibernateErrorException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        
        String updatedEmail = "newemail@firma.email";
        String updatedPhoneNumber = "0179 324 865";
        
        Employee updatedEmployee2 = new Employee(employee2.getId(), employee2.getFirstname(), employee2.getLastname(), employee2.getEmail(), 
                employee2.getPhoneNumber(), employee2.getRole(), employee2.getCredit(), employee2.getCategory());
        updatedEmployee2.setEmail(updatedEmail);
        updatedEmployee2.setPhoneNumber(updatedPhoneNumber);
        
        employeeDao.update(updatedEmployee2);
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        List<Employee> employees2 = session.createQuery("select e from Employee e where e.id=:num")
                .setParameter("num", employee2.getId()).list();
        
        session.getTransaction().commit();
        
        assertEquals(employees2.size(), 1);
        assertEquals(employees2.get(0).getEmail(), updatedEmail);
        assertEquals(employees2.get(0).getPhoneNumber(), updatedPhoneNumber);
    }
    
    @Test(priority = 4)
    public void employeeDeleteTest() throws HibernateErrorException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        List<Employee> employees = session.createQuery("select e from Employee e").list();
        
        session.getTransaction().commit();
        
        assertEquals(employees.size(), 2);
        
        employeeDao.delete(employee1);
        employeeDao.delete(employee2);
        
        if(!session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        session.getTransaction().begin();
        
        List<Employee> list = session.createQuery("select e from Employee e").list();
        
        session.getTransaction().commit();
        
        assertEquals(list.size(), 0);
    }
}
