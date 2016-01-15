package cz.muni.fi.pa165.project.service;

/**
 * Test class for {@link DriveService}.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/26/15.
 */

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;


import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriveServiceTest extends AbstractTestNGSpringContextTests{

	@Mock
	private DriveDao driveDao;

        @Inject
	@InjectMocks
	private DriveService driveService;

	private List<Drive> allDrives;
	private Drive drive1;
	private Drive drive2;
        private Date drive2StartDate;
        private Date drive2EndDate;

        @BeforeMethod
        public void prepare() throws ServiceException {
            MockitoAnnotations.initMocks(this);
        }
        
	@BeforeMethod
	public void prepareDrives() throws ParseException {
		drive1 = createDrive1();
		drive2 = createDrive2();
		allDrives = new ArrayList<>();
		allDrives.add(drive1);
		allDrives.add(drive2);
	}

	@Test
	public void findDriveByIdTest() {
		when(driveDao.get(0L)).thenReturn(this.drive1);

		Drive result = driveService.findById(0L);
		verify(driveDao).get(0L);
		verifyNoMoreInteractions(driveDao);

		assertEquals(result, drive1);
	}

	@Test
	public void findAllTest() {
		when(driveDao.getAll()).thenReturn(allDrives);

		List<Drive> drives = (List<Drive>) driveService.findAll();

		verify(driveDao).getAll();
		assertEquals(drives, allDrives);
	}

	@Test
	public void findByEmployeeTest() {
		Employee employee1 = createEmployee("FirstName1", "LastName1");
		Employee employee2 = createEmployee("FirstName2", "LastName2");
		List<Drive> drive1List = Collections.singletonList(drive1);
		when(driveDao.getAllByEmployee(employee1)).thenReturn(allDrives);
		when(driveDao.getAllByEmployee(employee2)).thenReturn(drive1List);

		List<Drive> result = (List<Drive>) driveService.findAllByEmployee(employee1);
		verify(driveDao).getAllByEmployee(employee1);

		assertEquals(allDrives, result);

		result = (List<Drive>) driveService.findAllByEmployee(employee2);
		verify(driveDao).getAllByEmployee(employee2);
		assertEquals(drive1List, result);
	}

	@Test
	public void findByVehicle() {
		Vehicle vehicle1 = createVehicle("dsfa");

		Vehicle vehicle2 = createVehicle("ASDG");
		List<Drive> drive2List = Collections.singletonList(drive2);

		when(driveDao.getAllByVehicle(vehicle1)).thenReturn(allDrives);
		when(driveDao.getAllByVehicle(vehicle2)).thenReturn(drive2List);

		List<Drive> result = (List<Drive>) driveService.findAllByVehicle(vehicle1);
		verify(driveDao).getAllByVehicle(vehicle1);
		assertEquals(allDrives, result);

		result = (List<Drive>) driveService.findAllByVehicle(vehicle2);
		verify(driveDao).getAllByVehicle(vehicle2);
		assertEquals(drive2List, result);
	}

	@Test
	public void findByTimeInterval() {
		Date startDate1 = new Date(1000000L);
		Date endDate1 = new Date(1500000L);
		Date startDate2 = new Date(2000000L);
		Date endDate2 = new Date(2500000L);

		List<Drive> drive1List = Collections.singletonList(drive1);

		when(driveDao.getAllDrivesByTimeInterval(startDate1, endDate1)).thenReturn(allDrives);
		when(driveDao.getAllDrivesByTimeInterval(startDate2, endDate2)).thenReturn(drive1List);

		List<Drive> result = (List<Drive>) driveService.findAllByTimeInterval(startDate1, endDate1);
		verify(driveDao).getAllDrivesByTimeInterval(startDate1, endDate1);
		assertEquals(allDrives, result);

		result = (List<Drive>) driveService.findAllByTimeInterval(startDate2, endDate2);
		verify(driveDao).getAllDrivesByTimeInterval(startDate2, endDate2);
		assertEquals(drive1List, result);
	}

	@Test
	public void findByStatusTest() {
		List<Drive> expectedResult = Collections.EMPTY_LIST;
		when(driveDao.getAll()).thenReturn(expectedResult);

		List<Drive> result = (List<Drive>) driveService.findAllByStatus(DriveStatus.APPROVED);

		assertEquals(0, result.size());

		result = (List<Drive>) driveService.findAllByStatus(DriveStatus.CANCELLED);

		assertEquals(0, result.size());

		expectedResult = Collections.singletonList(drive1);
		when(driveDao.getAll()).thenReturn(expectedResult);
		result = (List<Drive>) driveService.findAllByStatus(DriveStatus.COMPLETED);

		assertEquals(1, result.size());

		expectedResult = Collections.singletonList(drive2);
		when(driveDao.getAll()).thenReturn(expectedResult);
		result = (List<Drive>) driveService.findAllByStatus(DriveStatus.REQUESTED);

		assertEquals(1, result.size());

		drive1.setDriveStatus(DriveStatus.REQUESTED);
		expectedResult = new ArrayList<>();
		expectedResult.add(drive1);
		expectedResult.add(drive2);
		when(driveDao.getAll()).thenReturn(expectedResult);
		result = (List<Drive>) driveService.findAllByStatus(DriveStatus.REQUESTED);

		assertEquals(2, result.size());

		try {
			driveService.findAllByStatus(null);
			fail("Trying to find drives by null status, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void cancelDriveTest() {
		try {
			driveService.cancelDrive(drive1);
			fail("State transition not allowed, test should fail");
		} catch (IllegalStateException e) {
		}

		assertEquals(drive1.getDriveStatus(), DriveStatus.COMPLETED);

		driveService.cancelDrive(drive2);
		assertEquals(drive2.getDriveStatus(), DriveStatus.CANCELLED);

		try {
			driveService.cancelDrive(drive2);
			fail("State transition not allowed, test should fail");
		} catch (IllegalStateException e) {
		}

		try {
			driveService.cancelDrive(null);
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void completeDrive() throws ParseException {
		try {
			driveService.cancelDrive(drive1);
			fail("State transition not allowed, test should fail");
		} catch (IllegalStateException e) {
		}

		assertEquals(drive1.getDriveStatus(), DriveStatus.COMPLETED);

		drive2.setDriveStatus(DriveStatus.APPROVED);

		try {
			driveService.completeDrive(drive2);
			fail("End date in future, drive shouldn't be completed");
		} catch (IllegalStateException e) {
		}

		assertEquals(drive2.getDriveStatus(), DriveStatus.APPROVED);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		drive2.setStartDate(simpleDateFormat.parse("05-11-2015"));
		drive2.setEndDate(simpleDateFormat.parse("20-11-2015"));

		driveService.completeDrive(drive2);
		assertEquals(drive2.getDriveStatus(), DriveStatus.COMPLETED);

		try {
			driveService.completeDrive(drive2);
			fail("Drive already completed, test should fail");
		} catch (IllegalStateException e) {
		}

		try {
			driveService.completeDrive(null);
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void approveDrive() throws ParseException {
		try {
			driveService.approveDrive(drive1);
			fail("State transition not allowed, test should fail");
		} catch (IllegalStateException e) {
		}

		assertEquals(drive1.getDriveStatus(), DriveStatus.COMPLETED);

		driveService.approveDrive(drive2);
		assertEquals(drive2.getDriveStatus(), DriveStatus.APPROVED);

		try {
			driveService.approveDrive(drive2);
			fail("Drive already approved, test should fail");
		} catch (IllegalStateException e) {
		}

		try {
			driveService.approveDrive(null);
			fail("Drive already approved, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void changeDrivenKilometersTest() {
		driveService.changeDrivenKilometers(drive1, new BigDecimal(1000));
		assertEquals(new BigDecimal(1000), drive1.getKm());

		try {
			driveService.changeDrivenKilometers(drive1, new BigDecimal(-1));
			fail("Trying to change drive km to negative value, test should fail");
		} catch (IllegalArgumentException e) {
		}

		assertEquals(new BigDecimal(1000), drive1.getKm());

		driveService.changeDrivenKilometers(drive1, BigDecimal.ZERO);
		assertEquals(BigDecimal.ZERO, drive1.getKm());

		driveService.changeDrivenKilometers(drive1, BigDecimal.ONE);
		assertEquals(BigDecimal.ONE, drive1.getKm());

		assertEquals(new BigDecimal(120000), drive2.getKm());

		try {
			driveService.changeDrivenKilometers(null, new BigDecimal(10));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeDrivenKilometers(drive1, null);
			fail("Km is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeDrivenKilometers(null, new BigDecimal(-10));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void changeEndDate() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			driveService.changeEndDate(drive1, simpleDateFormat.parse("12-05-2014"));
			fail("End date before start date, test should fail");
		} catch (IllegalArgumentException e) {
		}

		assertEquals(simpleDateFormat.parse("11-11-2014"), drive1.getEndDate());

		try {
			driveService.changeEndDate(drive1, null);
			fail("Date is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		assertEquals(simpleDateFormat.parse("11-11-2014"), drive1.getEndDate());

		driveService.changeEndDate(drive1, simpleDateFormat.parse("05-05-2015"));
		assertEquals(simpleDateFormat.parse("05-05-2015"), drive1.getEndDate());

		//assertEquals(simpleDateFormat.parse("20-12-2015"), drive2.getEndDate());
                assertEquals(drive2EndDate, drive2.getEndDate());

		try {
			driveService.changeEndDate(null, simpleDateFormat.parse("12-05-2014"));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeEndDate(drive1, null);
			fail("End date is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeEndDate(null, simpleDateFormat.parse("12-05-2014"));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void changeStartDate() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			driveService.changeStartDate(drive1, simpleDateFormat.parse("12-12-2015"));
			fail("End date before start date, test should fail");
		} catch (IllegalArgumentException e) {
		}

		assertEquals(simpleDateFormat.parse("10-10-2014"), drive1.getStartDate());

		try {
			driveService.changeStartDate(drive1, null);
			fail("Date is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		assertEquals(simpleDateFormat.parse("10-10-2014"), drive1.getStartDate());

		driveService.changeStartDate(drive1, simpleDateFormat.parse("05-05-2014"));
		assertEquals(simpleDateFormat.parse("05-05-2014"), drive1.getStartDate());

		//assertEquals(simpleDateFormat.parse("05-12-2015"), drive2.getStartDate());
                assertEquals(drive2StartDate, drive2.getStartDate());

		try {
			driveService.changeEndDate(null, simpleDateFormat.parse("12-12-2015"));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeEndDate(drive1, null);
			fail("Start date is null, test should fail");
		} catch (IllegalArgumentException e) {
		}

		try {
			driveService.changeEndDate(null, simpleDateFormat.parse("12-12-2015"));
			fail("Drive is null, test should fail");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void deleteDriveTest() {
		driveService.deleteDrive(drive1);
		verify(driveDao).delete(drive1);
	}

	@Test
	public void createDriveTest() {
		driveService.createDrive(drive1);
		verify(driveDao).create(drive1);
	}

	private Drive createDrive1() throws ParseException {
		Drive drive = new Drive();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		drive.setDriveStatus(DriveStatus.COMPLETED);
		drive.setStartDate(simpleDateFormat.parse("10-10-2014"));
		drive.setEndDate(simpleDateFormat.parse("11-11-2014"));
		drive.setKm(new BigDecimal(80000));
		drive.setEmployee(new Employee());
		drive.setVehicle(new Vehicle());
		return drive;
	}

	private Drive createDrive2() throws ParseException {
		Drive drive = new Drive();
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		drive.setDriveStatus(DriveStatus.REQUESTED);
                Calendar startDate = new GregorianCalendar();
                startDate.add(Calendar.MONTH, -1);
                drive2StartDate = startDate.getTime();
		//drive.setStartDate(simpleDateFormat.parse("05-12-2015"));
                drive.setStartDate(drive2StartDate);
                Calendar endDate = new GregorianCalendar();
                endDate.add(Calendar.DAY_OF_MONTH, 1);
                drive2EndDate = endDate.getTime();
		//drive.setEndDate(simpleDateFormat.parse("20-12-2015"));
                drive.setEndDate(drive2EndDate);
		drive.setKm(new BigDecimal(120000));
		drive.setEmployee(new Employee());
                drive.getEmployee().setCredit(BigDecimal.TEN);
		drive.setVehicle(new Vehicle());
		return drive;
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
		vehicle.setServiceCheckInterval(365);

		return vehicle;
	}

	private Employee createEmployee(String firstname, String lastname) throws DataAccessExceptionImpl {
		return new Employee(firstname, lastname, "email@email.com", "0958421547", "role", new BigDecimal(5000), Category.SILVER, new ArrayList<>(), "SDO##NDF5:<>$DS$#46");
	}
}