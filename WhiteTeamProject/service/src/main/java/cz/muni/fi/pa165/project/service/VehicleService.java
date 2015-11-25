package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;

/**
 * An interface that defines a service access to the {@link Vehicle} entity.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/24/2015
 */
public interface VehicleService {
    
    public void addVehicle(Vehicle vehicle) throws DataAccessExceptionImpl;
    
    public void get(Long id) throws DataAccessExceptionImpl;
    
    public void getAll() throws DataAccessExceptionImpl;
    
    
}
