package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Drive entity
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/30/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class DriveTest {

	@Autowired
	@Qualifier(value = "driveDao")
	private DriveDao driveDao;

	@Autowired
	@Qualifier(value = "employeeDao")
	private EmployeeDao employeeDao;

	@Autowired
	@Qualifier(value = "vehicleDao")
	private VehicleDao vehicleDao;

	private final String vin1 = "KDJG454SD4DS";
	private final String vin2 = "REWGF69849SADF";

	@Test
	public void createDrive() throws DataAccessExceptionImpl {

		List<Drive> drives = driveDao.getAll();

		assertEquals(0, drives.size());

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.create(drive1);
		driveDao.create(drive2);

		drives = driveDao.getAll();

		assertEquals(2, drives.size());
	}

	@Test
	public void getAll() throws DataAccessExceptionImpl {

		List<Drive> drives = driveDao.getAll();

		assertEquals(drives.size(), 0);

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.create(drive1);

		drives = driveDao.getAll();

		assertEquals(drives.size(), 1);
		assertTrue(drive1.equals(drives.get(0)));

		driveDao.create(drive2);

		drives = driveDao.getAll();

		assertEquals(drives.size(), 2);
		assertTrue(drive1.equals(drives.get(0)));
		assertTrue(drive2.equals(drives.get(1)));
	}

	@Test
	public void getById() throws DataAccessExceptionImpl {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.create(drive1);

		Drive drive = driveDao.get(drive1.getId());

		assertTrue(drive1.equals(drive));
		
		driveDao.create(drive2);

		assertTrue(drive1.equals(driveDao.get(drive1.getId())));
		assertTrue(drive2.equals(driveDao.get(drive2.getId())));
	}

        @Test(expected=DataAccessException.class)
        public void getByIdError() throws DataAccessExceptionImpl {
            driveDao.get(null);
        }
        
        @Test(expected=DataAccessException.class)
        public void getByEmployeeError() throws DataAccessExceptionImpl {
            driveDao.getAllByEmployee(null);
        }
        
        @Test(expected=DataAccessException.class)
        public void getByVehicleError() throws DataAccessExceptionImpl {
            driveDao.getAllByVehicle(null);
        }
        
        @Test(expected=DataAccessException.class)
        public void updateError() throws DataAccessExceptionImpl {
            driveDao.update(null);
        }
        
        @Test(expected=DataAccessException.class)
        public void createError() throws DataAccessExceptionImpl {
            driveDao.create(null);
        }
        
        @Test(expected=DataAccessException.class)
        public void deleteError() throws DataAccessExceptionImpl {
            driveDao.delete(null);
        }
        
	@Test
	public void getByEmployee() throws DataAccessExceptionImpl {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();
		Employee employee = drive1.getEmployee();

		driveDao.create(drive1);

		List<Drive> drives = driveDao.getAllByEmployee(employee);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		employee = drive2.getEmployee();

		driveDao.create(drive2);

		drives = driveDao.getAllByEmployee(employee);

		assertEquals(1, drives.size());
		assertTrue(drive2.equals(drives.get(0)));
	}

	@Test
	public void getByVehicle() throws DataAccessExceptionImpl {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		Vehicle vehicle1 = drive1.getVehicle();
		Vehicle vehicle2 = drive2.getVehicle();

		driveDao.create(drive1);

		List<Drive> drives = driveDao.getAllByVehicle(vehicle1);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		driveDao.create(drive2);

		drives = driveDao.getAllByVehicle(vehicle1);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		drives = driveDao.getAllByVehicle(vehicle2);

		assertEquals(1, drives.size());
		assertTrue(drive2.equals(drives.get(0)));
	}

	@Test
	public void update() throws DataAccessExceptionImpl {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.create(drive1);
		driveDao.create(drive2);

		drive1.setKm(new BigDecimal(13216));
		driveDao.update(drive1);

		assertTrue(new BigDecimal(13216).compareTo(driveDao.get(drive1.getId()).getKm()) == 0);

		drive1.setDriveStatus(DriveStatus.CANCELLED);
		driveDao.update(drive1);

		assertEquals(DriveStatus.CANCELLED, driveDao.get(drive1.getId()).getDriveStatus());

		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 1, 1);
		drive1.setStartDate(calendar.getTime());
		driveDao.update(drive1);

		assertTrue(calendar.getTime().equals(driveDao.get(drive1.getId()).getStartDate()));

		calendar = new GregorianCalendar();
		calendar.set(2015, 2, 2);
		drive1.setEndDate(calendar.getTime());
		driveDao.update(drive1);

		assertTrue(calendar.getTime().equals(driveDao.get(drive1.getId()).getEndDate()));

		Employee employee = createEmployee("NewFirstname", "NewLastname");
		drive1.setEmployee(employee);

		driveDao.update(drive1);

		assertTrue(employee.equals(driveDao.get(drive1.getId()).getEmployee()));

		assertTrue(drive2.equals(driveDao.get(drive2.getId())));
	}

	@Test
	public void delete() throws DataAccessExceptionImpl {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.create(drive1);
		driveDao.create(drive2);

		assertEquals(2, driveDao.getAll().size());

		driveDao.delete(drive1);

		assertEquals(1, driveDao.getAll().size());
		assertEquals(null, driveDao.get(drive1.getId()));
		assertTrue(drive2.equals(driveDao.get(drive2.getId())));

		drive1 = prepareDrive3();

		driveDao.create(drive1);

		driveDao.delete(drive2);

		assertEquals(1, driveDao.getAll().size());
		assertTrue(drive1.equals(driveDao.get(drive1.getId())));
		assertEquals(null, driveDao.get(drive2.getId()));

		driveDao.delete(drive1);

		assertEquals(0, driveDao.getAll().size());
		assertEquals(null, driveDao.get(drive1.getId()));
		assertEquals(null, driveDao.get(drive2.getId()));
	}

	private Employee createEmployee(String firstname, String lastname) throws DataAccessExceptionImpl {
		Employee employee = new Employee(firstname, lastname, "email@email.com", "0958421547", "role", new BigDecimal(5000), Category.SILVER, new ArrayList<>(), "SDO##NDF5:<>$DS$#46");
		employeeDao.create(employee);

		return employee;
	}

	private Vehicle createVehicle(String vin) throws DataAccessExceptionImpl {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("brand");
		vehicle.setDrives(new ArrayList<>());
		vehicle.setEngineType("engine");
		vehicle.setMaxMileage(1000000L);
		vehicle.setMileage(10000L);
		vehicle.setModel("model");
		vehicle.setEngineType("diesel");
		vehicle.setType("type");
		vehicle.setVin(vin);
		vehicle.setServiceChecks(new ArrayList<>());
		vehicle.setServiceCheckInterval(365);
		vehicleDao.create(vehicle);

		return vehicle;
	}

	private Drive prepareDrive1() throws DataAccessExceptionImpl {
		Drive drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive1.setEndDate(calendar.getTime());
		drive1.setDriveStatus(DriveStatus.APPROVED);
		drive1.setVehicle(createVehicle(vin1));
		drive1.setEmployee(createEmployee("Firstname1", "Lastname1"));
		drive1.setKm(new BigDecimal(10000));

		return drive1;
	}

	private Drive prepareDrive2() throws DataAccessExceptionImpl {

		Drive drive2 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive2.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive2.setEndDate(calendar.getTime());
		drive2.setDriveStatus(DriveStatus.COMPLETED);
		drive2.setVehicle(createVehicle(vin2));
		drive2.setEmployee(createEmployee("Firstname2", "Lastname2"));
		drive2.setKm(new BigDecimal(10000));

		return drive2;
	}

	private Drive prepareDrive3() throws DataAccessExceptionImpl {
		Drive drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive1.setEndDate(calendar.getTime());
		drive1.setDriveStatus(DriveStatus.CANCELLED);
		drive1.setVehicle(createVehicle("FJAO836FE093G"));
		drive1.setEmployee(createEmployee("Firstname3", "Lastname3"));
		drive1.setKm(new BigDecimal(10000));

		return drive1;
	}
}