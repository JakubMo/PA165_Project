package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static junit.framework.Assert.assertFalse;

import org.hibernate.service.spi.ServiceException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jakub
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeDao employeeDao;
    
    @Inject
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    @BeforeClass
    public void prepare() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    List<Employee> all;
    
    @BeforeMethod
    public void prepareEmployees(){
        employee1 = new Employee();
        employee1.setLastname("Novak");
        employee1.setEmail("novak@mail.com");
        
        employee2 = new Employee();
        employee2.setLastname("Svoboda");
        employee2.setEmail("svoboda@mail.com");
        
        employee3 = new Employee();
        employee3.setLastname("Hruska");
        employee3.setEmail("hruska@mail.com");
        
        all = new ArrayList<>();
        all.add(employee1);
        all.add(employee2);
        all.add(employee3);
        
        when(employeeDao.getAll()).thenReturn(all);
    }
    
    @Test
    public void registerAndAuthenticateUser() {
        employeeService.registerEmployee(employee1, "pwd");
        assertTrue(employeeService.authenticate(employee1, "pwd"));
    }
    
    @Test
    public void registerAndAuthenticateUserEmptyPwd() {
        employeeService.registerEmployee(employee1, "");
        assertTrue(employeeService.authenticate(employee1, ""));
    }
    
    @Test
    public void registerAndAuthenticateUserIncorrectPwd() {
        employeeService.registerEmployee(employee1, "pwd");
        assertFalse(employeeService.authenticate(employee1, "pwd1"));
    }
    
    @Test
    public void registerAndAuthenticateUserCaseSensitivePwd() {
        employeeService.registerEmployee(employee1, "pwd");
        assertFalse(employeeService.authenticate(employee1, "PWD"));
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void registerAndAuthenticateUserWithoutPwd() {
        employeeService.authenticate(employee1, "PWD");
    }
    
    @Test
    public void registerAndAuthenticateMultipleUsers() {
        employeeService.registerEmployee(employee1, "pwd");
        employeeService.registerEmployee(employee2, "pwd1");
        assertTrue(employeeService.authenticate(employee1, "pwd"));
        assertTrue(employeeService.authenticate(employee2, "pwd1"));
    }
    
    @Test
    public void getTest() {
        employee1.setId(1L);
        when(employeeDao.get(1L)).thenReturn(employee1);
        assertEquals(employeeService.get(employee1.getId()), employee1);
    }
    
    @Test
    public void getAllTest() {
        assertEquals(employeeService.getAll(), all);
    }
    
    @Test
    public void getByEmailTest() {
        when(employeeDao.getByEmail("hruska@mail.com")).thenReturn(employee3);
        assertEquals(employeeService.getByEmail(employee3.getEmail()), employee3);
    }
    
    @Test
    public void getAllByLastName() {
        List<Employee> empl = new ArrayList<>();
        empl.add(employee2);
        
        when(employeeDao.getAllByLastName("Svoboda")).thenReturn(empl);
        assertEquals(employeeService.getAllByLastName(employee2.getLastname()), empl);
    }
    
    @Test
    public void update(){
        employeeService.update(employee1);
        verify(employeeDao).update(employee1);
    }
    
    @Test
    public void delete(){
        employeeService.delete(employee2);
        verify(employeeDao).delete(employee2);
    }
}
