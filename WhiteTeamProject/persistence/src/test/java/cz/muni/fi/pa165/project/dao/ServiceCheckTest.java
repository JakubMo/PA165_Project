package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.PersistenceLayerContext;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test suite for ServiceCheck entity
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/29/2015
 */
@ContextConfiguration(classes = {PersistenceLayerContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceCheckTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private ServiceCheckDao serviceCheckDao;

    @Autowired
    private VehicleDao vehicleDao;

    @PersistenceContext
    private EntityManager em;
    
    String v1Vin = "IPK204t4FG";
    String v2Vin = "DRH244yOKS";

    /**
     * Initializes records to the database
     */
    @BeforeMethod
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
    public void getAllByStatusTest() throws DataAccessException {
        List<ServiceCheck> scs1 = serviceCheckDao.getAllByStatus(ServiceCheckStatus.NOT_DONE);
        Assert.assertEquals(0, scs1.size());

        List<ServiceCheck> scs2 = serviceCheckDao.getAllByStatus(ServiceCheckStatus.DONE_NOT_OK);
        Assert.assertEquals(1, scs2.size());

        List<ServiceCheck> scs3 = serviceCheckDao.getAllByStatus(ServiceCheckStatus.DONE_OK);
        Assert.assertEquals(3, scs3.size());
    }

	@Test
	public void getAllByTimeIntervalTest() {
		Date startDate;
		Date endDate;

		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, Calendar.SEPTEMBER, 4);
		startDate = calendar.getTime();

		calendar.set(2015, Calendar.SEPTEMBER, 5);
		endDate = calendar.getTime();

		Assert.assertEquals(0, serviceCheckDao.getAllByTimeInterval(startDate, endDate).size());

		calendar.set(2015, Calendar.OCTOBER, 1);
		endDate = calendar.getTime();
		Assert.assertEquals(1, serviceCheckDao.getAllByTimeInterval(startDate, endDate).size());

		calendar.set(2015, Calendar.OCTOBER, 5);
		endDate = calendar.getTime();
		Assert.assertEquals(2, serviceCheckDao.getAllByTimeInterval(startDate, endDate).size());

		calendar.set(2015, Calendar.OCTOBER, 21);
		endDate = calendar.getTime();
		Assert.assertEquals(3, serviceCheckDao.getAllByTimeInterval(startDate, endDate).size());

		calendar.set(2015, Calendar.DECEMBER, 24);
		endDate = calendar.getTime();
		Assert.assertEquals(4, serviceCheckDao.getAllByTimeInterval(startDate, endDate).size());
	}

    @Test
    public void serviceCheckUpdateTest() throws DataAccessException{
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
    public void serviceCheckDeleteTest() throws DataAccessException {
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

    @Test(expectedExceptions = DataAccessException.class)
    public void testDoubleDeletionException() {
        ServiceCheck sc = serviceCheckDao.getAll().get(0);
        serviceCheckDao.delete(sc);
        serviceCheckDao.delete(sc);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateAfterDelete() {
        ServiceCheck sc = serviceCheckDao.getAll().get(0);
        serviceCheckDao.delete(sc);
        sc.setServiceEmployee("Martin");
        serviceCheckDao.update(sc);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testGetError() throws DataAccessException {
        serviceCheckDao.get(null);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testUpdateError() throws DataAccessException {
        serviceCheckDao.update(null);
    }
        
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testDeleteError() throws DataAccessException {
        serviceCheckDao.delete(null);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testCreateError() throws DataAccessException {
        serviceCheckDao.create(null);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testGetByVehicleError() throws DataAccessException {
        serviceCheckDao.getAllByVehicle(null);
    }

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetAllByStatusError() throws DataAccessException {
		serviceCheckDao.getAllByStatus(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetAllByTimeIntervalStartDateError(){
		serviceCheckDao.getAllByTimeInterval(null, new Date());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetAllByTimeIntervalEndDateError(){
		serviceCheckDao.getAllByTimeInterval(new Date(), null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetAllByTimeIntervalEndDateBeforeStartDateError(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015,Calendar.NOVEMBER, 25);
		Date endDate = calendar.getTime();
		calendar.set(2015,Calendar.NOVEMBER, 26);
		Date startDate = calendar.getTime();
		serviceCheckDao.getAllByTimeInterval(startDate, endDate);
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.OCTOBER, 1);
        sc1.setServiceCheckDate(calendar.getTime());
        sc1.setReport("please handle with care");
        return sc1;
    }

    private ServiceCheck createServiceCheck2(Vehicle v1) throws DataAccessException {
        ServiceCheck sc2 = new ServiceCheck();
        sc2.setStatus(ServiceCheckStatus.DONE_OK);
        sc2.setVehicle(v1);
        sc2.setServiceEmployee("Peter");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.OCTOBER, 5);
        sc2.setServiceCheckDate(calendar.getTime());
        sc2.setReport("please handle with care");
        return sc2;
    }

    private ServiceCheck createServiceCheck3(Vehicle v1) throws DataAccessException {
        ServiceCheck sc3 = new ServiceCheck();
        sc3.setStatus(ServiceCheckStatus.DONE_NOT_OK);
        sc3.setVehicle(v1);
        sc3.setServiceEmployee("Peter");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.OCTOBER, 18);
        sc3.setServiceCheckDate(calendar.getTime());
        sc3.setReport("please handle with care");
        return sc3;
    }

    private ServiceCheck createServiceCheck4(Vehicle v2) throws DataAccessException {
        ServiceCheck sc4 = new ServiceCheck();
        sc4.setStatus(ServiceCheckStatus.DONE_OK);
        sc4.setVehicle(v2);
        sc4.setServiceEmployee("Peter");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.DECEMBER, 6);
        sc4.setServiceCheckDate(calendar.getTime());
        sc4.setReport("please handle with care");
        return sc4;
    }
}
