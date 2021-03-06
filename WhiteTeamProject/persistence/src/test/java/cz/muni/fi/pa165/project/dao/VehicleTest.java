package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.PersistenceLayerContext;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Vehicle test suite
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 30.10.2015.
 */
@ContextConfiguration(classes = {PersistenceLayerContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class VehicleTest extends AbstractTestNGSpringContextTests{

    private String vin1 = "IPK204t4FG";
    private String vin2 = "DRH244yOKS";
    private String vin3 = "HJY83HJSA2";

    @Autowired
    private VehicleDao vehicleDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void insertVehicle() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();

        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
    }

    @Test(expectedExceptions = DataAccessException.class)
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
    public void findVehicleById() throws DataAccessException {
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
    public void findVehicleByVin() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();
        
        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
        
        assertEquals(vehicle1, vehicleDao.getByVin(vehicle1.getVin()));
        assertEquals(vehicle2, vehicleDao.getByVin(vehicle2.getVin()));
    }
    
    @Test
    public void findVehiclesByModel() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();
        Vehicle vehicle3 = prepareVehicle2_1();
        
        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
        vehicleDao.create(vehicle3);
        
        assertEquals(1, vehicleDao.getAllByModel(vehicle1.getModel()).size());
        assertEquals(2, vehicleDao.getAllByModel(vehicle2.getModel()).size());
    }
    
    @Test
    public void findVehiclesByBrand() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();
        Vehicle vehicle3 = prepareVehicle2_1();
        
        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
        vehicleDao.create(vehicle3);
        
        assertEquals(2, vehicleDao.getAllByBrand(vehicle1.getBrand()).size());
        assertEquals(1, vehicleDao.getAllByBrand(vehicle2.getBrand()).size());
    }
    
    @Test
    public void findVehiclesByMileage() throws DataAccessException {
        Vehicle vehicle1 = prepareVehicle1();
        Vehicle vehicle2 = prepareVehicle2();
        Vehicle vehicle3 = prepareVehicle2_1();
        
        vehicleDao.create(vehicle1);
        vehicleDao.create(vehicle2);
        vehicleDao.create(vehicle3);
        
        assertEquals(0, vehicleDao.getAllByMileage(9999L).size());
        assertEquals(1, vehicleDao.getAllByMileage(10000L).size());
        assertEquals(1, vehicleDao.getAllByMileage(10001L).size());
        
        assertEquals(1, vehicleDao.getAllByMileage(29999L).size());
        assertEquals(2, vehicleDao.getAllByMileage(30000L).size());
        assertEquals(2, vehicleDao.getAllByMileage(30001L).size());
        
        assertEquals(2, vehicleDao.getAllByMileage(44999L).size());
        assertEquals(3, vehicleDao.getAllByMileage(45000L).size());
        assertEquals(3, vehicleDao.getAllByMileage(45001L).size());
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
    
    private Vehicle prepareVehicle2_1() {
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVin(vin3);
        vehicle2.setModel("Model2");
        vehicle2.setBrand("Brand1");
        vehicle2.setType("type3");
        vehicle2.setYearOfProduction(2007);
        vehicle2.setEngineType("diesel");
        vehicle2.setMaxMileage(110000L);
        vehicle2.setMileage(30000L);
        vehicle2.setServiceCheckInterval(200);
        return vehicle2;
    }
}
