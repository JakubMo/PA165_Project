package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
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
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException 
     */
    public List<Drive> getAllDrives() throws HibernateErrorException;
    
    /**
     * Creates collection of all drive records for specific car
     * 
     * @param vehicle
     * @return list of {@link Drive} records 
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException 
     */
    public List<Drive> getAllDrivesByVehicle(Vehicle vehicle) throws HibernateErrorException;
    
    /**
     * Creates collection of all drive records for specific employee
     * 
     * @param employee
     * @return list of all {@link Drive} records 
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException 
     */
    public List<Drive> getAllDrivesByEmployee(Employee employee) throws HibernateErrorException;
    /**
     * Finds drive record by drive id.
     * 
     * @param id id of wanted drive record
     * @return instatnce of {@link Drive}
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public Drive getDrive(Long id) throws HibernateErrorException;
    
    /**
     * Updates drive record.
     * 
     * @param drive updated {@link Drive} record 
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException 
     */
    public void updateDrive(Drive drive) throws HibernateErrorException;
    
    /**
     * Deletes drive record.
     * 
     * @param drive {@link Drive} record to be deleted 
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException 
     */
    public void deleteDrive(Drive drive) throws HibernateErrorException;
    
    /**
     * Inserts new drive record.
     * 
     * @param drive {@link Drive} record to be inserted
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException
     */
    public void insertDrive(Drive drive) throws HibernateErrorException;
    
}
