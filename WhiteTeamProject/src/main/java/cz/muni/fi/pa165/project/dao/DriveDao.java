package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import java.util.List;

/**
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/26/2015
 */

public interface DriveDao {
    
    public List<Drive> getAllDrives();
    
    public Drive getDrive(Long id);
    
    public void updateDrive(Drive drive);
    
    public void deleteDrive(Drive drive);
    
    public void insertDrive(Drive drive);
    
}
