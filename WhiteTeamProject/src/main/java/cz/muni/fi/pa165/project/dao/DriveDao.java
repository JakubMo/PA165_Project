package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
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
     */
    public List<Drive> getAllDrives();
    
    /**
     * Creates collection of all drive records for specific car
     * 
     * @param vehicle
     * @return list of {@link Drive} records 
     */
    public List<Drive> getAllDrivesByVehicle(Vehicle vehicle);
    
    /**
     * Creates collection of all drive records for specific employee
     * 
     * @param employee
     * @return list of all {@link Drive} records 
     */
    public List<Drive> getAllDrivesByEmployee(Employee employee);
    /**
     * Finds drive record by drive id.
     * 
     * @param id id of wanted drive record
     * @return instatnce of {@link Drive}
     */
    public Drive getDrive(Long id);
    
    /**
     * Updates drive record.
     * 
     * @param drive updated {@link Drive} record 
     */
    public void updateDrive(Drive drive);
    
    /**
     * Deletes drive record.
     * 
     * @param drive {@link Drive} record to be deleted 
     */
    public void deleteDrive(Drive drive);
    
    /**
     * Inserts new drive record.
     * 
     * @param drive {@link Drive} record to be inserted
     */
    public void insertDrive(Drive drive);
    
}
