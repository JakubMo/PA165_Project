package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.ServiceCheckService;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for {@link ServiceCheckFacade}.
 *
 * @author Marek
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class ServiceCheckFacadeTest {
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @Mock
    private ServiceCheckService serviceCheckService;
    
    @Inject
    @InjectMocks
    private ServiceCheckFacadeImpl serviceCheckFacade;
    
    private ServiceCheckCreateDTO serviceCheckCreateDTO;
    private ServiceCheckDTO serviceCheckDTO;
    private ServiceCheck serviceCheck;
    
    private VehicleDTO vehicleDTO;
    private Vehicle vehicle;
    
    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare() {
        vehicleDTO = prepareVehicleDto();
        vehicle = prepareVehicle();
        
        serviceCheckCreateDTO = prepareServiceCheckCreateDto();
        serviceCheckDTO = prepareServiceCheckDto();
        serviceCheck = prepareServiceCheck();
    }
    
    @Test
    public void createServiceCheckTest() {
        serviceCheckFacade.createServiceCheck(serviceCheckCreateDTO);
        verify(beanMappingService).mapTo(serviceCheckCreateDTO, ServiceCheck.class);
        verify(serviceCheckService).createServiceCheck(any());        
    }
    
    @Test
    public void deleteServiceCheckTest() {
        serviceCheckFacade.deleteServiceCheck(serviceCheckDTO.getId());
        verify(serviceCheckService).deleteServiceCheck(any());
    }
    
    @Test
    public void updateServiceCheckStatusTest() {
        serviceCheckFacade.updateServiceCheckStatus(serviceCheckDTO.getId(), ServiceCheckStatus.DONE_OK);
        verify(serviceCheckService).updateServiceCheckStatus(any(), any());
    }
    
    @Test
    public void updateServiceCheckReportTest() {
        serviceCheckFacade.updateReport(serviceCheckDTO.getId(), "Everything OK.");
        verify(serviceCheckService).updateReport(any(), any());
    }
    
    @Test
    public void updateServiceCheckDateTest() {
        serviceCheckFacade.updateServiceCheckDate(serviceCheckDTO.getId(), new Date(2016, 2, 17));
        verify(serviceCheckService).updateServiceCheckDate(any(), any());
    }
    
    @Test
    public void updateServiceCheckEmployeeTest() {
        serviceCheckFacade.updateServiceEmployee(serviceCheckDTO.getId(), "New Employee");
        verify(serviceCheckService).updateServiceEmployee(any(), any());
    }
    
    @Test
    public void getAllTest() {
        serviceCheckFacade.getAll();
        verify(beanMappingService).mapTo(serviceCheckService.getAll(), ServiceCheckDTO.class);
    }
    
    @Test
    public void getAllByVehicle() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck);
        
        when(serviceCheckService.getAllByVehicle(any())).thenReturn(serviceChecks);
        serviceCheckFacade.getAllByVehicle(vehicleDTO);
        
        verify(serviceCheckService).getAllByVehicle(any());
        verify(beanMappingService).mapTo(serviceChecks, ServiceCheckDTO.class);
    }
    
    @Test
    public void getAllByStatusTest() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck);
        
        when(serviceCheckService.getAllByServiceCheckStatus(any())).thenReturn(serviceChecks);
        serviceCheckFacade.getAllByServiceCheckStatus(ServiceCheckStatus.NOT_DONE);
        
        verify(serviceCheckService).getAllByServiceCheckStatus(any());
        verify(beanMappingService).mapTo(serviceChecks, ServiceCheckDTO.class);
    }
    
    @Test
    public void getAllByTimeIntervalTest() {
        List<ServiceCheck> serviceChecks = new ArrayList<>();
        serviceChecks.add(serviceCheck);
        
        when(serviceCheckService.getAllByTimeInterval(any(), any())).thenReturn(serviceChecks);
        serviceCheckFacade.getAllByTimeInterval(new Date(2010, 1, 1), new Date(2019, 12, 31));
        
        verify(serviceCheckService).getAllByTimeInterval(any(), any());
        verify(beanMappingService).mapTo(serviceChecks, ServiceCheckDTO.class);
    }
    
    private VehicleDTO prepareVehicleDto() {
        VehicleDTO vdto = new VehicleDTO();
        vdto.setId(156L);
        vdto.setVin("IPK204t4FG");
        vdto.setBrand("Brand 1");
        vdto.setModel("Model 1");
        vdto.setType("Type 1");
        vdto.setYearOfProduction(2008);
        vdto.setEngineType("diesel");
        vdto.setMileage(9850L);
        vdto.setServiceCheckInterval(475);
        vdto.setMaxMileage(75000L);
        return vdto;
    }

    private Vehicle prepareVehicle() {
        Vehicle v = new Vehicle();
        v.setId(156L);
        v.setVin("IPK204t4FG");
        v.setBrand("Brand 1");
        v.setModel("Model 1");
        v.setType("Type 1");
        v.setYearOfProduction(2008);
        v.setEngineType("diesel");
        v.setMileage(9850L);
        v.setServiceCheckInterval(475);
        v.setMaxMileage(75000L);
        return v;
    }
    
    private ServiceCheckCreateDTO prepareServiceCheckCreateDto() {
        ServiceCheckCreateDTO sccdto = new ServiceCheckCreateDTO();
        sccdto.setStatus(ServiceCheckStatus.NOT_DONE);
        sccdto.setServiceCheckDate(new Date(2016, 3, 27));
        sccdto.setVehicle(vehicleDTO);
        sccdto.setServiceEmployee("Employee 1");
        sccdto.setReport("");
        return sccdto;
    }

    private ServiceCheckDTO prepareServiceCheckDto() {
        ServiceCheckDTO scdto = new ServiceCheckDTO();
        scdto.setId(15847L);
        scdto.setStatus(ServiceCheckStatus.NOT_DONE);
        scdto.setServiceCheckDate(new Date(2016, 3, 27));
        scdto.setVehicle(vehicleDTO);
        scdto.setServiceEmployee("Employee 1");
        scdto.setReport("");
        return scdto;
    }

    private ServiceCheck prepareServiceCheck() {
        ServiceCheck sc = new ServiceCheck();
        sc.setStatus(ServiceCheckStatus.NOT_DONE);
        sc.setServiceCheckDate(new Date(2016, 3, 27));
        sc.setVehicle(vehicle);
        sc.setServiceEmployee("Employee 1");
        sc.setReport("");
        return sc;
    }
}
