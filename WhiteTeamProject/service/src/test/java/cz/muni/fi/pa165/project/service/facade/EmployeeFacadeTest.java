package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.BeanMappingServiceImpl;
import cz.muni.fi.pa165.project.service.EmployeeService;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jakub
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class EmployeeFacadeTest {
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @Mock
    private EmployeeService employeeService;
    
    @Inject
    @InjectMocks
    private EmployeeFacadeImpl employeeFacade;
    
    EmployeeDTO employeeDTO;
    Employee employee2;
    Employee employee;
    
    @BeforeMethod //has to be reseted every time, otherwise verify() can behave abnormaly
    public void prepare() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare_objects(){
        employeeDTO = new EmployeeDTO("Petr", "Svoboda", "svoboda@mail.com", 
                "777xxx", "Manager", BigDecimal.TEN, Category.BRONZE, null);
        employee2 = new Employee("Petr", "Svoboda", "svoboda@mail.com", 
                "777xxx", "Manager", BigDecimal.TEN, Category.BRONZE, null, null);
    }
    
    
    @Test
    public void registerTest() {
        try{
            employeeFacade.register(employeeDTO, "pwd");
        } catch (NullPointerException e) {// id is not generated, has to be skipped
        }
        verify(beanMappingService).mapTo(employeeDTO, Employee.class);
        verify(employeeService).registerEmployee(employee, "pwd");
    }
    
    @Test
    public void getAllTest() {
        employeeFacade.getAll();
        verify(beanMappingService).mapTo(employeeService.getAll(), EmployeeDTO.class);
    }
    
    @Test
    public void getByEmailTest() {
        when(employeeService.getByEmail("name@mail.com")).thenReturn(employee2);
        
        EmployeeDTO emp = employeeFacade.getByEmail("name@mail.com");
   
        verify(employeeService).getByEmail("name@mail.com");
        verify(beanMappingService).mapTo(employee2, EmployeeDTO.class);
    }
    
    @Test
    public void getAllByLastNameTest() {
        employeeFacade.getAllByLastName(employeeDTO.getLastname());
        verify(beanMappingService).mapTo(employeeService.getAllByLastName(employeeDTO.getLastname()), EmployeeDTO.class);
    }
    
    @Test
    public void updateTest() {
        employeeDTO.setId(1L);
        
        employeeFacade.update(employeeDTO);
       
        verify(beanMappingService).mapTo(employeeDTO, Employee.class);
        verify(employeeService).update(any());
    }
    
    @Test
    public void delete() {
        employeeDTO.setId(1L);
        
        employeeFacade.delete(employeeDTO);
       
        verify(beanMappingService).mapTo(employeeDTO, Employee.class);
        verify(employeeService).delete(any());
    }
}
