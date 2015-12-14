package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.service.DriveService;
import cz.muni.fi.pa165.project.service.EmployeeService;
import cz.muni.fi.pa165.project.service.ServiceCheckService;
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
import org.springframework.dao.DataAccessException;
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

    @Autowired
    private ServiceCheckService serviceCheckService;

    @Autowired
    private DriveService driveService;

    @Override
    public void load() throws IOException {
        try {
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

            ServiceCheck checkAccord = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 7, 15), hondaAccord, "Jan Suchy", "good condition");
            ServiceCheck checkAccord2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 5, 27), hondaAccord2, "Peter Dan", "poor lights");
            ServiceCheck checkCivic = serviceCheck(null, ServiceCheckStatus.DONE_NOT_OK, getDate(2015, 6, 8), hondaCivic, "Martin Coupek", "holes in exhaust system");
            ServiceCheck checkCivic2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 9, 10), hondaCivic2, "Jan Suchy", "will need new tires soon");
            ServiceCheck checkJazz = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2014, 11, 29), hondaJazz, "Martin Coupek", "overall good");
            ServiceCheck checkJazz2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 6, 13), hondaJazz2, "Richard Janik", "weird noise from trunk");
            ServiceCheck checkFocus = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 10, 19), fordFocus, "Martin Coupek", "overall good");
            ServiceCheck checkFocus2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 7, 22), fordFocus2, "Peter Dan", "overall good");
            ServiceCheck checkFocus3 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 6, 29), fordFocus3, "Jan Suchy", "overall good");
            ServiceCheck checkFocus4 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2014, 11, 5), fordFocus4, "Martin Coupek", "overall good");
            ServiceCheck checkMondeo = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 1, 30), fordMondeo, "Jan Suchy", "passed last time, next time propably won't");
            ServiceCheck checkCMax = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 8, 7), fordCMax, "Martin Coupek", "very good!");
            ServiceCheck checkCMax2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 9, 8), fordCMax2, "Martin Coupek", "small flaws, but good overall");
            ServiceCheck checkFavorit = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2013, 12, 30), skodaFavorit, "Peter Dan", "undestructable communist machine - good!");
            ServiceCheck checkFelicia = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 11, 30), skodaFelicia, "Jan Suchy", "rusty but steady");
            ServiceCheck checkFabia = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 2, 16), skodaFabia, "Richard Janik", "ok");
            ServiceCheck checkFabia2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 5, 3), skodaFabia2, "Richard Janik", "good");
            ServiceCheck checkOctavia = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 4, 18), skodaOctavia, "Peter Dan", "turbo will be dead soon");
            ServiceCheck checkOctavia2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 7, 1), skodaOctavia2, "Martin Coupek", "very good");
            ServiceCheck checkOctavia3 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 10, 25), skodaOctavia3, "Peter Dan", "ok");
            ServiceCheck checkOctavia4 = serviceCheck(null, ServiceCheckStatus.DONE_NOT_OK, getDate(2015, 4, 18), skodaOctavia4, "Richard Janik", "rusty breaks");
            ServiceCheck checkPolo = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 3, 2), vwPolo, "Jan Suchy", "starter will be dead soon");
            ServiceCheck checkPolo2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 8, 29), vwPolo2, "Martin Coupek", "ok");
            ServiceCheck checkGolf = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 8, 29), vwGolf, "Martin Coupek", "rusty exhaust system");
            ServiceCheck checkGolf2 = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 5, 6), vwGolf2, "Richard Janik", "ok");
            ServiceCheck checkPassat = serviceCheck(null, ServiceCheckStatus.DONE_OK, getDate(2015, 8, 29), vwPassat, "Peter Dan", "ok");
            log.info("Loaded all service checks.");

            Drive driveAccord = drive(null, hondaAccord, jakub, getDate(2015, 11, 10), getDate(2015, 11, 11), BigDecimal.valueOf(256L), DriveStatus.COMPLETED);
            Drive driveFocus = drive(null, fordFocus, tomas, getDate(2015, 11, 22), getDate(2015, 11, 30), BigDecimal.valueOf(256L), DriveStatus.CANCELLED);
            Drive driveFabia = drive(null, skodaFabia, martin, getDate(2015, 12, 15), getDate(2015, 12, 20), BigDecimal.valueOf(256L), DriveStatus.REQUESTED);
            Drive driveFabia_2 = drive(null, skodaFabia, lukas, getDate(2015, 12, 21), getDate(2015, 12, 23), BigDecimal.valueOf(256L), DriveStatus.APPROVED);
            log.info("Loaded all drives.");
        } catch (DataAccessException daex) {
            throw new IOException("Data Access Exception occured: " + daex.getMessage());
        } catch (IllegalArgumentException iaex) {
            throw new IOException("Illegal argument exception occured: " + iaex.getMessage());
        } catch (Exception ex) {
            throw new IOException("Exception occured: " + ex.getMessage());
        }
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

        ServiceCheck check = new ServiceCheck(id, status, serviceCheckDate, vehicle, serviceEmployee, report);
        serviceCheckService.createServiceCheck(check);
        return check;
    }

    private Drive drive(Long id, Vehicle vehicle, Employee employee, Date startDate,
            Date endDate, BigDecimal km, DriveStatus driveStatus) {

        Drive drive = new Drive(id, vehicle, employee, startDate, endDate, km, driveStatus);
        driveService.createDrive(drive);
        return drive;
    }

    private Date getDate(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
