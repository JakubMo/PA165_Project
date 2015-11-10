package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.travelagency.data.entity.Drive;
import cz.muni.fi.pa165.travelagency.data.entity.Employee;
import cz.muni.fi.pa165.travelagency.data.entity.ServiceCheck;
import cz.muni.fi.pa165.travelagency.data.entity.Vehicle;
import cz.muni.fi.pa165.travelagency.data.enums.Category;
import cz.muni.fi.pa165.travelagency.data.enums.DriveStatus;
import cz.muni.fi.pa165.travelagency.util.HibernateErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
	public void createDrive() throws HibernateErrorException {

		List<Drive> drives = driveDao.getAllDrives();

		assertEquals(0, drives.size());

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.insertDrive(drive1);
		driveDao.insertDrive(drive2);

		drives = driveDao.getAllDrives();

		assertEquals(2, drives.size());
	}

	@Test
	public void getAllDrives() throws HibernateErrorException {

		List<Drive> drives = driveDao.getAllDrives();

		assertEquals(drives.size(), 0);

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.insertDrive(drive1);

		drives = driveDao.getAllDrives();

		assertEquals(drives.size(), 1);
		assertTrue(drive1.equals(drives.get(0)));

		driveDao.insertDrive(drive2);

		drives = driveDao.getAllDrives();

		assertEquals(drives.size(), 2);
		assertTrue(drive1.equals(drives.get(0)));
		assertTrue(drive2.equals(drives.get(1)));
	}

	@Test
	public void getDriveById() throws HibernateErrorException {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.insertDrive(drive1);

		Drive drive = driveDao.getDrive(drive1.getId());

		assertTrue(drive1.equals(drive));
		try {
			assertEquals(null, driveDao.getDrive(drive2.getId()));
			fail("Finding drive without id, test should fail");
		} catch (InvalidDataAccessApiUsageException e) {
		}

		driveDao.insertDrive(drive2);

		assertTrue(drive1.equals(driveDao.getDrive(drive1.getId())));
		assertTrue(drive2.equals(driveDao.getDrive(drive2.getId())));
	}

	@Test
	public void getDriveByEmployee() throws HibernateErrorException {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();
		Employee employee = drive1.getEmployee();

		driveDao.insertDrive(drive1);

		List<Drive> drives = driveDao.getAllDrivesByEmployee(employee);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		employee = drive2.getEmployee();

		driveDao.insertDrive(drive2);

		drives = driveDao.getAllDrivesByEmployee(employee);

		assertEquals(1, drives.size());
		assertTrue(drive2.equals(drives.get(0)));
	}

	@Test
	public void getDriveByVehicle() throws HibernateErrorException {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		Vehicle vehicle1 = drive1.getVehicle();
		Vehicle vehicle2 = drive2.getVehicle();

		driveDao.insertDrive(drive1);

		List<Drive> drives = driveDao.getAllDrivesByVehicle(vehicle1);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		driveDao.insertDrive(drive2);

		drives = driveDao.getAllDrivesByVehicle(vehicle1);

		assertEquals(1, drives.size());
		assertTrue(drive1.equals(drives.get(0)));

		drives = driveDao.getAllDrivesByVehicle(vehicle2);

		assertEquals(1, drives.size());
		assertTrue(drive2.equals(drives.get(0)));
	}

	@Test
	public void updateDrive() throws HibernateErrorException {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.insertDrive(drive1);
		driveDao.insertDrive(drive2);

		drive1.setKm(new BigDecimal(13216));
		driveDao.updateDrive(drive1);

		assertTrue(new BigDecimal(13216).compareTo(driveDao.getDrive(drive1.getId()).getKm()) == 0);

		drive1.setState(DriveStatus.CANCELLED);
		driveDao.updateDrive(drive1);

		assertEquals(DriveStatus.CANCELLED, driveDao.getDrive(drive1.getId()).getState());

		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 1, 1);
		drive1.setStartDate(calendar.getTime());
		driveDao.updateDrive(drive1);

		assertTrue(calendar.getTime().equals(driveDao.getDrive(drive1.getId()).getStartDate()));

		calendar = new GregorianCalendar();
		calendar.set(2015, 2, 2);
		drive1.setEndDate(calendar.getTime());
		driveDao.updateDrive(drive1);

		assertTrue(calendar.getTime().equals(driveDao.getDrive(drive1.getId()).getEndDate()));

		Employee employee = createEmployee("NewFirstname", "NewLastname");
		drive1.setEmployee(employee);

		driveDao.updateDrive(drive1);

		assertTrue(employee.equals(driveDao.getDrive(drive1.getId()).getEmployee()));

		assertTrue(drive2.equals(driveDao.getDrive(drive2.getId())));
	}

	@Test
	public void deleteDrive() throws HibernateErrorException {

		Drive drive1 = prepareDrive1();
		Drive drive2 = prepareDrive2();

		driveDao.insertDrive(drive1);
		driveDao.insertDrive(drive2);

		assertEquals(2, driveDao.getAllDrives().size());

		driveDao.deleteDrive(drive1);

		assertEquals(1, driveDao.getAllDrives().size());
		assertEquals(null, driveDao.getDrive(drive1.getId()));
		assertTrue(drive2.equals(driveDao.getDrive(drive2.getId())));

		drive1 = prepareDrive3();

		driveDao.insertDrive(drive1);

		driveDao.deleteDrive(drive2);

		assertEquals(1, driveDao.getAllDrives().size());
		assertTrue(drive1.equals(driveDao.getDrive(drive1.getId())));
		assertEquals(null, driveDao.getDrive(drive2.getId()));

		driveDao.deleteDrive(drive1);

		assertEquals(0, driveDao.getAllDrives().size());
		assertEquals(null, driveDao.getDrive(drive1.getId()));
		assertEquals(null, driveDao.getDrive(drive2.getId()));
	}

	private Employee createEmployee(String firstname, String lastname) throws HibernateErrorException {
		Employee employee = new Employee(firstname, lastname, "email@email.com", "0958421547", "role", new BigDecimal(5000), Category.SILVER, new ArrayList<Drive>());
		employeeDao.create(employee);

		return employee;
	}

	private Vehicle createVehicle(String vin) throws HibernateErrorException {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("brand");
		vehicle.setDrives(new ArrayList<Drive>());
		vehicle.setEngineType("engine");
		vehicle.setMaxMileage(1000000L);
		vehicle.setMileage(10000L);
		vehicle.setModel("model");
		vehicle.setEngineType("diesel");
		vehicle.setType("type");
		vehicle.setVin(vin);
		vehicle.setServiceChecks(new ArrayList<ServiceCheck>());
		vehicle.setServiceInterval(365);
		vehicleDao.insert(vehicle);

		return vehicle;
	}

	private Drive prepareDrive1() throws HibernateErrorException {
		Drive drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive1.setEndDate(calendar.getTime());
		drive1.setState(DriveStatus.APPROVED);
		drive1.setVehicle(createVehicle(vin1));
		drive1.setEmployee(createEmployee("Firstname1", "Lastname1"));
		drive1.setKm(new BigDecimal(10000));

		return drive1;
	}

	private Drive prepareDrive2() throws HibernateErrorException {

		Drive drive2 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive2.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive2.setEndDate(calendar.getTime());
		drive2.setState(DriveStatus.COMPLETED);
		drive2.setVehicle(createVehicle(vin2));
		drive2.setEmployee(createEmployee("Firstname2", "Lastname2"));
		drive2.setKm(new BigDecimal(10000));

		return drive2;
	}

	private Drive prepareDrive3() throws HibernateErrorException {
		Drive drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive1.setEndDate(calendar.getTime());
		drive1.setState(DriveStatus.CANCELLED);
		drive1.setVehicle(createVehicle("FJAO836FE093G"));
		drive1.setEmployee(createEmployee("Firstname3", "Lastname3"));
		drive1.setKm(new BigDecimal(10000));

		return drive1;
	}
}