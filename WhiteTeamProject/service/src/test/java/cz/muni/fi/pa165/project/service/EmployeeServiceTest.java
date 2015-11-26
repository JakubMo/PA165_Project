package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import javax.inject.Inject;

import org.hibernate.service.spi.ServiceException;

import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertTrue;
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
    
    @BeforeMethod
    public void prepareEmployees(){
        employee1 = new Employee();
        employee1.setLastname("Novak");
        employee1.setEmail("novak@mail.com");
        
        employee2 = new Employee();
        employee1.setLastname("Svoboda");
        employee1.setEmail("svoboda@mail.com");
        
        employee3 = new Employee();
        employee1.setLastname("Hruska");
        employee1.setEmail("hruska@mail.com");
    }
    
    @Test
    public void registerAndAuthenticateUser() {
        employeeService.registerEmployee(employee1, "pwd");
        assertTrue(employeeService.authenticate(employee1, "pwd"));
    }
    
}
