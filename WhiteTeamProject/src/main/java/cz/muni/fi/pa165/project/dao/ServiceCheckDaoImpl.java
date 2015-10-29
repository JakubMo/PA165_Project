package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 * Provides implementation of {@link ServiceCheckDao} interface.
 *
 * @author Marek
 */
public class ServiceCheckDaoImpl implements ServiceCheckDao {
    @Override
    public void insertServiceCheck(ServiceCheck serviceCheck) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.save(serviceCheck);
        
        session.getTransaction().commit();
    }

    @Override
    public void updateServiceCheck(ServiceCheck serviceCheck) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.update(serviceCheck);
        
        session.getTransaction().commit();
    }

    @Override
    public void deleteServiceCheck(ServiceCheck serviceCheck) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        session.delete(serviceCheck);
        
        session.getTransaction().commit();
    }

    @Override
    public ServiceCheck findServiceCheck(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        ServiceCheck serviceCheck = (ServiceCheck) session.get(ServiceCheck.class, id);
        
        session.getTransaction().commit();
        return serviceCheck;
    }

    @Override
    public List<ServiceCheck> getAllServiceChecks() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        
        List<ServiceCheck> serviceChecks = session.createCriteria(ServiceCheck.class).list();
        
        session.getTransaction().commit();
        return serviceChecks;
    }    
}
