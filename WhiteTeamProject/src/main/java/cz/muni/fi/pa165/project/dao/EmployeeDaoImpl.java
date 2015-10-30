package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.HibernateErrorException;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import org.hibernate.HibernateException;

/**
 * {@link DriveDao} interface implementation.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void create(Employee employee) throws HibernateErrorException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        session.save(employee);

        session.getTransaction().commit();
    }

    @Override
    public List findAll() throws HibernateErrorException {
        try {
            List employees;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            employees = session.createCriteria(Employee.class).list();

            session.getTransaction().commit();

            return employees;
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public Employee findById(Long id) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            Employee employee = (Employee) session.get(Employee.class, id);

            session.getTransaction().commit();

            return employee;
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void update(Employee employee) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.update(employee);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }

    @Override
    public void delete(Employee employee) throws HibernateErrorException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            session.delete(employee);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new HibernateErrorException(ex);
        }
    }
}
