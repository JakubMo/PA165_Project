package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import org.springframework.dao.DataAccessException;

/**
 * An interface that defines a service access to the {@link Employee} entity.
 *
 * @author Marek
 */
public interface DriveService {
    /**
     * Creates new drive.
     * 
     * @param drive drive to be created
     * @throws DataAccessException 
     */
    public void createDrive(Drive drive) throws DataAccessException;
    
    /**
     * Deletes given drive.
     * 
     * @param drive drive to be deleted
     * @throws DataAccessException 
     */
    public void deleteDrive(Drive drive) throws DataAccessException;
    
    /**
     * Updates start date of given drive.
     * 
     * @param drive drive to be updated
     * @param startDate new start date of drive
     * @throws DataAccessException 
     */
    public void changeStartDate(Drive drive, Date startDate) throws DataAccessException;
    
    /**
     * Updates end date of given drive.
     * 
     * @param drive drive to be updated
     * @param endDate new end date of drive
     * @throws DataAccessException 
     */
    public void changeEndDate(Drive drive, Date endDate) throws DataAccessException;
    
    /**
     * Updates amount of driven kilometers of given drive.
     * 
     * @param drive drive to be updated
     * @param km new amount of driven kilometers
     * @throws DataAccessException 
     */
    public void changeDrivenKilometers(Drive drive, BigDecimal km) throws DataAccessException;
    
    /**
     * Approves given drive.
     * 
     * @param drive drive to be approved
     * @throws DataAccessException 
     */    
    public void approveDrive(Drive drive) throws DataAccessException;
    
    /**
     * Completes given drive.
     * 
     * @param drive drive to be completed
     * @throws DataAccessException 
     */
    public void completeDrive(Drive drive) throws DataAccessException;
    
    /**
     * Cancels given drive
     * 
     * @param drive drive to be canceled
     * @throws DataAccessException 
     */
    public void cancelDrive(Drive drive) throws DataAccessException;
    
    /**
     * Gets drive by ID.
     * 
     * @param id ID of drive to be found
     * @return found drive
     * @throws DataAccessException 
     */
    public Drive findById(Long id) throws DataAccessException;
    
    /**
     * Gets all drives.
     * 
     * @return collection of all drives
     * @throws DataAccessException 
     */
    public Collection<Drive> findAll() throws DataAccessException;
    
    /**
     * Gets all drives of given vehicle.
     * 
     * @param vehicle vehicle which drives should be found
     * @return collection of drives of given vehicle
     * @throws DataAccessException 
     */
    public Collection<Drive> findAllByVehicle(Vehicle vehicle) throws DataAccessException;
    
    /**
     * Gets all drives of given employee.
     * 
     * @param employee employee which drives should be found
     * @return collection of drives of given employee
     * @throws DataAccessException 
     */
    public Collection<Drive> findAllByEmployee(Employee employee) throws DataAccessException;
    
    /**
     * Gets all drives f given drive status.
     * 
     * @param status status of wanted drives
     * @return collection of drives of given drive status
     * @throws DataAccessException 
     */
    public Collection<Drive> findAllByStatus(DriveStatus status) throws DataAccessException;
}
