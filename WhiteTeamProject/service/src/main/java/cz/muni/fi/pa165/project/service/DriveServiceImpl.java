package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.service.util.Transition;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link DriveService} interface. Provides implementation of the business logic.
 *
 * @author Marek
 */
@Service
public class DriveServiceImpl implements DriveService {
    
    @Autowired
    private DriveDao driveDao;
    
    private final Set<Transition> allowedTransitions = new HashSet<>();
    {
        allowedTransitions.add(new Transition(DriveStatus.REQUESTED, DriveStatus.APPROVED));
        allowedTransitions.add(new Transition(DriveStatus.REQUESTED, DriveStatus.CANCELLED));
        allowedTransitions.add(new Transition(DriveStatus.APPROVED, DriveStatus.COMPLETED));
        allowedTransitions.add(new Transition(DriveStatus.APPROVED, DriveStatus.CANCELLED));
    }
    
    private void checkTransitions(DriveStatus oldState, DriveStatus newState) {
        if(!allowedTransitions.contains(new Transition(oldState, newState))) {
            throw new IllegalStateException("The transition from " + oldState + " to " + newState + " is not allowed.");
        }
    }
    
    @Override
    public void createDrive(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("drive is null");
        }
                
        driveDao.create(drive);
    }

    @Override
    public void deleteDrive(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        driveDao.delete(drive);
    }

    @Override
    public void changeStartDate(Drive drive, Date startDate) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(startDate == null) {
            throw new IllegalArgumentException("start date is null");
        }        
        if(drive.getEndDate().before(startDate)) {
            throw new IllegalArgumentException("start date must be before end date");
        }
        
        drive.setStartDate(startDate);
        driveDao.update(drive);
    }

    @Override
    public void changeEndDate(Drive drive, Date endDate) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(endDate == null) {
            throw new IllegalArgumentException("end date is null");
        }        
        if(endDate.before(drive.getStartDate())) {
            throw new IllegalArgumentException("start date must be before end date");
        }
        
        drive.setEndDate(endDate);
        driveDao.update(drive);
    }

    @Override
    public void changeDrivenKilometers(Drive drive, BigDecimal km) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(km == null) {
            throw new IllegalArgumentException("km is null");
        }
        if(km.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("km must be non-negative");
        }
        
        drive.setKm(km);
        driveDao.update(drive);
    }
    
    @Override
    public void approveDrive(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        checkTransitions(drive.getDriveStatus(), DriveStatus.APPROVED);
        drive.setDriveStatus(DriveStatus.APPROVED);
        driveDao.update(drive);
    }

    @Override
    public void completeDrive(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(drive.getEndDate().after(new Date())) {
            throw new IllegalStateException("End date of drive is in the future, this drive cannot be completed now.");
        }
        
        checkTransitions(drive.getDriveStatus(), DriveStatus.COMPLETED);
        drive.setDriveStatus(DriveStatus.COMPLETED);
        driveDao.update(drive);
    }

    @Override
    public void cancelDrive(Drive drive) throws DataAccessException {
        if(drive == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        checkTransitions(drive.getDriveStatus(), DriveStatus.CANCELLED);
        drive.setDriveStatus(DriveStatus.CANCELLED);
        driveDao.update(drive);
    }

    @Override
    public Drive findById(Long id) throws DataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        return driveDao.get(id);
    }

    @Override
    public Collection<Drive> findAll() throws DataAccessException {
        return driveDao.getAll();
    }

    @Override
    public Collection<Drive> findAllByVehicle(Vehicle vehicle) throws DataAccessException {
        if(vehicle == null) {
            throw new IllegalArgumentException("vehicle is null");
        }
        
        return driveDao.getAllByVehicle(vehicle);
    }

    @Override
    public Collection<Drive> findAllByEmployee(Employee employee) throws DataAccessException {
        if(employee == null) {
            throw new IllegalArgumentException("employee is null");
        }
        
        return driveDao.getAllByEmployee(employee);
    }

    @Override
    public Collection<Drive> findAllByStatus(DriveStatus status) throws DataAccessException {
        if(status == null) {
            throw new IllegalArgumentException("status is null");
        }
        
        Collection<Drive> result = new ArrayList<>();
        for(Drive drive : driveDao.getAll()) {
            if(drive.getDriveStatus() == status) {
                result.add(drive);
            }
        }        
        return result;
    }

    @Override
    public Collection<Drive> findAllByTimeInterval(Date startDate, Date endDate) throws DataAccessException {
        if(startDate == null) {
            throw new IllegalArgumentException("start date is null");
        }
        if(endDate == null) {
            throw new IllegalArgumentException("end date is null");
        }
        if(endDate.before(startDate)) {
            throw new IllegalArgumentException("end date is before start date");
        }
        
        return driveDao.getAllDrivesByTimeInterval(startDate, endDate);
    }
}
