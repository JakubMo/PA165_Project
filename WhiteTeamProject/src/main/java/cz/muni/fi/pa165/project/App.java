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
import org.hibernate.Session;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
            
            HibernateUtil.getSessionFactory().close();
	}
}
