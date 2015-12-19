/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.EmployeeCreateDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author jakub
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests{
    
    @Inject
    BeanMappingService beanMappingService;
    
    Employee employee;
    EmployeeDTO employeeDTO;
    EmployeeAuthenticateDTO employeeAuthenticateDTO;
    EmployeeCreateDTO employeeCreateDTO;
    
    Drive drive;
    
    @Test
    public void EmployeeMappingTest() {
        employeeAuthenticateDTO = new EmployeeAuthenticateDTO();
        employeeAuthenticateDTO.setEmail("jakub@mail.com");
        employeeAuthenticateDTO.setPassword("pwd1");
        employee = beanMappingService.mapTo(employeeAuthenticateDTO, Employee.class);
        
        employeeCreateDTO = new EmployeeCreateDTO("jakub", "mozucha", "jakub@mail.com", "xxx", "ADMIN", BigDecimal.ZERO, Category.BRONZE, null);
        employee = beanMappingService.mapTo(employeeCreateDTO, Employee.class);
        
        employeeDTO = new EmployeeDTO("jakub", "mozucha", "jakub@mail.com", "xxx", "ADMIN", BigDecimal.ZERO, Category.BRONZE, null);
        employee = beanMappingService.mapTo(employeeDTO, Employee.class);
    }
}
