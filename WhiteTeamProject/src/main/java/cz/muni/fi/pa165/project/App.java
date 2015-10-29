package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.DriveDao;
import cz.muni.fi.pa165.project.dao.DriveDaoImpl;
import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.dao.EmployeeDaoImpl;
import cz.muni.fi.pa165.project.dao.VehicleDao;
import cz.muni.fi.pa165.project.dao.VehicleDaoImpl;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
            /*
             EmployeeDao employeeDao = new EmployeeDaoImpl();

             Employee employee = new Employee();
             //employee.setId(0L);
             employee.setFirstname("New");
             employee.setLastname("Employee");
             employee.setEmail("fake@email.com");
             employee.setPhoneNumber("55-448-221");
             employee.setRole("role");
             employee.setCredit(new BigDecimal(10000));

             employeeDao.create(employee);
             //employee.setId(1L);
             //employeeDao.insertEmployee(employee);
             employee.setFirstname("Updated");
             employeeDao.update(employee);
             Long id = employee.getId();
             System.out.println(employeeDao.findById(id).toString());
             */
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            Employee employee = new Employee();
            employee.setFirstname("New");
            employee.setLastname("Employee");
            employee.setEmail("fake@email.com");
            employee.setPhoneNumber("55-448-221");
            employee.setRole("role");
            employee.setCredit(new BigDecimal(10000));
            employeeDao.create(employee);
            Long employeeId = employee.getId();
            
            VehicleDao vehicleDao = new VehicleDaoImpl();
            Vehicle vehicle = new Vehicle();
            vehicle.setVin("IPK204t4FG");
            vehicle.setModel("Mustang");
            vehicle.setBrand("Ford");
            vehicle.setType("5 door");
            vehicle.setYearOfProduction(new Date(2009, 0, 0));
            vehicle.setEngineType("petrol");
            vehicle.setMaxMileage(200000L);
            vehicle.setMileage(10000L);
            vehicle.setServiceInterval(new Date(0, 6, 0));
            vehicleDao.insert(vehicle);
            String vehicleVin = vehicle.getVin();
            
            employee = employeeDao.findById(employeeId);
            vehicle = vehicleDao.findByVin(vehicleVin);
            
            DriveDao driveDao = new DriveDaoImpl();
            Drive drive = new Drive();
            drive.setEmployee(employee);
            drive.setVehicle(vehicle);
            drive.setStartDate(new Date(2015, 9, 28));
            drive.setEndDate(new Date(2015, 9, 29));
            drive.setState(DriveStatus.REQUESTED);
            driveDao.insertDrive(drive);
            Long driveId = drive.getId();
            
            drive = driveDao.getDrive(driveId);
            
            System.out.println(drive.toString());
            
            HibernateUtil.getSessionFactory().close();
	}
}
