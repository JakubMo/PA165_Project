package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.service.EmployeeService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Loads data to database
 * 
 * @author jakub
 */
@Component
@Transactional
public class LoadDataFacadeImpl implements LoadDataFacade {
    
    final static Logger log = LoggerFactory.getLogger(LoadDataFacadeImpl.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    @Override
    public void load() throws IOException {
        Employee jakub = employee("Jakub", "Mozucha", "jakub@mail.com", "7654321", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd1");
        Employee marek = employee("Marek", "Jonis", "marek@mail.com", "1234567", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd2");
        Employee mario = employee("Mario", "Kudolani", "mario@mail.com", "1237654", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd3");
        Employee tomas = employee("Tomas", "Borcin", "tomas@mail.com", "7651234", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd4");
        
        log.info("Loaded all employees.");
        
    }
    
    private Employee employee(String firstname, String lastname, String email, 
            String phoneNumber, String role, BigDecimal credit, Category category, String password) {
        
        Employee emp = new Employee(firstname, lastname, email, phoneNumber, role, credit, category, null, null); 
        employeeService.registerEmployee(emp, password);
        return emp;
    }
    
    private Vehicle vehicle(String vin, String brand, String model, String type, int yearOfProduction, 
            String engineType, Long mileage, int ServiceCheckInterval, Long maxMileage) {
        
        Vehicle veh = new Vehicle();
        veh.setVin(vin);
        veh.setBrand(brand);
        veh.setModel(model);
        veh.setType(type);
        veh.setYearOfProduction(yearOfProduction);
        veh.setEngineType(engineType);
        veh.setMileage(mileage);
        veh.setMaxMileage(maxMileage);
        veh.setServiceCheckInterval(ServiceCheckInterval);
        veh.setDrives(null);
        veh.setServiceChecks(null);
        return veh;
    }
    
    private ServiceCheck serviceCheck(Long id, ServiceCheckStatus status, Date serviceCheckDate, 
            Vehicle vehicle, String serviceEmployee, String report) {
        
        return new ServiceCheck(id, status, serviceCheckDate, vehicle, serviceEmployee, report);
    }

    private Drive drive(Long id, Vehicle vehicle, Employee employee, Date startDate, 
            Date endDate, BigDecimal km, DriveStatus driveStatus) {
        
        return new Drive(id, vehicle, employee, startDate, endDate, km, driveStatus);
    }
    
    private Date getDate(int year, int month, int day){
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
