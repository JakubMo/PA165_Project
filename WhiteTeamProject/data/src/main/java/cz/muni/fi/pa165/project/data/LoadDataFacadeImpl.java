package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.service.EmployeeService;
import cz.muni.fi.pa165.project.service.VehicleService;
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
    
    @Autowired
    private VehicleService vehicleService;
    
    @Override
    public void load() throws IOException {
        Employee jakub = employee("Jakub", "Mozucha", "jakub@mail.com", "7654321", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd1");
        Employee marek = employee("Marek", "Jonis", "marek@mail.com", "1234567", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd2");
        Employee mario = employee("Mario", "Kudolani", "mario@mail.com", "1237654", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd3");
        Employee tomas = employee("Tomas", "Borcin", "tomas@mail.com", "7651234", "ADMIN", BigDecimal.TEN, Category.PLATINUM, "pwd4");
        Employee martin = employee("Martin", "Svoboda", "martin@mail.com", "11223344", "USER", BigDecimal.TEN, Category.GOLD, "pwd5");
        Employee peter = employee("Peter", "Kratky", "peter@mail.com", "44332211", "USER", BigDecimal.ONE, Category.SILVER, "pwd6");
        Employee lukas = employee("Lukas", "Zeman", "lukas@mail.com", "11442233", "USER", BigDecimal.ONE, Category.BRONZE, "pwd7");
        log.info("Loaded all employees.");
        
        Vehicle hondaAccord = vehicle("FNHCE75100U019029", "Honda", "Accord", "liftback", 2005, "petrol", 200399L, 12, 300000L);
        Vehicle hondaAccord2 = vehicle("FBHCE751L0U0190U9", "Honda", "Accord", "liftback", 2010, "diesel", 79399L, 6, 300000L);
        Vehicle hondaCivic = vehicle("RHMEG33S00S221213", "Honda", "Civic", "hatchback", 2010, "petrol", 135900L, 10, 250000L);
        Vehicle hondaCivic2 = vehicle("RBMEG3US0MS221Z1Z", "Honda", "Civic", "hatchback", 2006, "petrol", 235900L, 10, 250000L);
        Vehicle hondaJazz = vehicle("BHM3D17502S220E08", "Honda", "Jazz", "hatchback", 2004, "petrol", 184300L, 15, 200000L);
        Vehicle hondaJazz2 = vehicle("BJM3D17502S2I0E0L", "Honda", "Jazz", "hatchback", 2010, "petrol", 67402L, 6, 200000L);
        Vehicle fordFocus = vehicle("WF0BXXGCD6XY7838Z", "Ford", "Focus", "combi", 2008, "diesel", 199940L, 10, 250000L);
        Vehicle fordFocus2 = vehicle("WF0UXXGCDXXY78H8Z", "Ford", "Focus", "sedan", 2002, "diesel", 249940L, 12, 250000L);
        Vehicle fordFocus3 = vehicle("WFZBXXNCD6XY7A38Z", "Ford", "Focus", "combi", 2012, "petrol", 30940L, 6, 300000L);
        Vehicle fordFocus4 = vehicle("AF0BXNGCD6XM7838Z", "Ford", "Focus", "hatchback", 2014, "diesel", 4579L, 6, 300000L);
        Vehicle fordMondeo = vehicle("ZF0NXXGBBNTY63T14", "Ford", "Mondeo", "sedan", 2013, "diesel", 15000L, 12, 300000L);
        Vehicle fordCMax = vehicle("WFLMXXGSDM3J46Y01", "Ford", "C-Max", "combi", 2007, "petrol", 160350L, 11, 230000L);
        Vehicle fordCMax2 = vehicle("WFLMXYGSDM3J46YZ1", "Ford", "C-Max", "combi", 2004, "petrol", 230540L, 11, 230000L);
        Vehicle skodaFavorit = vehicle("TMCADA200LW08Z892", "Skoda", "Favorit", "hatchback", 1989, "petrol", 89500L, 24, 90000L);
        Vehicle skodaFelicia = vehicle("TMCEFF613O53307X6", "Skoda", "Felicia", "hatchback", 1999, "petrol", 176000L, 12, 180000L);
        Vehicle skodaFabia = vehicle("TMBPA16YX235Z9T6P", "Skoda", "Fabia", "hatchback", 2003, "petrol", 198210L, 12, 200000L);
        Vehicle skodaFabia2 = vehicle("TMBZA17YX23XZ9TR7", "Skoda", "Fabia", "hatchback", 2006, "petrol", 125002L, 12, 200000L);
        Vehicle skodaOctavia = vehicle("TTBZZZ1U9W2B46D94", "Skoda", "Octavia", "liftback", 2004, "diesel", 235000L, 12, 300000L);
        Vehicle skodaOctavia2 = vehicle("TTRZZZ1UUW2BA6D9K", "Skoda", "Octavia", "liftback", 2010, "diesel", 134000L, 12, 300000L);
        Vehicle skodaOctavia3 = vehicle("TTBZIZ1U9WMB46DP4", "Skoda", "Octavia", "combi", 2007, "petrol", 278400L, 10, 350000L);
        Vehicle skodaOctavia4 = vehicle("TJBZI21U9W4B4RDP4", "Skoda", "Octavia", "liftback", 2014, "diesel", 13040L, 10, 200000L);
        Vehicle vwPolo = vehicle("WVWZUZ9NZ5Y1M40N2", "Volkswagen", "Polo", "hachback", 2005, "petrol", 186400L, 12, 200000L);
        Vehicle vwPolo2 = vehicle("WVWMUZCNZ5I1MB0N2", "Volkswagen", "Polo", "combi", 2001, "LPG", 230340L, 15, 300000L);
        Vehicle vwGolf = vehicle("WVWZZM1JZYU0K0J27", "Volkswagen", "Golf", "hachback", 2000, "petrol", 236400L, 12, 250000L);
        Vehicle vwGolf2 = vehicle("WVWZZU1JZYU0B0JM7", "Volkswagen", "Golf", "combi", 2001, "diesel", 159400L, 10, 250000L);
        Vehicle vwPassat = vehicle("WVWZZZEBZYPA4J254", "Volkswagen", "Passat", "combi", 2004, "diesel", 289400L, 8, 300000L);
        log.info("Loaded all vehicles.");
        
        
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
        vehicleService.createVehicle(veh);
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
