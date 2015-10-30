package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Provides implementation of {@link ServiceCheckDao} interface.
 *
 * @author Marek
 */
public class ServiceCheckDaoImpl implements ServiceCheckDao {
    @Override
    public void insertServiceCheck(ServiceCheck serviceCheck) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.save(serviceCheck);

            session.getTransaction().commit();
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void updateServiceCheck(ServiceCheck serviceCheck) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.update(serviceCheck);

            session.getTransaction().commit();
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void deleteServiceCheck(ServiceCheck serviceCheck) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.delete(serviceCheck);

            session.getTransaction().commit();
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public ServiceCheck findServiceCheck(Long id) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            ServiceCheck serviceCheck = (ServiceCheck) session.get(ServiceCheck.class, id);

            session.getTransaction().commit();

            return serviceCheck;
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public List<ServiceCheck> getAllServiceChecks() throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            List<ServiceCheck> serviceChecks = session.createCriteria(ServiceCheck.class).list();

            session.getTransaction().commit();

            return serviceChecks;
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }    

    public List<ServiceCheck> getAllServiceChecksByVehicle(Vehicle vehicle) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            List<ServiceCheck> serviceChecks = session.createQuery("select s from ServiceCheck s where s.vehicle=:v")
                    .setParameter("v", vehicle).list();

            session.getTransaction().commit();

            return serviceChecks;
        }
        catch(HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }
}
