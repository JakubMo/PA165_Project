package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


/**
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 26.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class VehicleServiceTest {

	@Mock
	private VehicleDao vehicleDao;

	@Inject
	@InjectMocks
	private VehicleServiceImpl vehicleService;

	@BeforeClass
	public void prepare() throws ServiceException{
		MockitoAnnotations.initMocks(this);
	}


	private String vin1 = "IPK204t4FG";
	private String vin2 = "DRH244yOKS";
	private String vin3 = "HJY83HJSA2";

	private Vehicle vehicle1;
	private Vehicle vehicle2;
	private Vehicle vehicle3;


	@BeforeMethod
	public void prepareVehicles(){
		this.vehicle1 = new Vehicle();
		vehicle1.setVin(vin1);
		vehicle1.setModel("Astra");
		vehicle1.setBrand("Opel");
		vehicle1.setType("type1");
		vehicle1.setYearOfProduction(2012);
		vehicle1.setEngineType("gasoline");
		vehicle1.setMaxMileage(400000L);
		vehicle1.setMileage(45000L);
		vehicle1.setServiceCheckInterval(365);

		this.vehicle2 = new Vehicle();
		vehicle2.setVin(vin2);
		vehicle2.setModel("Astra");
		vehicle2.setBrand("Opel");
		vehicle2.setType("type1");
		vehicle2.setYearOfProduction(2005);
		vehicle2.setEngineType("diesel");
		vehicle2.setMaxMileage(110000L);
		vehicle2.setMileage(10000L);
		vehicle2.setServiceCheckInterval(200);

		this.vehicle3 = new Vehicle();
		vehicle3.setVin(vin3);
		vehicle3.setModel("3");
		vehicle3.setBrand("Mazda");
		vehicle3.setType("type2");
		vehicle3.setYearOfProduction(2007);
		vehicle3.setEngineType("diesel");
		vehicle3.setMaxMileage(110000L);
		vehicle3.setMileage(30000L);
		vehicle3.setServiceCheckInterval(200);
	}

	@Test
	public void testGetById() throws DataAccessException {
		when(vehicleDao.get(1L)).thenReturn(this.vehicle1);

		Vehicle v1 = this.vehicleService.getById(1L);
		verify(this.vehicleDao).get(1L);
		verifyNoMoreInteractions(this.vehicleDao);

		Assert.assertEquals(v1, this.vehicle1);
	}

	@Test
	public void testGetByVin() throws DataAccessException {
		when(vehicleDao.getByVin(this.vin2)).thenReturn(this.vehicle2);

		Vehicle v1 = this.vehicleService.getByVin(this.vin2);
		verify(this.vehicleDao).getByVin(this.vin2);
		verifyNoMoreInteractions(this.vehicleDao);

		Assert.assertEquals(v1, this.vehicle2);
	}

	@Test
	public void testGetAll() throws DataAccessException {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(this.vehicle1);
		vehicles.add(this.vehicle2);
		vehicles.add(this.vehicle3);
		when(vehicleDao.getAll()).thenReturn(vehicles);

		List<Vehicle> foundVehicles = this.vehicleService.getAll();
		verify(this.vehicleDao).getAll();

		Assert.assertEquals(foundVehicles, vehicles);
	}

	@Test
	public void testGetAllByModel() throws DataAccessException {
		String model = "Astra";
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(this.vehicle1);
		vehicles.add(this.vehicle2);
		when(vehicleDao.getAllByModel(model)).thenReturn(vehicles);

		List<Vehicle> foundVehicles = this.vehicleService.getAllByModel(model);
		verify(this.vehicleDao).getAllByModel(model);
		verifyNoMoreInteractions(this.vehicleDao);

		Assert.assertEquals(foundVehicles, vehicles);
	}

	@Test
	public void testGetAllByBrand() throws DataAccessException {
		String brand = "Mazda";
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(this.vehicle3);
		when(vehicleDao.getAllByBrand(brand)).thenReturn(vehicles);

		List<Vehicle> foundVehicles = this.vehicleService.getAllByBrand(brand);
		verify(this.vehicleDao).getAllByBrand(brand);
		verifyNoMoreInteractions(this.vehicleDao);

		Assert.assertEquals(foundVehicles, vehicles);
	}

	@Test
	public void tetGetAllByMileage() throws DataAccessException {

		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(this.vehicle2);
		vehicles.add(this.vehicle3);
		when(vehicleDao.getAllByMileage(31000L)).thenReturn(vehicles);
		List<Vehicle> foundVehicles = this.vehicleService.getAllByMileage(31000L);

		verify(this.vehicleDao).getAllByMileage(31000L);
		verifyNoMoreInteractions(this.vehicleDao);
		Assert.assertEquals(foundVehicles, vehicles);
	}

}
