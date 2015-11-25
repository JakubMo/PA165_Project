package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.DriveCreateDTO;
import cz.muni.fi.pa165.project.dto.DriveDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.DriveService;
import cz.muni.fi.pa165.project.service.VehicleService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 * Implementation of {@link DriveFacade} interface.
 *
 * @author Marek
 */
public class DriveFacadeImpl implements DriveFacade{
    
    final static Logger log = LoggerFactory.getLogger(DriveFacadeImpl.class);
    
    @Inject
    private DriveService driveService;
    
    @Inject
    private VehicleService vehicleService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void createDrive(DriveCreateDTO driveCreateDTO) throws DataAccessException {
        if(driveCreateDTO == null) {
            throw new IllegalArgumentException("drive is null");
        }
        
        Drive drive = beanMappingService.mapTo(driveCreateDTO, Drive.class);
        Collection<Vehicle> freeVehicles = vehicleService.getAllFreeInDate(drive.getStartDate(), drive.getEndDate());
        if(!freeVehicles.contains(drive.getVehicle())) {
            throw new IllegalStateException("Vehicle of given drive is not free in required time interval.");
        }
        
        driveService.createDrive(drive);
    }

    @Override
    public void deleteDrive(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Drive drive = driveService.findById(id);
        driveService.deleteDrive(drive);
    }

    @Override
    public void changeStartDate(Long id, Date startDate) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(startDate == null) {
            throw new IllegalArgumentException("start date is null");
        }
        
        Drive drive = driveService.findById(id);
        driveService.changeStartDate(drive, startDate);
    }

    @Override
    public void changeEndDate(Long id, Date endDate) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(endDate == null) {
            throw new IllegalArgumentException("end date is null");
        }
        
        Drive drive = driveService.findById(id);
        driveService.changeEndDate(drive, endDate);
    }

    @Override
    public void changeDrivenKilometers(Long id, BigDecimal km) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(km == null) {
            throw new IllegalArgumentException("km is null");
        }
        if(km.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("km must be non-negative");
        }
        
        Drive drive = driveService.findById(id);
        driveService.changeDrivenKilometers(drive, km);
    }

    @Override
    public void approveDrive(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Drive drive = driveService.findById(id);
        driveService.approveDrive(drive);
    }

    @Override
    public void completeDrive(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Drive drive = driveService.findById(id);
        if(drive.getEndDate().after(new Date())) {
            throw new IllegalStateException("End date of drive is in the future, this drive cannot be completed now.");
        }
        
        driveService.completeDrive(drive);
        vehicleService.updateMileage(drive.getVehicle(), drive.getKm().longValue());
    }

    @Override
    public void cancelDrive(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Drive drive = driveService.findById(id);
        driveService.cancelDrive(drive);
    }

    @Override
    public DriveDTO findById(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Drive drive = driveService.findById(id);
        return (drive == null) ? null : beanMappingService.mapTo(drive, DriveDTO.class);
    }

    @Override
    public Collection<DriveDTO> findAll() throws DataAccessException {
        return beanMappingService.mapTo(driveService.findAll(), DriveDTO.class);
    }

    @Override
    public Collection<DriveDTO> findAllByVehicle(VehicleDTO vehicleDTO) throws DataAccessException {
        if(vehicleDTO == null) {
            throw new IllegalArgumentException("vehicle is null");
        }
        
        Vehicle vehicle = beanMappingService.mapTo(vehicleDTO, Vehicle.class);
        Collection<Drive> drives = driveService.findAllByVehicle(vehicle);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }

    @Override
    public Collection<DriveDTO> findAllByEmployee(EmployeeDTO employeeDTO) throws DataAccessException {
        if(employeeDTO == null) {
            throw new IllegalArgumentException("employee is null");
        }
        
        Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
        Collection<Drive> drives = driveService.findAllByEmployee(employee);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }

    @Override
    public Collection<DriveDTO> findAllByStatus(DriveStatus status) throws DataAccessException {
        if(status == null) {
            throw new IllegalArgumentException("status is null");
        }
        
        Collection<Drive> drives = driveService.findAllByStatus(status);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }

    @Override
    public Collection<DriveDTO> findAllByTimeInterval(Date startDate, Date endDate) throws DataAccessException {
        if(startDate == null) {
            throw new IllegalArgumentException("start date is null");
        }
        if(endDate == null) {
            throw new IllegalArgumentException("end date is null");
        }
        if(endDate.before(startDate)) {
            throw new IllegalArgumentException("end date is before start date");
        }
        
        Collection<Drive> drives = driveService.findAllByTimeInterval(startDate, endDate);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }
}
