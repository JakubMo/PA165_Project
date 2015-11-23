package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * Provides DAO interface for drive record.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/26/2015
 */

public interface DriveDao {
    
    /**
     * Creates collection of all drive records.
     * 
     * @return list of all {@link Drive} records 
     * @throws cz.muni.fi.pa165.project.util.DataAccessException 
     */
    List<Drive> getAll() throws DataAccessException;
    
    /**
     * Creates collection of all drive records for specific car
     * 
     * @param vehicle
     * @return list of {@link Drive} records 
     * @throws cz.muni.fi.pa165.project.util.DataAccessException 
     */
    List<Drive> getAllByVehicle(Vehicle vehicle) throws DataAccessException;
    
    /**
     * Creates collection of all drive records for specific employee
     * 
     * @param employee
     * @return list of all {@link Drive} records 
     * @throws cz.muni.fi.pa165.project.util.DataAccessException 
     */
    List<Drive> getAllByEmployee(Employee employee) throws DataAccessException;
    /**
     * Finds drive record by drive id.
     * 
     * @param id id of wanted drive record
     * @return instance of {@link Drive}
     * @throws cz.muni.fi.pa165.project.util.DataAccessException
     */
    Drive get(Long id) throws DataAccessException;
    
    /**
     * Updates drive record.
     * 
     * @param drive updated {@link Drive} record 
     * @throws cz.muni.fi.pa165.project.util.DataAccessException 
     */
    void update(Drive drive) throws DataAccessException;
    
    /**
     * Deletes drive record.
     * 
     * @param drive {@link Drive} record to be deleted 
     * @throws cz.muni.fi.pa165.project.util.DataAccessException 
     */
    void delete(Drive drive) throws DataAccessException;
    
    /**
     * Inserts new drive record.
     * 
     * @param drive {@link Drive} record to be inserted
     * @throws cz.muni.fi.pa165.project.util.DataAccessException
     */
    void create(Drive drive) throws DataAccessException;
    
}
