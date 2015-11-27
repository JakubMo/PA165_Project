package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.DriveDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.DriveService;
import cz.muni.fi.pa165.project.service.VehicleService;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link DriveFacade}.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/27/15.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriveFacadeTest {

	@Mock
	private BeanMappingService beanMappingService;

	@Mock
	private DriveService driveService;

	@Mock
	private VehicleService vehicleService;

	@Inject
	@InjectMocks
	private DriveFacadeImpl driveFacade;

	private DriveDTO driveDTO;
	private Drive drive1;

	@BeforeMethod //has to be reseted every time, otherwise verify() can behave abnormaly
	public void prepare() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void prepare_objects() throws ParseException {
		driveDTO = createDriveDTO();
		drive1 = createDrive1();
	}

	@Test
	public void findAllTest() {
		driveFacade.findAll();
		verify(beanMappingService).mapTo(driveService.findAll(), DriveDTO.class);
	}

	@Test
	public void findByIdTest() {
		when(driveService.findById(1L)).thenReturn(drive1);

		DriveDTO driveDTO = driveFacade.findById(1L);

		verify(driveService).findById(1L);
		verify(beanMappingService).mapTo(drive1, DriveDTO.class);
	}

	@Test
	public void cancelDriveTest() {
		driveDTO.setId(1L);

		driveFacade.cancelDrive(driveDTO.getId());
		verify(driveService).cancelDrive(any());
	}

	@Test
	public void approveDriveTest() {
		driveFacade.approveDrive(driveDTO.getId());
		verify(driveService).approveDrive(any());
	}

	@Test
	public void changeDriveKmTest() {
		driveFacade.changeDrivenKilometers(driveDTO.getId(), new BigDecimal(1000));
		verify(driveService).changeDrivenKilometers(any(), any());
	}

	@Test
	public void delete() {
		driveFacade.deleteDrive(driveDTO.getId());
		verify(driveService).deleteDrive(any());
	}

	@Test
	public void changeDriveEndDateTest() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		driveFacade.changeEndDate(driveDTO.getId(), simpleDateFormat.parse("12-12-2015"));
		verify(driveService).changeEndDate(any(), any());
	}

	@Test
	public void changeDriveStartDateTest() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		driveFacade.changeStartDate(driveDTO.getId(), simpleDateFormat.parse("11-10-2015"));
		verify(driveService).changeStartDate(any(), any());
	}

	@Test
	public void findAllDrivesByVehicleTest() {
		VehicleDTO vehicleDTO = createVehicleDTO("vin");
		driveFacade.findAllByVehicle(vehicleDTO);
		verify(driveService).findAllByVehicle(any());
	}

	@Test
	public void findAllDrivesByEmployeeTest() {
		EmployeeDTO employeeDTO = createEmployeeDTO("FirstnameDTO", "LastNameDTO");
		driveFacade.findAllByEmployee(employeeDTO);
		verify(driveService).findAllByEmployee(any());
	}

	@Test
	public void findAllDrivesByStatusTest() {
		driveFacade.findAllByStatus(DriveStatus.COMPLETED);
		verify(driveService).findAllByStatus(any());
	}

	@Test
	public void findAllDrivesByTimeIntervalTest() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		driveFacade.findAllByTimeInterval(simpleDateFormat.parse("10-10-2014"), simpleDateFormat.parse("10-10-2015"));
		verify(driveService).findAllByTimeInterval(any(), any());
	}

	private DriveDTO createDriveDTO() throws ParseException {
		DriveDTO driveDTO = new DriveDTO();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		driveDTO.setId(1L);
		driveDTO.setDriveStatus(DriveStatus.APPROVED);
		driveDTO.setStartDate(simpleDateFormat.parse("10-10-2015"));
		driveDTO.setEndDate(simpleDateFormat.parse("11-12-2015"));
		driveDTO.setKm(new BigDecimal(50000));
		driveDTO.setEmployee(new EmployeeDTO());
		driveDTO.setVehicle(new VehicleDTO());
		return driveDTO;
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

	private VehicleDTO createVehicleDTO(String vin) {
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setBrand("brand");
		vehicleDTO.setEngineType("engine");
		vehicleDTO.setMaxMileage(1000000L);
		vehicleDTO.setMileage(10000L);
		vehicleDTO.setModel("model");
		vehicleDTO.setEngineType("diesel");
		vehicleDTO.setType("type");
		vehicleDTO.setVin(vin);
		vehicleDTO.setServiceCheckInterval(365);
		return vehicleDTO;
	}

	private EmployeeDTO createEmployeeDTO(String firstname, String lastname) throws DataAccessExceptionImpl {
		return new EmployeeDTO(firstname, lastname, "email@email.com", "0958421547", "role", new BigDecimal(5000), Category.SILVER, new ArrayList<>());
	}
}
