package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.*;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Unit tests for Drive entity
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/30/15.
 */
public class DriveTest {

	private Drive drive1;
	private Drive drive2;
	private final String vin1 = "KDJG454SD4DS";
	private final String vin2 = "REWGF69849SADF";

	@BeforeClass
	public void initializeDrive() {
		drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 9, 9);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 10);
		drive1.setEndDate(calendar.getTime());
		drive1.setState(DriveStatus.APPROVED);
		drive1.setVehicle(createVehicle(vin1));
		drive1.setEmployee(createEmployee("Firstname", "Lastname"));
		drive1.setKm(new BigDecimal(10000));

		drive2 = new Drive();
		calendar = new GregorianCalendar();
		calendar.set(2015, 8, 6);
		drive2.setStartDate(calendar.getTime());
		calendar.set(2015, 9, 4);
		drive2.setEndDate(calendar.getTime());
		drive2.setState(DriveStatus.COMPLETED);
		drive2.setVehicle(createVehicle(vin2));
		drive2.setEmployee(createEmployee("Firstname", "Lastname"));
		drive2.setKm(new BigDecimal(10000));
	}

	@Test
	public void createDrive() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(createVehicle(vin1));
		driveDao.insertDrive(drive1);
		driveDao.insertDrive(drive2);
		try {
			driveDao.insertDrive(new Drive());
			fail("Drive attributes are null, exception should be thrown");
		} catch (Exception e) {
		}
	}

	@Test
	public void getAllDrives() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		List<Drive> drives = driveDao.getAllDrives();

		assertEquals(drives.size(), 0);

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(createVehicle(vin1));

		try {
			driveDao.insertDrive(drive1);
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		drives = driveDao.getAllDrives();

		assertEquals(drives.size(), 2);
		assertEquals(drive1, drives.get(0));
		assertEquals(drive2, drives.get(1));

		try {
			driveDao.insertDrive(new Drive());
		} catch (Exception e) {
		}

		drives = driveDao.getAllDrives();
		assertEquals(drives.size(), 2);
		assertEquals(drive1, drives.get(0));
		assertEquals(drive2, drives.get(1));
	}

	@Test
	public void getDriveById() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(createVehicle(vin1));
		try {
			driveDao.insertDrive(drive1);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		assertEquals(drive1, driveDao.getDrive(drive1.getId()));
		assertEquals(null, driveDao.getDrive(drive2.getId()));

		try {
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		assertEquals(drive2, driveDao.getDrive(drive2.getId()));
		assertEquals(drive1, driveDao.getDrive(drive1.getId()));
	}

	@Test
	public void getDriveByEmployee() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		Employee employee = createEmployee("Firstname", "Lastname");

		employeeDao.create(employee);
		vehicleDao.insert(createVehicle(vin1));

		try {
			driveDao.insertDrive(drive1);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		List<Drive> drives = driveDao.getAllDrivesByEmployee(employee);

		assertEquals(1, drives.size());
		assertEquals(drive1, drives.get(0));

		try {
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		drives = driveDao.getAllDrivesByEmployee(employee);

		assertEquals(2, drives.size());
		assertEquals(drive1, drives.get(0));
		assertEquals(drive2, drives.get(1));
	}

	@Test
	public void getDriveByVehicle() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		Vehicle vehicle = createVehicle(vin1);

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(vehicle);

		try {
			driveDao.insertDrive(drive1);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		List<Drive> drives = driveDao.getAllDrivesByVehicle(vehicle);

		assertEquals(1, drives.size());
		assertEquals(drive1, drives.get(0));

		try {
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		drives = driveDao.getAllDrivesByVehicle(vehicle);

		assertEquals(1, drives.size());
		assertEquals(drive1, drives.get(0));
	}

	@Test
	public void updateDrive() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(createVehicle(vin1));

		try {
			driveDao.insertDrive(drive1);
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		drive1.setKm(new BigDecimal(13216));
		driveDao.updateDrive(drive1);
		try {
			assertEquals(new BigDecimal(13216), driveDao.getDrive(drive1.getId()).getKm());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		drive1.setState(DriveStatus.CANCELLED);
		driveDao.updateDrive(drive1);
		try {
			assertEquals(DriveStatus.CANCELLED, driveDao.getDrive(drive1.getId()).getState());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 2, 2);
		drive1.setStartDate(calendar.getTime());
		driveDao.updateDrive(drive1);
		try {
			assertEquals(calendar.getTime(), driveDao.getDrive(drive1.getId()).getStartDate());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		calendar = new GregorianCalendar();
		calendar.set(2015, 2, 2);
		drive1.setStartDate(calendar.getTime());
		driveDao.updateDrive(drive1);
		try {
			assertEquals(calendar.getTime(), driveDao.getDrive(drive1.getId()).getStartDate());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		Employee employee = createEmployee("NewLastname", "NewFirstname");
		drive1.setEmployee(employee);
		driveDao.updateDrive(drive1);
		try {
			assertEquals(employee, driveDao.getDrive(drive1.getId()).getEmployee());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		String newVin = "GFUKC56846FE";
		Vehicle vehicle = createVehicle(newVin);
		drive1.setVehicle(vehicle);
		driveDao.updateDrive(drive1);
		try {
			assertEquals(vehicle, driveDao.getDrive(drive1.getId()).getVehicle());
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		try {
			assertEquals(drive2, driveDao.getDrive(drive2.getId()));
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}
		;
	}

	@Test
	public void deleteDrive() throws HibernateErrorException {
		DriveDao driveDao = new DriveDaoImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		VehicleDao vehicleDao = new VehicleDaoImpl();

		employeeDao.create(createEmployee("Firstname", "Lastname"));
		vehicleDao.insert(createVehicle(vin1));

		try {
			driveDao.insertDrive(drive1);
			driveDao.insertDrive(drive2);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		driveDao.deleteDrive(drive1);

		try {
			assertEquals(null, driveDao.getDrive(drive1.getId()));
			assertEquals(drive2, driveDao.getDrive(drive2.getId()));
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		try {
			driveDao.insertDrive(drive1);
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		driveDao.deleteDrive(drive2);

		try {
			assertEquals(drive1, driveDao.getDrive(drive1.getId()));
			assertEquals(null, driveDao.getDrive(drive2.getId()));
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}

		driveDao.deleteDrive(drive1);

		try {
			assertEquals(null, driveDao.getDrive(drive1.getId()));
			assertEquals(null, driveDao.getDrive(drive2.getId()));
		} catch (HibernateErrorException e) {
			e.printStackTrace();
		}
	}

	private Vehicle createVehicle(String vin) {
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
		vehicle.setServiceInterval(365);
		return vehicle;
	}

	private Employee createEmployee(String firstname, String lastname) {
		return new Employee(1L, firstname, lastname, "email@email.com", "0958421547", "role", new BigDecimal(5000), Category.SILVER);
	}

}
