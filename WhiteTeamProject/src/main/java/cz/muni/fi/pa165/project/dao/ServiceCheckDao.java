package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import java.util.List;

/**
 * Provides DAO interface for service check.
 *
 * @author Marek
 */
public interface ServiceCheckDao {
    /**
     * Inserts new service check.
     * 
     * @param serviceCheck service check to be inserted
     */
    public void insertServiceCheck(ServiceCheck serviceCheck);
    
    /**
     * Updates existing service check.
     * 
     * @param serviceCheck updated service check.
     */
    public void updateServiceCheck(ServiceCheck serviceCheck);
    
    /**
     * Deletes existing service check.
     * 
     * @param serviceCheck service check to be deleted
     */
    public void deleteServiceCheck(ServiceCheck serviceCheck);
    
    /**
     * Finds service check by id.
     * 
     * @param id id of wanted service check
     * @return instance of {@link ServiceCheck}
     */
    public ServiceCheck findServiceCheck(Long id);
    
    /**
     * Creates collection of all existing service checks.
     * 
     * @return list of all service checks
     */
    public List<ServiceCheck> getAllServiceChecks();
}
