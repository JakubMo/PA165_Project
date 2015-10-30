package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Provides implementation of {@link DriveDao} interface.
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/27/2015
 */
public class DriveDaoImpl implements DriveDao {
    
    @Override
    public List<Drive> getAllDrives() throws HibernateErrorException{
        try {
            List<Drive> drives = new ArrayList<Drive>();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            drives = session.createCriteria(Drive.class).list();

            session.getTransaction().commit();

            return drives;
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }
    
    @Override
    public List<Drive> getAllDrivesByVehicle(Vehicle vehicle) throws HibernateErrorException{
        List<Drive> drives = new ArrayList<Drive>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        drives = session.createQuery("select d from Drive d where d.vehicle=:v")
                .setParameter("v", vehicle)
                .list();
        
        session.getTransaction().commit();
        
        return drives;
    }
    
    @Override
    public List<Drive> getAllDrivesByEmployee(Employee employee) throws HibernateErrorException{
        List<Drive> drives = new ArrayList<Drive>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        drives = session.createQuery("select d from Drive d where d.employee=:e")
                .setParameter("e", employee)
                .list();
        
        session.getTransaction().commit();
        
        return drives;
    }
    
    @Override
    public Drive getDrive(Long id) throws HibernateErrorException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        Drive drive = (Drive) session.get(Drive.class, id);
        
        session.getTransaction().commit();
        
        return drive;
    }
    
    @Override
    public void updateDrive(Drive drive) throws HibernateErrorException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.update(drive);
        
        session.getTransaction().commit();
    }
    
    @Override
    public void deleteDrive(Drive drive) throws HibernateErrorException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.delete(drive);
        
        session.getTransaction().commit();
    }
    
    @Override
    public void insertDrive(Drive drive) throws HibernateErrorException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.save(drive);
        
        session.getTransaction().commit();
    }
}
