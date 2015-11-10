package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
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
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public void create(ServiceCheck serviceCheck) throws HibernateErrorException;
    
    /**
     * Updates service check.
     * 
     * @param serviceCheck updated service check
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public void update(ServiceCheck serviceCheck) throws HibernateErrorException;
    
    /**
     * Deletes service check.
     * 
     * @param serviceCheck service check to be deleted
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public void delete(ServiceCheck serviceCheck) throws HibernateErrorException;
    
    /**
     * Finds service check by id.
     * 
     * @param id id of wanted service check
     * @return instance of {@link ServiceCheck}
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public ServiceCheck get(Long id) throws HibernateErrorException;
    
    /**
     * Creates collection of all service checks.
     * 
     * @return list of all service checks
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public List<ServiceCheck> getAll() throws HibernateErrorException;
    
    /**
     * Finds all service checks of given vehicle.
     * 
     * @param vehicle target vehicle
     * @return collection of service checks of vehicle
     * @throws cz.muni.fi.pa165.project.util.HibernateErrorException thrown when error in hibernate occurs
     */
    public List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws HibernateErrorException;
}
