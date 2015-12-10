package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.ServiceCheckDao;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for {@link ServiceCheckService}.
 *
 * @author Marek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceCheckServiceTest extends AbstractTestNGSpringContextTests{
   
    @Mock
    private ServiceCheckDao serviceCheckDao;
    
    @Inject
    @InjectMocks
    private ServiceCheckService serviceCheckService;
    
    private ServiceCheck serviceCheck1;
    private ServiceCheck serviceCheck2;
    private ServiceCheck serviceCheck3;
    private Vehicle vehicle1;
    private Vehicle vehicle2;
    
    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void initServiceChecksAndVehicles() {
        vehicle1 = prepareVehicle1();
        vehicle2 = prepareVehicle2();
        
        serviceCheck1 = prepareServiceCheck1();
        serviceCheck2 = prepareServiceCheck2();
        serviceCheck3 = prepareServiceCheck3();
    }
    
    @Test
    public void getByIdTest() {
        Long id = 7L;
        serviceCheck2.setId(id);
        when(serviceCheckDao.get(id)).thenReturn(serviceCheck2);
        assertEquals(serviceCheckService.getById(id), serviceCheck2);        
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByNullIdTest() {
        serviceCheckService.getById(null);
    }
    
    @Test
    public void getAllTest() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck1);
        serviceChecks.add(serviceCheck2);
        serviceChecks.add(serviceCheck3);
        
        when(serviceCheckDao.getAll()).thenReturn(serviceChecks);
        assertEquals(serviceCheckService.getAll().size(), 3);
        assertEquals(serviceCheckService.getAll(), serviceChecks);
    }
    
    @Test
    public void getAllByVehicleTest() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck1);
        serviceChecks.add(serviceCheck2);
        
        when(serviceCheckDao.getAllByVehicle(vehicle1)).thenReturn(serviceChecks);
        assertEquals(serviceCheckService.getAllByVehicle(vehicle1).size(), 2);
        assertEquals(serviceCheckService.getAllByVehicle(vehicle1), serviceChecks);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllByNulVehicleTest() {
        serviceCheckService.getAllByVehicle(null);
    }
    
    @Test
    public void getAllByStatusTest() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck3);
        
        when(serviceCheckDao.getAllByStatus(ServiceCheckStatus.DONE_OK)).thenReturn(serviceChecks);
        assertEquals(serviceCheckService.getAllByServiceCheckStatus(ServiceCheckStatus.DONE_OK).size(), 1);
        assertEquals(serviceCheckService.getAllByServiceCheckStatus(ServiceCheckStatus.DONE_OK), serviceChecks);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllByNullStatusTest() {
        serviceCheckService.getAllByServiceCheckStatus(null);
    }
    
    @Test
    public void getAllByTimeIntervalTest() {
        Date startDate = new Date(2015, 1, 1);
        Date endDate = new Date(2015, 12, 31);
        
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck2);
        serviceChecks.add(serviceCheck3);
        
        when(serviceCheckDao.getAllByTimeInterval(startDate, endDate)).thenReturn(serviceChecks);
        assertEquals(serviceCheckService.getAllByTimeInterval(startDate, endDate).size(), 2);
        assertEquals(serviceCheckService.getAllByTimeInterval(startDate, endDate), serviceChecks);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllByIntervalNullStartDateTest() {
        serviceCheckService.getAllByTimeInterval(null, new Date(2015, 12, 31));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllByIntervalNullEndDateTest() {
        serviceCheckService.getAllByTimeInterval(new Date(2015, 1, 1), null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getAllByInvalidIntervalTest() {
        serviceCheckService.getAllByTimeInterval(new Date(2015, 12, 31), new Date(2015, 1, 1));
    }
    
    @Test
    public void createServiceCheckTest() {
        serviceCheckService.createServiceCheck(serviceCheck1);
        verify(serviceCheckDao).create(serviceCheck1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullServiceCheckTest() {
        serviceCheckService.createServiceCheck(null);
    }
    
    @Test
    public void deleteServiceCheckTest() {
        serviceCheck2.setId(987L);
        serviceCheckService.deleteServiceCheck(serviceCheck2);
        verify(serviceCheckDao).delete(serviceCheck2);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullServiceCheckTest() {
        serviceCheckService.deleteServiceCheck(null);
    }
    
    @Test
    public void updateServiceCheckStatusTest() {
        serviceCheck1.setId(795L);
        serviceCheckService.updateServiceCheckStatus(serviceCheck1, ServiceCheckStatus.DONE_OK);
        verify(serviceCheckDao).update(serviceCheck1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckStatusNullServiceCheckTest() {
        serviceCheckService.updateServiceCheckStatus(null, ServiceCheckStatus.DONE_OK);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckNullStatusTest() {
        serviceCheckService.updateServiceCheckStatus(serviceCheck1, null);
    }
    
    @Test
    public void updateServiceCheckReportTest() {
        serviceCheck1.setId(795L);
        serviceCheckService.updateReport(serviceCheck1, "Car is OK now.");
        verify(serviceCheckDao).update(serviceCheck1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckReportNullServiceCheckTest() {
        serviceCheckService.updateReport(null, "Car is OK now.");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckNullReportTest() {
        serviceCheckService.updateReport(serviceCheck1, null);
    }
    
    @Test
    public void updateServiceCheckDateTest() {
        serviceCheck1.setId(795L);
        serviceCheckService.updateServiceCheckDate(serviceCheck1, new Date(2015, 12, 17));
        verify(serviceCheckDao).update(serviceCheck1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckDateNullServiceCheckTest() {
        serviceCheckService.updateServiceCheckDate(null, new Date(2015, 12, 17));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckNullDateTest() {
        serviceCheckService.updateServiceCheckDate(serviceCheck1, null);
    }
    
    @Test
    public void updateServiceCheckEmployeeTest() {
        serviceCheck3.setId(1258L);
        serviceCheckService.updateServiceEmployee(serviceCheck3, "Employee XYZ");
        verify(serviceCheckDao).update(serviceCheck3);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckEmployeeNullServiceCheckTest() {
        serviceCheckService.updateServiceEmployee(null, "Employee XYZ");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateServiceCheckNullEmployeeTest() {
        serviceCheckService.updateServiceEmployee(serviceCheck3, null);
    }
    
    private Vehicle prepareVehicle1() {
        Vehicle v = new Vehicle();
        v.setVin("IPK204t4FG");
        v.setBrand("Brand 1");
        v.setModel("Model 1");
        v.setType("Type 1");
        v.setYearOfProduction(2007);
        v.setEngineType("diesel");
        v.setMileage(10000L);
        v.setServiceCheckInterval(250);
        v.setMaxMileage(100000L);
        return v;
    }
    
    private Vehicle prepareVehicle2() {
        Vehicle v = new Vehicle();
        v.setVin("DRH244yOKS");
        v.setBrand("Brand 2");
        v.setModel("Model 2");
        v.setType("Type 2");
        v.setYearOfProduction(2006);
        v.setEngineType("diesel");
        v.setMileage(12000L);
        v.setServiceCheckInterval(250);
        v.setMaxMileage(100000L);
        return v;
    }
    
    private ServiceCheck prepareServiceCheck1() {
        ServiceCheck sc = new ServiceCheck();
        sc.setStatus(ServiceCheckStatus.NOT_DONE);
        sc.setServiceCheckDate(new Date(2016, 2, 17));
        sc.setVehicle(vehicle1);
        sc.setServiceEmployee("Service employee #1");
        sc.setReport("");
        return sc;
    }
    
    private ServiceCheck prepareServiceCheck2() {
        ServiceCheck sc = new ServiceCheck();
        sc.setStatus(ServiceCheckStatus.DONE_NOT_OK);
        sc.setServiceCheckDate(new Date(2015, 8, 25));
        sc.setVehicle(vehicle1);
        sc.setServiceEmployee("Service employee #23");
        sc.setReport("Car is broken.");
        return sc;
    }
    
    private ServiceCheck prepareServiceCheck3() {
        ServiceCheck sc = new ServiceCheck();
        sc.setStatus(ServiceCheckStatus.DONE_OK);
        sc.setServiceCheckDate(new Date(2015, 10, 3));
        sc.setVehicle(vehicle2);
        sc.setServiceEmployee("Service employee #14");
        sc.setReport("Everything OK.");
        return sc;
    }
}
