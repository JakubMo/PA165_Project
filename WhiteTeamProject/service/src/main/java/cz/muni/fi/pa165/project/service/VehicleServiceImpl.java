/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/25/2015
 */
@Service
public class VehicleServiceImpl implements VehicleService {
    
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DriveDao driveDao;

    @Override
    public void createVehicle(Vehicle vehicle) throws DataAccessExceptionImpl {
        if(vehicle == null){
            throw new IllegalArgumentException("vehicle is null");
        }
        
        vehicleDao.create(vehicle);
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) throws DataAccessExceptionImpl {
        if(vehicle == null){
            throw new IllegalArgumentException("vehicle is null");
        }
        
        vehicleDao.delete(vehicle);
    }

    @Override
    public void updateServiceCheckInterval(Vehicle vehicle, int interval) throws DataAccessExceptionImpl {
        if(vehicle == null){
            throw new IllegalArgumentException("vehicle is null");
        }
        if(interval < 1 || interval > 12){
            throw new IllegalArgumentException("interval is either too long or too short");
        }
        
        vehicle.setServiceCheckInterval(interval);
        vehicleDao.update(vehicle);
    }

    @Override
    public void updateMaxMileage(Vehicle vehicle, long maxMileage) throws DataAccessExceptionImpl {
        if(vehicle == null){
            throw new IllegalArgumentException("vehicle is null");
        }
        if(maxMileage < 99999L){
            throw new IllegalArgumentException("maxMileage has to be at least 100.000, not less!");
        }
        
        vehicle.setMaxMileage(maxMileage);
        vehicleDao.update(vehicle);
    }

    @Override
    public void updateMileage(Vehicle vehicle, long mileage) throws DataAccessExceptionImpl {
        if(vehicle == null){
            throw new IllegalArgumentException("vehicle is null");
        }
        if(mileage == 0L){
            return;
        }
        
        vehicle.setMileage(vehicle.getMileage()+mileage);
        vehicleDao.update(vehicle);
    }

    @Override
    public Vehicle getById(Long id) throws DataAccessExceptionImpl {
        return vehicleDao.get(id);
    }

    @Override
    public Vehicle getByVin(String vin) throws DataAccessExceptionImpl {
        return vehicleDao.getByVin(vin);
    }

    @Override
    public List<Vehicle> getAll() throws DataAccessExceptionImpl {
        return vehicleDao.getAll();
    }

    @Override
    public List<Vehicle> getAllByModel(String model) throws DataAccessExceptionImpl {
        return vehicleDao.getAllByModel(model);
    }

    @Override
    public List<Vehicle> getAllByBrand(String brand) throws DataAccessExceptionImpl {
        return vehicleDao.getAllByBrand(brand);
    }

    @Override
    public List<Vehicle> getAllByMileage(Long mileage) throws DataAccessExceptionImpl {
        return vehicleDao.getAllByMileage(mileage);
    }
    
    @Override
    public List<Vehicle> getAllFreeInDate(Date startDate, Date endDate) throws DataAccessExceptionImpl{
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("startDate/endDate is null"); 
        }
        if(endDate.before(startDate)){
            throw new IllegalArgumentException("start date must be before end date");
        }
        
        List<Drive> drivesInDate = driveDao.getAllDrivesByTimeInterval(startDate, endDate);
        List<Vehicle> vehicles = vehicleDao.getAll();
        List<Vehicle> results = new ArrayList<>();
        for(Vehicle v : vehicles){
            for(Drive d : drivesInDate){
                if(v.equals(d.getVehicle())){
                    drivesInDate.remove(d);
                    break;
                }
                results.add(v);
            }
        }
        return results;
    }
}
