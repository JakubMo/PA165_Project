package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.*;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Test for Drive entity
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 30.10.2015
 */
public class DriveTest {

	private Drive drive1;
	private Drive drive2;

	private Vehicle vehicle1;

	private Employee employee1;
	private Employee employee2;

	@BeforeClass
	public void init() throws HibernateErrorException {

		vehicle1 = new Vehicle();
		vehicle1.setVin("v1Vin");
		vehicle1.setModel("Mustang");
		vehicle1.setBrand("Ford");
		vehicle1.setType("5 door");
		vehicle1.setYearOfProduction(2009);
		vehicle1.setEngineType("petrol");
		vehicle1.setMaxMileage(200000L);
		vehicle1.setMileage(10000L);
		vehicle1.setServiceInterval(150);

		VehicleDao vehicleDao = new VehicleDaoImpl();
		vehicleDao.insert(vehicle1);


		employee1 = new Employee();
		employee1.setId(1L);
		employee1.setFirstname("Jozko");
		employee1.setLastname("Mrkvicka");
		employee1.setEmail("jozkomrkvicka@firma.mail");
		employee1.setPhoneNumber("0987 654 321");
		employee1.setRole("user");
		employee1.setCredit(new BigDecimal("1230.50"));
		employee1.setCategory(Category.SILVER);

		employee2 = new Employee();
		employee2.setId(2L);
		employee2.setFirstname("Janko");
		employee2.setLastname("Hrasko");
		employee2.setEmail("hraskoj@firma.mail");
		employee2.setPhoneNumber("0963 852 741");
		employee2.setRole("admin");
		employee2.setCredit(new BigDecimal("45700.00"));
		employee2.setCategory(Category.PLATINUM);

		EmployeeDao employeeDao = new EmployeeDaoImpl();
		employeeDao.create(employee1);
		employeeDao.create(employee2);

		drive1 = new Drive();
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 10, 4);
		drive1.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 4);
		drive1.setEndDate(calendar.getTime());
		drive1.setState(DriveStatus.COMPLETED);
		drive1.setVehicle(vehicle1);
		drive1.setEmployee(employee1);
		drive1.setKm(new BigDecimal(100L));

		drive2 = new Drive();
		calendar = new GregorianCalendar();
		calendar.set(2015, 10, 6);
		drive2.setStartDate(calendar.getTime());
		calendar.set(2015, 10, 8);
		drive2.setEndDate(calendar.getTime());
		drive2.setState(DriveStatus.COMPLETED);
		drive2.setVehicle(vehicle1);
		drive2.setEmployee(employee2);
		drive2.setKm(new BigDecimal(300L));
	}

	@Test(priority = 1)
	public void createDriveTest(){
		try {
			DriveDao driveDao = new DriveDaoImpl();


			List<Drive> driveList = driveDao.getAllDrives();

			Assert.assertEquals(driveList.size(), 0);

			driveDao.insertDrive(drive1);
			driveDao.insertDrive(drive2);

			driveList = driveDao.getAllDrives();
			Assert.assertEquals(driveList.size(), 2);

		} catch (HibernateErrorException e) {
			Assert.fail();
		}
	}


	@Test(priority = 2)
	public void findDriveTest(){
		try{
			DriveDao driveDao = new DriveDaoImpl();

			Drive d1 = driveDao.getDrive(this.drive1.getId());
			Drive d2 = driveDao.getDrive(this.drive2.getId());

			Assert.assertEquals(d1, this.drive1);
			Assert.assertEquals(d2, this.drive2);
			Assert.assertNotEquals(d1, this.drive2);


			List<Drive> vehicleDrives = driveDao.getAllDrivesByVehicle(this.vehicle1);
			Assert.assertEquals(vehicleDrives.size(), 2);

			List<Drive> employeeDrives = driveDao.getAllDrivesByEmployee(this.employee2);
			Assert.assertEquals(employeeDrives.size(), 1);


		} catch (HibernateErrorException e) {
			Assert.fail();
		}
	}

	@Test(priority = 3)
	public void updateDriveTest(){
		try{
			DriveDao driveDao = new DriveDaoImpl();

			Drive d1 = driveDao.getDrive(this.drive1.getId());
			Assert.assertEquals(d1, this.drive1);

			d1.setState(DriveStatus.CANCELLED);

			driveDao.updateDrive(d1);

			Drive drive = driveDao.getDrive(this.drive1.getId());
			Assert.assertNotEquals(drive, this.drive1);

			drive.setEmployee(this.employee2);
			driveDao.updateDrive(drive);
			List<Drive> employeesDrives = driveDao.getAllDrivesByEmployee(this.employee1);
			Assert.assertEquals(employeesDrives.size(), 0);

		} catch (HibernateErrorException e) {
			Assert.fail();
		}
	}

	@Test(priority = 4)
	public void deleteDriveTest(){
		try{
			DriveDao driveDao = new DriveDaoImpl();

			List<Drive> drives = driveDao.getAllDrives();
			Assert.assertEquals(drives.size(), 2);

			Drive d1 = driveDao.getDrive(this.drive1.getId());

			driveDao.deleteDrive(d1);
			drives = driveDao.getAllDrives();
			Assert.assertEquals(drives.size(), 1);

			d1 = driveDao.getDrive(this.drive1.getId());
			Assert.assertNotEquals(d1, drive1);

		} catch (HibernateErrorException e) {
			Assert.fail();
		}
	}

	@Test(priority = 5, expectedExceptions=HibernateException.class)
	public void secondaryDeleteDriveTest(){
		try{
			DriveDao driveDao = new DriveDaoImpl();

			Drive drive = driveDao.getDrive(this.drive2.getId());

			driveDao.deleteDrive(drive);
			driveDao.updateDrive(drive);

		} catch (HibernateErrorException e) {
			throw new HibernateException(e);
		}
	}

}