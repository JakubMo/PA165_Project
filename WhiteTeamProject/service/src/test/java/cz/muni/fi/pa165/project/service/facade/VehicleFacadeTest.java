package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.VehicleService;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;


import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link VehicleFacade}
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class VehicleFacadeTest {

	@Mock
	private BeanMappingService beanMappingService;

	@Mock
	private VehicleService vehicleService;

	@Inject
	@InjectMocks
	private VehicleFacadeImpl vehicleFacade;

	private String vin1 = "IPK204t4FG";

	private VehicleCreateDTO vehicleCreateDTO;
	private VehicleDTO vehicleDTO;
	private Vehicle vehicle;

	@BeforeMethod //has to be reseted every time, otherwise verify() can behave abnormaly
	public void init() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void prepare() {
		this.vehicleCreateDTO = this.prepareVehicleCreateDTO();
		this.vehicleDTO = this.prepareVehicleDTO();
		this.vehicle = this.prepareVehicle();
	}

	@Test
	public void createVehicleTest() {
		this.vehicleFacade.createVehicle(vehicleCreateDTO);
		verify(beanMappingService).mapTo(vehicleCreateDTO, Vehicle.class);
		verify(vehicleService).createVehicle(any());
	}

	@Test
	public void updateServiceCheckIntervalTest(){
		this.vehicleFacade.updateServiceCheckInterval(vehicleDTO.getId(), 60);
		verify(vehicleService).updateServiceCheckInterval(anyObject(), anyInt());
	}

	@Test
	public void updateMaxMileage(){
		this.vehicleFacade.updateMaxMileage(vehicleDTO.getId(), 60000L);
		verify(vehicleService).updateMaxMileage(anyObject(), anyLong());
	}

	@Test
	public void deleteVehicleTest(){
		this.vehicleFacade.deleteVehicle(vehicleDTO.getId());
		verify(vehicleService).deleteVehicle(any());
	}

	@Test
	public void getAllByModelTest(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);

		when(vehicleService.getAllByModel(any())).thenReturn(vehicles);
		vehicleFacade.getAllByModel(vehicleDTO.getModel());

		verify(vehicleService).getAllByModel(any());
		verify(beanMappingService).mapTo(vehicles, VehicleDTO.class);
	}

	@Test
	public void getAllByBrandTest(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);

		when(vehicleService.getAllByBrand(any())).thenReturn(vehicles);
		vehicleFacade.getAllByBrand(vehicleDTO.getModel());

		verify(vehicleService).getAllByBrand(any());
		verify(beanMappingService).mapTo(vehicles, VehicleDTO.class);
	}

	@Test
	public void getAllByMileageTest(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);

		when(vehicleService.getAllByMileage(any())).thenReturn(vehicles);
		vehicleFacade.getAllByMileage(100000L);

		verify(vehicleService).getAllByMileage(any());
		verify(beanMappingService).mapTo(vehicles, VehicleDTO.class);
	}

	@Test
	public void getAllTest(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);

		when(vehicleService.getAll()).thenReturn(vehicles);
		vehicleFacade.getAll();

		verify(vehicleService).getAll();
		verify(beanMappingService).mapTo(vehicles, VehicleDTO.class);
	}

	@Test
	public void getAllFreeInDateTest(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);

		when(vehicleService.getAllFreeInDate(any(), any())).thenReturn(vehicles);
		vehicleFacade.getAllFreeInDate(new java.util.Date(115, 4,5), new java.util.Date(115, 4,6));

		verify(vehicleService).getAllFreeInDate(any(), any());
		verify(beanMappingService).mapTo(vehicles, VehicleDTO.class);
	}

	private Vehicle prepareVehicle(){
		Vehicle v = new Vehicle();
		v.setBrand("Mazda");
		v.setEngineType("Diesel");
		v.setMaxMileage(100000L);
		v.setMileage(20000L);
		v.setModel("3");
		v.setServiceCheckInterval(100);
		v.setType("type1");
		v.setVin(this.vin1);
		v.setYearOfProduction(2014);
		return v;
	}

	private VehicleDTO prepareVehicleDTO(){
		VehicleDTO vdto = new VehicleDTO();
		vdto.setId(100L);
		vdto.setBrand("Mazda");
		vdto.setEngineType("Diesel");
		vdto.setMaxMileage(100000L);
		vdto.setMileage(20000L);
		vdto.setModel("3");
		vdto.setServiceCheckInterval(100);
		vdto.setType("type1");
		vdto.setVin(this.vin1);
		vdto.setYearOfProduction(2014);
		return vdto;
	}

	private VehicleCreateDTO prepareVehicleCreateDTO(){
		VehicleCreateDTO vdto = new VehicleCreateDTO();
		vdto.setBrand("Mazda");
		vdto.setEngineType("Diesel");
		vdto.setMaxMileage(100000L);
		vdto.setMileage(20000L);
		vdto.setModel("3");
		vdto.setServiceCheckInterval(100);
		vdto.setType("type1");
		vdto.setVin(this.vin1);
		vdto.setYearOfProduction(2014);
		return vdto;
	}

}
