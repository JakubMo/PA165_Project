package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Vehicle test suite
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 30.10.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class VehicleTest {

    private String vin1 = "IPK204t4FG";
    private String vin2 = "DRH244yOKS";

    @Autowired
    @Qualifier(value = "vehicleDao")
    private VehicleDao vehicleDao;

    @BeforeClass
    public static void init() {
    }

    @Test
    public void insertVehicle() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
    }

    @Test(expected = DataAccessException.class)
    public void insertVehicleError() throws DataAccessException {
        vehicleDao.create(new Vehicle());
    }

    @Test
    public void getAllVehicles() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        List<Vehicle> vehicles = vehicleDao.getAll();

        assertEquals(vehicles.size(), 0);

        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);

        vehicles = vehicleDao.getAll();

        assertEquals(vehicles.size(), 2);
        assertEquals(vehicle1, vehicles.get(0));
        assertEquals(vehicle2, vehicles.get(1));

        vehicles = vehicleDao.getAll();
        assertEquals(vehicles.size(), 2);
        assertEquals(vehicle1, vehicles.get(0));
        assertEquals(vehicle2, vehicles.get(1));
    }

    @Test
    public void findVehicleByVin() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        vehicleDao.create(vehicle1);

        assertEquals(vehicle1, vehicleDao.get(vehicle1.getId()));
        assertEquals(null, vehicleDao.get(vehicle2.getId()));

        vehicleDao.create(vehicle2);

        assertEquals(vehicle2, vehicleDao.get(vehicle2.getId()));
        assertEquals(vehicle1, vehicleDao.get(vehicle1.getId()));
    }

    @Test
    public void updateVehicle() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);

        Long maxMileage = 16568L;
        vehicle1.setMaxMileage(maxMileage);
        vehicleDao.update(vehicle1);
        assertEquals(maxMileage, vehicleDao.get(vehicle1.getId()).getMaxMileage());

        Long mileage = 25540L;
        vehicle1.setMileage(mileage);
        vehicleDao.update(vehicle1);
        assertEquals(mileage, vehicleDao.get(vehicle1.getId()).getMileage());

        vehicle1.setServiceCheckInterval(985);
        vehicleDao.update(vehicle1);
        assertEquals(985, vehicleDao.get(vehicle1.getId()).getServiceCheckInterval());

        vehicle1.setType("5 doors");
        vehicleDao.update(vehicle1);
        assertEquals("5 doors", vehicleDao.get(vehicle1.getId()).getType());

        vehicle1.setBrand("Toyota");
        vehicleDao.update(vehicle1);
        assertEquals("Toyota", vehicleDao.get(vehicle1.getId()).getBrand());

        vehicle1.setEngineType("LPG");
        vehicleDao.update(vehicle1);
        assertEquals("LPG", vehicleDao.get(vehicle1.getId()).getEngineType());

        vehicle2.setModel("Corolla");
        vehicleDao.update(vehicle2);
        assertEquals("Corolla", vehicleDao.get(vehicle2.getId()).getModel());

        vehicle1.setYearOfProduction(1999);
        vehicleDao.update(vehicle1);
        assertEquals(1999, vehicleDao.get(vehicle1.getId()).getYearOfProduction());

        assertEquals(vehicle2, vehicleDao.get(vehicle2.getId()));
    }

    @Test
    public void deleteVehicle() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);

        vehicleDao.delete(vehicle1);

        assertEquals(null, vehicleDao.get(vehicle1.getId()));
        assertEquals(vehicle2, vehicleDao.get(vehicle2.getId()));

        vehicleDao.create(vehicle1);

        vehicleDao.delete(vehicle2);

        assertEquals(vehicle1, vehicleDao.get(vehicle1.getId()));
        assertEquals(null, vehicleDao.get(vehicle2.getId()));

        vehicleDao.delete(vehicle1);

        assertEquals(null, vehicleDao.get(vehicle1.getId()));
        assertEquals(null, vehicleDao.get(vehicle2.getId()));
    }

    private Vehicle prepareVehicle1() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVin(vin1);
        vehicle1.setModel("Model1");
        vehicle1.setBrand("Brand1");
        vehicle1.setType("type1");
        vehicle1.setYearOfProduction(2012);
        vehicle1.setEngineType("gasoline");
        vehicle1.setMaxMileage(400000L);
        vehicle1.setMileage(45000L);
        vehicle1.setServiceCheckInterval(365);
        return vehicle1;
    }

    private Vehicle prepareVehicle2() {
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVin(vin2);
        vehicle2.setModel("Model2");
        vehicle2.setBrand("Brand2");
        vehicle2.setType("type2");
        vehicle2.setYearOfProduction(2005);
        vehicle2.setEngineType("diesel");
        vehicle2.setMaxMileage(110000L);
        vehicle2.setMileage(10000L);
        vehicle2.setServiceCheckInterval(200);
        return vehicle2;
    }
}
