package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
* @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
*/
public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void create(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.save(employee);

		session.getTransaction().commit();
	}

	@Override
	public List findAll() {
		List employees;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		employees = session.createCriteria(Employee.class).list();

		session.getTransaction().commit();

		return employees;
	}

	@Override
	public Employee findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		Employee employee = (Employee) session.get(Employee.class, id);

		session.getTransaction().commit();

		return employee;
	}

	@Override
	public void update(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.update(employee);

		session.getTransaction().commit();
	}

	@Override
	public void delete(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		session.delete(employee);

		session.getTransaction().commit();
	}
}
