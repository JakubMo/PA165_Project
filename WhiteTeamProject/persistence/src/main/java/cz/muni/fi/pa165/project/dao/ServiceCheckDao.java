package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import java.util.List;
import org.springframework.dao.DataAccessException;

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
    void create(ServiceCheck serviceCheck) throws DataAccessException;
    
    /**
     * Updates service check.
     * 
     * @param serviceCheck updated service check
     */
    void update(ServiceCheck serviceCheck) throws DataAccessException;
    
    /**
     * Deletes service check.
     * 
     * @param serviceCheck service check to be deleted
     */
    void delete(ServiceCheck serviceCheck) throws DataAccessException;
    
    /**
     * Finds service check by id.
     * 
     * @param id id of wanted service check
     * @return instance of {@link ServiceCheck}
     */
    ServiceCheck get(Long id) throws DataAccessException;
    
    /**
     * Creates collection of all service checks.
     * 
     * @return list of all service checks
     */
    List<ServiceCheck> getAll() throws DataAccessException;
    
    /**
     * Finds all service checks of given vehicle.
     * 
     * @param vehicle target vehicle
     * @return collection of service checks of vehicle
     */
    List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws DataAccessException;
}
