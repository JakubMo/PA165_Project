package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.VehicleService;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Vehicle facade implementation.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/25/2015
 */
@Service
@Transactional
public class VehicleFacadeImpl implements VehicleFacade {

    final static Logger log = LoggerFactory.getLogger(VehicleFacadeImpl.class);
    
    @Inject
    private VehicleService vehicleService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void createVehicle(VehicleCreateDTO v) throws DataAccessExceptionImpl {
        if(v == null) {
            throw new IllegalArgumentException("vehicle is null");
        }
        
        Vehicle vehicle = beanMappingService.mapTo(v, Vehicle.class);
        vehicleService.createVehicle(vehicle);
    }

    @Override
    public void updateServiceCheckInterval(Long vehicleId, int interval) throws DataAccessExceptionImpl {
        if(vehicleId == null){
            throw new IllegalArgumentException("id is null");
        }
        
        Vehicle vehicle = vehicleService.getById(vehicleId);
        vehicleService.updateServiceCheckInterval(vehicle, interval);
    }

    @Override
    public void updateMaxMileage(Long vehicleId, Long max) throws DataAccessExceptionImpl {
        if(vehicleId == null){
            throw new IllegalArgumentException("id is null");
        }
        if(max == null){
            throw new IllegalArgumentException("maxMileage object is null");
        }
        
        Vehicle vehicle = vehicleService.getById(vehicleId);
        vehicleService.updateMaxMileage(vehicle, max);
    }

    @Override
    public void deleteVehicle(Long id) throws DataAccessExceptionImpl {
        if(id == null){
            throw new IllegalArgumentException("id is null");
        }
        
        Vehicle vehicle = vehicleService.getById(id);
        vehicleService.deleteVehicle(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllByModel(String model) throws DataAccessExceptionImpl {
        if(model == null || model.equals("")){
            return this.getAll();
        }
        return beanMappingService.mapTo(vehicleService.getAllByModel(model), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllByBrand(String brand) throws DataAccessExceptionImpl {
        if(brand == null || brand.equals("")){
            return this.getAll();
        }
        return beanMappingService.mapTo(vehicleService.getAllByBrand(brand), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllByMileage(Long mileage) throws DataAccessExceptionImpl {
        if(mileage < 0){
            throw new IllegalArgumentException("mileage is negative");
        }
        return beanMappingService.mapTo(vehicleService.getAllByMileage(mileage), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAll() throws DataAccessExceptionImpl {
        return beanMappingService.mapTo(vehicleService.getAll(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllFreeInDate(Date startDate, Date endDate) throws DataAccessExceptionImpl {
        return beanMappingService.mapTo(vehicleService.getAllFreeInDate(startDate, endDate), VehicleDTO.class);
    }
    
}
