package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.ServiceCheckDao;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * Test suite for ServiceCheck entity
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/29/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class ServiceCheckTest {

    @Autowired
    @Qualifier(value = "serviceCheckDao")
    private ServiceCheckDao serviceCheckDao;

    @Autowired
    @Qualifier(value = "vehicleDao")
    private VehicleDao vehicleDao;

    String v1Vin = "IPK204t4FG";
    String v2Vin = "DRH244yOKS";

    /**
     * Initializes records to the database
     */
    @Before
    public void prepare() throws DataAccessException {
        Vehicle v1 = createVehicle1();
        Vehicle v2 = createVehicle2();

        vehicleDao.create(v1);
        vehicleDao.create(v2);

        ServiceCheck sc1 = createServiceCheck1(v1);
        ServiceCheck sc2 = createServiceCheck2(v1);
        ServiceCheck sc3 = createServiceCheck3(v1);
        ServiceCheck sc4 = createServiceCheck4(v2);

        serviceCheckDao.create(sc1);
        serviceCheckDao.create(sc2);
        serviceCheckDao.create(sc3);
        serviceCheckDao.create(sc4);
    }

    /**
     * Finds all created vehicles and their service checks and tests equals
     * method
     */
    @Test
    public void getAllVehiclesAndServiceChecks() throws DataAccessException {
        List<Vehicle> vehicles = vehicleDao.getAll();
        List<ServiceCheck> scsAll = serviceCheckDao.getAll();
        Assert.assertEquals(scsAll.size(), 4);
        Assert.assertEquals(vehicles.size(), 2);
        Vehicle v1 = vehicles.get(0);
        Vehicle v2 = vehicles.get(1);
        Assert.assertEquals(v1, v1);
        Assert.assertFalse(v1.equals(v2));

        List<ServiceCheck> scs1 = serviceCheckDao.getAllByVehicle(v1);
        List<ServiceCheck> scs2 = serviceCheckDao.getAllByVehicle(v2);

        Assert.assertEquals(scs1.size(), 3);
        Assert.assertEquals(scs2.size(), 1);

        ServiceCheck sc11 = scs1.get(0);
        ServiceCheck sc12 = scs1.get(1);
        ServiceCheck sc13 = scs1.get(2);
        ServiceCheck sc2 = scs2.get(0);

        Assert.assertEquals(sc11, sc11);
        Assert.assertFalse(sc12.equals(sc13));
        Assert.assertFalse(sc12.equals(sc2));
    }

    @Test
    public void ServiceCheckUpdateTest() throws DataAccessException{
        ServiceCheck sc = null;

        for (ServiceCheck s : serviceCheckDao.getAll()) {
            if (s.getStatus().equals(ServiceCheckStatus.DONE_NOT_OK)) {
                sc = s;
            }
        }

        List<Vehicle> vehicles = vehicleDao.getAll();
        Vehicle v1 = vehicles.get(0);
        Vehicle v2 = vehicles.get(1);

        sc.setVehicle(v2);
        serviceCheckDao.update(sc);

        List<ServiceCheck> scs1 = serviceCheckDao.getAllByVehicle(v1);
        List<ServiceCheck> scs2 = serviceCheckDao.getAllByVehicle(v2);

        Assert.assertEquals(scs1.size(), scs2.size());
    }

    @Test
    public void ServiceCheckDeleteTest() throws DataAccessException {
        ServiceCheck sc = null;

        for (ServiceCheck s : serviceCheckDao.getAll()) {
            if (s.getStatus().equals(ServiceCheckStatus.DONE_NOT_OK)) {
                sc = s;
            }
        }

        List<Vehicle> vehicles = vehicleDao.getAll();
        Vehicle v1 = vehicles.get(0);
        Vehicle v2 = vehicles.get(1);

        serviceCheckDao.delete(sc);

        List<ServiceCheck> scs1 = serviceCheckDao.getAllByVehicle(v1);
        List<ServiceCheck> scs2 = serviceCheckDao.getAllByVehicle(v2);

        Assert.assertEquals(scs1.size(), 2);
        Assert.assertEquals(scs2.size(), 1);
    }

    @Test(expected = DataAccessException.class)
    public void testDoubleDeletionException() {
        ServiceCheck sc = serviceCheckDao.getAll().get(0);
        serviceCheckDao.delete(sc);
        serviceCheckDao.delete(sc);
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateAfterDelete() {
        ServiceCheck sc = serviceCheckDao.getAll().get(0);
        serviceCheckDao.delete(sc);
        sc.setServiceEmployee("Martin");
        serviceCheckDao.update(sc);
    }

    @Test(expected=DataAccessException.class)
    public void testGetError() throws DataAccessException {
        serviceCheckDao.get(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testUpdateError() throws DataAccessException {
        serviceCheckDao.update(null);
    }
        
    @Test(expected=DataAccessException.class)
    public void testDeleteError() throws DataAccessException {
        serviceCheckDao.delete(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testCreateError() throws DataAccessException {
        serviceCheckDao.create(null);
    }
    
    @Test(expected=DataAccessException.class)
    public void testGetByVehicleError() throws DataAccessException {
        serviceCheckDao.getAllByVehicle(null);
    }
    
    private Vehicle createVehicle1() throws DataAccessException {
        Vehicle v1 = new Vehicle();
        v1.setVin(v1Vin);
        v1.setModel("Mustang");
        v1.setBrand("Ford");
        v1.setType("5 door");
        v1.setYearOfProduction(2009);
        v1.setEngineType("petrol");
        v1.setMaxMileage(200000L);
        v1.setMileage(10000L);
        v1.setServiceCheckInterval(150);
        v1.setDrives(new ArrayList<Drive>());
        v1.setServiceChecks(new ArrayList<ServiceCheck>());
        return v1;
    }

    private Vehicle createVehicle2() throws DataAccessException {
        Vehicle v2 = new Vehicle();
        v2.setVin(v2Vin);
        v2.setModel("Accord");
        v2.setBrand("Honda");
        v2.setType("5 door");
        v2.setYearOfProduction(2007);
        v2.setEngineType("petrol");
        v2.setMaxMileage(250000L);
        v2.setMileage(49000L);
        v2.setServiceCheckInterval(200);
        v2.setDrives(new ArrayList<Drive>());
        v2.setServiceChecks(new ArrayList<ServiceCheck>());
        return v2;
    }

    private ServiceCheck createServiceCheck1(Vehicle v1) throws DataAccessException {
        ServiceCheck sc1 = new ServiceCheck();
        sc1.setStatus(ServiceCheckStatus.DONE_OK);
        sc1.setVehicle(v1);
        sc1.setServiceEmployee("Peter");
        sc1.setServiceCheckDate(new Date(115, 10, 4));
        sc1.setReport("please handle with care");
        return sc1;
    }

    private ServiceCheck createServiceCheck2(Vehicle v1) throws DataAccessException {
        ServiceCheck sc2 = new ServiceCheck();
        sc2.setStatus(ServiceCheckStatus.DONE_OK);
        sc2.setVehicle(v1);
        sc2.setServiceEmployee("Peter");
        sc2.setServiceCheckDate(new Date(115, 10, 4));
        sc2.setReport("please handle with care");
        return sc2;
    }

    private ServiceCheck createServiceCheck3(Vehicle v1) throws DataAccessException {
        ServiceCheck sc3 = new ServiceCheck();
        sc3.setStatus(ServiceCheckStatus.DONE_NOT_OK);
        sc3.setVehicle(v1);
        sc3.setServiceEmployee("Peter");
        sc3.setServiceCheckDate(new Date(115, 10, 4));
        sc3.setReport("please handle with care");
        return sc3;
    }

    private ServiceCheck createServiceCheck4(Vehicle v2) throws DataAccessException {
        ServiceCheck sc4 = new ServiceCheck();
        sc4.setStatus(ServiceCheckStatus.DONE_OK);
        sc4.setVehicle(v2);
        sc4.setServiceEmployee("Peter");
        sc4.setServiceCheckDate(new Date(115, 10, 4));
        sc4.setReport("please handle with care");
        return sc4;
    }
}
