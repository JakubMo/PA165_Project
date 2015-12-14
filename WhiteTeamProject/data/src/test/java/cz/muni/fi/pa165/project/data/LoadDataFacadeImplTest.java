package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.dao.ServiceCheckDao;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.service.EmployeeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests data loader
 * 
 * @author jakub
 */
@ContextConfiguration(classes={CarRentalDataConfiguration.class})
@Transactional
public class LoadDataFacadeImplTest extends AbstractTestNGSpringContextTests{
    
    final static Logger log = LoggerFactory.getLogger(LoadDataFacadeImplTest.class);
    
    @Autowired
    public EmployeeDao employeeDao;
    
    @Autowired
    public VehicleDao vehicleDao;
    
    @Autowired
    public ServiceCheckDao serviceCheckDao;
    
    @Autowired
    public DriveDao driveDao;
    
    @Autowired
    public LoadDataFacade loadDataFacade;
    
    @Autowired
    public EmployeeService employeeService;

    @PersistenceContext
    private EntityManager em;
    
    @BeforeClass
    public void initialLoad() throws IOException {
        log.info("Test: Start of test. Loading data.");
        loadDataFacade.load();
        log.info("Test: Data loaded");
    }
    
    @Test
    public void employeesLoadedTest() {
        Assert.assertEquals(employeeDao.getAll().size(), 7, "number of employees is wrong");
        Assert.assertEquals(employeeDao.getAllByLastName("Mozucha").get(0).getFirstname(), "Jakub", "wrong first name");
        Assert.assertTrue(employeeService.authenticate(employeeDao.getByEmail("tomas@mail.com"), "pwd4"), "cannot authenticate");
        log.info("Test: All employees loaded succesfully");
    }
    
    @Test
    public void vehiclesLoadedTest() {
        Assert.assertEquals(vehicleDao.getAll().size(), 26, "number of vehicles is wrong");
        Assert.assertEquals(vehicleDao.getAllByBrand("Skoda").size(), 8, "number of skoda vehicles is wrong");
        Assert.assertEquals(vehicleDao.getAllByBrand("Honda").size(), 6, "number of honda vehicles is wrong");
        Assert.assertEquals(vehicleDao.getAllByBrand("Ford").size(), 7, "number of ford vehicles is wrong");
        Assert.assertEquals(vehicleDao.getAllByBrand("Volkswagen").size(), 5, "number of vw vehicles is wrong");
        log.info("Test: All vehicles loaded succesfully");
    }
    
    @Test
    public void serviceChecksLoadedTest() {
        Assert.assertEquals(serviceCheckDao.getAll().size(), 26, "number of service checks is wrong");
        Assert.assertEquals(serviceCheckDao.getAllByStatus(ServiceCheckStatus.DONE_OK).size(), 24, "number of succesfull service checks is wrong");
        Assert.assertEquals(serviceCheckDao.getAllByStatus(ServiceCheckStatus.DONE_NOT_OK).size(), 2, "number of unsuccesfull service checks is wrong");
        Assert.assertEquals(serviceCheckDao.getAllByStatus(ServiceCheckStatus.NOT_DONE).size(), 0, "number of not yet done service checks is wrong");
        log.info("Test: All service checks loaded succesfully");
    }
    
    @Test
    public void drivesLoadedTest() {
        Assert.assertEquals(driveDao.getAll().size(), 4, "number of drives is wrong");
        Assert.assertEquals(driveDao.getAllByVehicle(vehicleDao.getByVin("TMBPA16YX235Z9T6P")).size(), 2, "wrong number of fabia drives");
        log.info("Test: All drives loaded succesfully");
    }
    
    @Test
    public void vehiclesServiceChecksTest() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleDao.getAll();
        
        for(Vehicle veh : vehicles){
            Assert.assertEquals(serviceCheckDao.getAllByVehicle(veh).size(), 1, "vehicle " + veh.getVin() + " has wrong number of service checks.");
        }
    }
}
