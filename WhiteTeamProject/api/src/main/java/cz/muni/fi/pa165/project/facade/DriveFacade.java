package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.DriveCreateDTO;
import cz.muni.fi.pa165.project.dto.DriveDTO;
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
     * @param d drive to be created
     * @throws DataAccessException 
     */
    public void register(DriveCreateDTO d) throws DataAccessException;
    
    /**
     * Deletes given drive.
     * 
     * @param id ID of drive to be deleted
     * @throws DataAccessException 
     */
    public void delete(Long id) throws DataAccessException;
    
    /**
     * Updates start date of given drive.
     * 
     * @param id drive to be updated
     * @param startDate new start date of drive
     * @throws DataAccessException 
     */
    public void updateStartDate(Long id, Date startDate) throws DataAccessException;
    
    /**
     * Updates end date of given drive.
     * 
     * @param id drive to be updated
     * @param endDate new end date of drive
     * @throws DataAccessException 
     */
    public void updateEndDate(Long id, Date endDate) throws DataAccessException;
    
    /**
     * Updates kilometers driven in drive.
     * 
     * @param id drive to be updated
     * @param km amount of driven kilometers
     * @throws DataAccessException 
     */
    public void updateDrivenKilometers(Long id, BigDecimal km) throws DataAccessException;
    
    /**
     * Updates status of given drive.
     * 
     * @param id drive to be updated
     * @param status new status of drive
     * @throws DataAccessException 
     */
    public void updateStatus(Long id, DriveStatus status) throws DataAccessException;
    
    /**
     * Finds drive by given id.
     * 
     * @param id ID of drive to be found
     * @return found drive
     * @throws DataAccessException 
     */
    public DriveDTO get(Long id) throws DataAccessException;
    
    /**
     * Gets all drives.
     * 
     * @return collection of drives
     * @throws DataAccessException 
     */
    public Collection<DriveDTO> getAll() throws DataAccessException;
}
