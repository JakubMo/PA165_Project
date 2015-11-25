package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.DriveCreateDTO;
import cz.muni.fi.pa165.project.dto.DriveDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import org.springframework.dao.DataAccessException;

/**
 * Drive facade interface.
 *
 * @author Marek
 */
public interface DriveFacade {
    /**
     * Creates new drive.
     * 
     * @param driveCreateDTO drive to be created
     * @throws DataAccessException 
     */
    public void createDrive(DriveCreateDTO driveCreateDTO) throws DataAccessException;
    
    /**
     * Deletes given drive.
     * 
     * @param id ID of drive to be deleted
     * @throws DataAccessException 
     */
    public void deleteDrive(Long id) throws DataAccessException;
    
    /**
     * Updates start date of given drive.
     * 
     * @param id ID of drive to be updated
     * @param startDate new start date of drive
     * @throws DataAccessException 
     */
    public void changeStartDate(Long id, Date startDate) throws DataAccessException;
    
    /**
     * Updates end date of given drive.
     * 
     * @param id ID of drive to be updated
     * @param endDate new end date of drive
     * @throws DataAccessException 
     */
    public void changeEndDate(Long id, Date endDate) throws DataAccessException;
    
    /**
     * Updates kilometers driven in drive.
     * 
     * @param id ID of drive to be updated
     * @param km amount of driven kilometers
     * @throws DataAccessException 
     */
    public void changeDrivenKilometers(Long id, BigDecimal km) throws DataAccessException;
    
    /**
     * Approves given drive.
     * 
     * @param id ID of drive to be approved
     * @throws DataAccessException 
     */
    public void approveDrive(Long id) throws DataAccessException;
    
    /**
     * Completes given drive.
     * 
     * @param id ID of drive to be completed
     * @throws DataAccessException 
     */
    public void completeDrive(Long id) throws DataAccessException;
    
    /**
     * Cancels given drive.
     * 
     * @param id ID of drive to be canceled
     * @throws DataAccessException 
     */
    public void cancelDrive(Long id) throws DataAccessException;
    
    /**
     * Finds drive by given id.
     * 
     * @param id ID of drive to be found
     * @return found drive
     * @throws DataAccessException 
     */
    public DriveDTO findById(Long id) throws DataAccessException;
    
    /**
     * Gets all drives.
     * 
     * @return collection of drives
     * @throws DataAccessException 
     */
    public Collection<DriveDTO> findAll() throws DataAccessException;
    
    /**
     * Gets all drives of given vehicle.
     * 
     * @param vehicleDTO vehicle which drives should be found
     * @return collection of drives of given vehicle
     * @throws DataAccessException 
     */
    public Collection<DriveDTO> findAllByVehicle(VehicleDTO vehicleDTO) throws DataAccessException;
    
    /**
     * Gets all drives of given employee.
     * 
     * @param employeeDTO employee which drives should be found
     * @return collection of drive of given employee
     * @throws DataAccessException 
     */
    public Collection<DriveDTO> findAllByEmployee(EmployeeDTO employeeDTO) throws DataAccessException;
    
    /**
     * Gets all drives of given drive status.
     * 
     * @param status status of wanted drives
     * @return collection of drives of given status
     * @throws DataAccessException 
     */
    public Collection<DriveDTO> findAllByStatus(DriveStatus status) throws DataAccessException;
}
