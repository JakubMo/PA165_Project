package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.*;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Vehicle test suite
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 30.10.2015.
 */
public class VehicleTest {

	private String vin1 = "IPK204t4FG";
	private String vin2 = "DRH244yOKS";

	private Vehicle vehicle1 = new Vehicle();
	private Vehicle vehicle2 = new Vehicle();

	@BeforeClass
	public void init() {
		VehicleDao vehicleDao = new VehicleDaoImpl();
		vehicle1.setVin(vin1);
		vehicle1.setModel("Model1");
		vehicle1.setBrand("Brand1");
		vehicle1.setType("type1");
		vehicle1.setYearOfProduction(2012);
		vehicle1.setEngineType("gasoline");
		vehicle1.setMaxMileage(400000L);
		vehicle1.setMileage(45000L);
		vehicle1.setServiceInterval(365);


		vehicle2.setVin(vin2);
		vehicle2.setModel("Model2");
		vehicle2.setBrand("Brand2");
		vehicle2.setType("type2");
		vehicle2.setYearOfProduction(2005);
		vehicle2.setEngineType("diesel");
		vehicle2.setMaxMileage(110000L);
		vehicle2.setMileage(10000L);
		vehicle2.setServiceInterval(200);
	}

	@Test
	public void insertVehicle() throws HibernateErrorException {
		VehicleDao vehicleDao = new VehicleDaoImpl();

		vehicleDao.insert(vehicle1);
		vehicleDao.insert(vehicle2);
		try {
			vehicleDao.insert(new Vehicle());
			fail("Vehicle attributes are null, exception should be thrown");
		} catch (Exception e) {
		}
	}

	@Test
	public void findAllVehicles() throws HibernateErrorException {
		VehicleDao vehicleDao = new VehicleDaoImpl();

		List<Vehicle> vehicles = vehicleDao.findAll();

		assertEquals(vehicles.size(), 0);

		vehicleDao.insert(vehicle1);
		vehicleDao.insert(vehicle2);

		vehicles = vehicleDao.findAll();

		assertEquals(vehicles.size(), 2);
		assertEquals(vehicle1, vehicles.get(0));
		assertEquals(vehicle2, vehicles.get(1));

		try {
			vehicleDao.insert(new Vehicle());
			fail("Cannot insert vehicle without vin");
		} catch (HibernateErrorException ex){
			// -- ok
		}

		vehicles = vehicleDao.findAll();
		assertEquals(vehicles.size(), 2);
		assertEquals(vehicle1, vehicles.get(0));
		assertEquals(vehicle2, vehicles.get(1));
	}

	@Test
	public void findVehicleByVin() throws HibernateErrorException {
		VehicleDao vehicleDao = new VehicleDaoImpl();

		vehicleDao.insert(vehicle1);

		assertEquals(vehicle1, vehicleDao.findByVin(vehicle1.getVin()));
		assertEquals(null, vehicleDao.findByVin(vehicle2.getVin()));

		vehicleDao.insert(vehicle2);

		assertEquals(vehicle2, vehicleDao.findByVin(vehicle2.getVin()));
		assertEquals(vehicle1, vehicleDao.findByVin(vehicle1.getVin()));
	}

	@Test
	public void updateVehicle() throws HibernateErrorException {
		VehicleDao vehicleDao = new VehicleDaoImpl();

		vehicleDao.insert(vehicle1);
		vehicleDao.insert(vehicle2);

		Long maxMileage = 16568L;
		vehicle1.setMaxMileage(maxMileage);
		vehicleDao.update(vehicle1);
		assertEquals(maxMileage, vehicleDao.findByVin(vehicle1.getVin()).getMaxMileage());

		Long mileage = 25540L;
		vehicle1.setMileage(mileage);
		vehicleDao.update(vehicle1);
		assertEquals(mileage, vehicleDao.findByVin(vehicle1.getVin()).getMileage());


		vehicle1.setServiceInterval(985);
		vehicleDao.update(vehicle1);
		assertEquals(985, vehicleDao.findByVin(vehicle1.getVin()).getServiceInterval());

		vehicle1.setType("5 doors");
		vehicleDao.update(vehicle1);
		assertEquals("5 doors", vehicleDao.findByVin(vehicle1.getVin()).getType());

		vehicle1.setBrand("Toyota");
		vehicleDao.update(vehicle1);
		assertEquals("Toyota", vehicleDao.findByVin(vehicle1.getVin()).getBrand());

		vehicle1.setEngineType("LPG");
		vehicleDao.update(vehicle1);
		assertEquals("LPG", vehicleDao.findByVin(vehicle1.getVin()).getEngineType());

		vehicle2.setModel("Corolla");
		vehicleDao.update(vehicle2);
		assertEquals("Corolla", vehicleDao.findByVin(vehicle2.getVin()).getModel());

		vehicle1.setYearOfProduction(1999);
		vehicleDao.update(vehicle1);
		assertEquals(1999, vehicleDao.findByVin(vehicle1.getVin()).getYearOfProduction());

		assertEquals(vehicle2, vehicleDao.findByVin(vehicle2.getVin()));
	}

	@Test
	public void deleteVehicle() throws HibernateErrorException {
		VehicleDao vehicleDao = new VehicleDaoImpl();

		vehicleDao.insert(vehicle1);
		vehicleDao.insert(vehicle2);

		vehicleDao.delete(vehicle1);

		assertEquals(null, vehicleDao.findByVin(vehicle1.getVin()));
		assertEquals(vehicle2, vehicleDao.findByVin(vehicle2.getVin()));

		vehicleDao.insert(vehicle1);

		vehicleDao.delete(vehicle2);

		assertEquals(vehicle1, vehicleDao.findByVin(vehicle1.getVin()));
		assertEquals(null, vehicleDao.findByVin(vehicle2.getVin()));

		vehicleDao.delete(vehicle1);

		assertEquals(null, vehicleDao.findByVin(vehicle1.getVin()));
		assertEquals(null, vehicleDao.findByVin(vehicle2.getVin()));
	}

	@AfterMethod
	public void tearDown() throws Exception {
		VehicleDao vehicleDao = new VehicleDaoImpl();
		vehicleDao.delete(this.vehicle1);
		vehicleDao.delete(this.vehicle2);

	}
}
