package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.ServiceCheckDao;
import cz.muni.fi.pa165.project.dao.ServiceCheckDaoImpl;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.dao.VehicleDaoImpl;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test suite for ServiceCheck entity
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/29/2015
 */

public class ServiceCheckTest {

    String v1Vin = "IPK204t4FG";
    String v2Vin = "DRH244yOKS";

    /**
     * Initializes records to the database
     */
    @Test(priority = 1)
    public void ServiceCheckInsertTest() {
        VehicleDao vehicleDao = new VehicleDaoImpl();
        Vehicle v1 = new Vehicle();
        v1.setVin(v1Vin);
        v1.setModel("Mustang");
        v1.setBrand("Ford");
        v1.setType("5 door");
        v1.setYearOfProduction(2009);
        v1.setEngineType("petrol");
        v1.setMaxMileage(200000L);
        v1.setMileage(10000L);
        v1.setServiceInterval(150);

        Vehicle v2 = new Vehicle();
        v2.setVin(v2Vin);
        v2.setModel("Accord");
        v2.setBrand("Honda");
        v2.setType("5 door");
        v2.setYearOfProduction(2007);
        v2.setEngineType("petrol");
        v2.setMaxMileage(250000L);
        v2.setMileage(49000L);
        v2.setServiceInterval(200);

        vehicleDao.insert(v1);
        vehicleDao.insert(v2);

        ServiceCheckDao scDao = new ServiceCheckDaoImpl();
        ServiceCheck sc1 = new ServiceCheck();
        sc1.setStatus(ServiceCheckStatus.DONE_OK);
        sc1.setVehicle(v1);
        sc1.setServiceEmployee("Peter");
        sc1.setServiceCheckDate(new Date(115, 10, 4));
        sc1.setReport("please handle with care");

        ServiceCheck sc2 = new ServiceCheck();
        sc2.setStatus(ServiceCheckStatus.DONE_OK);
        sc2.setVehicle(v1);
        sc2.setServiceEmployee("Peter");
        sc2.setServiceCheckDate(new Date(115, 10, 4));
        sc2.setReport("please handle with care");

        ServiceCheck sc3 = new ServiceCheck();
        sc3.setStatus(ServiceCheckStatus.DONE_NOT_OK);
        sc3.setVehicle(v1);
        sc3.setServiceEmployee("Peter");
        sc3.setServiceCheckDate(new Date(115, 10, 4));
        sc3.setReport("please handle with care");

        ServiceCheck sc4 = new ServiceCheck();
        sc4.setStatus(ServiceCheckStatus.DONE_OK);
        sc4.setVehicle(v2);
        sc4.setServiceEmployee("Peter");
        sc4.setServiceCheckDate(new Date(115, 10, 4));
        sc4.setReport("please handle with care");

        try {
            scDao.insertServiceCheck(sc1);
            scDao.insertServiceCheck(sc2);
            scDao.insertServiceCheck(sc3);
            scDao.insertServiceCheck(sc4);
        } catch (HibernateErrorException hex) {
            Assert.fail();
        }
    }

    /**
     * Finds all created vehicles and their service checks and tests equals
     * method
     */
    @Test(priority = 2)
    public void findAllVehiclesAndServiceChecks() {
        try {
            List<Vehicle> vehicles = new VehicleDaoImpl().findAll();
            ServiceCheckDao scDao = new ServiceCheckDaoImpl();
            List<ServiceCheck> scsAll = scDao.getAllServiceChecks();
            Assert.assertEquals(scsAll.size(), 4);
            Assert.assertEquals(vehicles.size(), 2);
            Vehicle v1 = vehicles.get(0);
            Vehicle v2 = vehicles.get(1);
            Assert.assertEquals(v1, v1);
            Assert.assertNotEquals(v1, v2);

            List<ServiceCheck> scs1 = scDao.getAllServiceChecksByVehicle(v1);
            List<ServiceCheck> scs2 = scDao.getAllServiceChecksByVehicle(v2);

            Assert.assertEquals(scs1.size(), 3);
            Assert.assertEquals(scs2.size(), 1);

            ServiceCheck sc11 = scs1.get(0);
            ServiceCheck sc12 = scs1.get(1);
            ServiceCheck sc13 = scs1.get(2);
            ServiceCheck sc2 = scs2.get(0);

            Assert.assertEquals(sc11, sc11);
            Assert.assertNotEquals(sc12, sc13);
            Assert.assertNotEquals(sc12, sc2);
        } catch (HibernateErrorException hex) {
            Assert.fail();
        }
    }

    @Test(priority = 3)
    public void ServiceCheckUpdateTest() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            ServiceCheck sc = (ServiceCheck) session.createQuery("select s from ServiceCheck s where s.status=:stat")
                    .setParameter("stat", ServiceCheckStatus.DONE_NOT_OK)
                    .uniqueResult();
            session.getTransaction().commit();

            ServiceCheckDao scDao = new ServiceCheckDaoImpl();
            VehicleDao vDao = new VehicleDaoImpl();
            Vehicle v1 = vDao.findByVin(v1Vin);
            Vehicle v2 = vDao.findByVin(v2Vin);

            sc.setVehicle(v2);
            scDao.updateServiceCheck(sc);

            List<ServiceCheck> scs1 = scDao.getAllServiceChecksByVehicle(v1);
            List<ServiceCheck> scs2 = scDao.getAllServiceChecksByVehicle(v2);

            Assert.assertEquals(scs1.size(), scs2.size());
        } catch (HibernateErrorException hex) {
            Assert.fail();
        }
    }

    @Test(priority = 4)
    public void ServiceCheckDeleteTest() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            ServiceCheck sc = (ServiceCheck) session.createQuery("select s from ServiceCheck s where s.status=:stat")
                    .setParameter("stat", ServiceCheckStatus.DONE_NOT_OK)
                    .uniqueResult();
            session.getTransaction().commit();

            ServiceCheckDao scDao = new ServiceCheckDaoImpl();
            VehicleDao vDao = new VehicleDaoImpl();
            Vehicle v1 = vDao.findByVin(v1Vin);
            Vehicle v2 = vDao.findByVin(v2Vin);

            scDao.deleteServiceCheck(sc);

            List<ServiceCheck> scs1 = scDao.getAllServiceChecksByVehicle(v1);
            List<ServiceCheck> scs2 = scDao.getAllServiceChecksByVehicle(v2);

            Assert.assertEquals(scs1.size(), 2);
            Assert.assertEquals(scs2.size(), 1);
        } catch (HibernateErrorException hex) {
            Assert.fail();
        }
    }

    @Test(priority=5, expectedExceptions=HibernateException.class)
    public void testDoubleDeletionException(){
        try {
            ServiceCheckDao scDao = new ServiceCheckDaoImpl(); 
            ServiceCheck sc = scDao.findServiceCheck(1L);
            scDao.deleteServiceCheck(sc);
            scDao.deleteServiceCheck(sc);
        } catch (HibernateErrorException hex) {
            throw new HibernateException(hex);
        }
    }
    
    @Test(priority=6, expectedExceptions=HibernateException.class)
    public void testUpdateAfterDelete(){
        try {
            ServiceCheckDao scDao = new ServiceCheckDaoImpl(); 
            ServiceCheck sc = scDao.findServiceCheck(1L);
            scDao.deleteServiceCheck(sc);
            sc.setServiceEmployee("Martin");
            scDao.updateServiceCheck(sc);
        } catch (HibernateErrorException hex) {
            throw new HibernateException(hex);
        }
    }
}
