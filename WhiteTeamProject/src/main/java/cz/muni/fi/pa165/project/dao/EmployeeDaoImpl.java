package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.travelagency.data.dao.EmployeeDao;
import cz.muni.fi.pa165.travelagency.data.entity.Customer;
import cz.muni.fi.pa165.travelagency.data.entity.Employee;
import cz.muni.fi.pa165.travelagency.util.HibernateErrorException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ../DriveDao} interface implementation.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */
@Repository(value = "employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	private EntityManager em;

    @Override
    public void create(Employee employee) throws HibernateErrorException {
		em.persist(employee);
    }

    @Override
    public List findAll() throws HibernateErrorException {
		List<Employee> result = new ArrayList<>();
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Employee.class));
		Query q = em.createQuery(cq);
		result = q.getResultList();
		return result;
    }

    @Override
    public Employee findById(Long id) throws HibernateErrorException {
		Employee result = null;
		result = em.find(Employee.class, id);
		return result;
    }

    @Override
    public void update(Employee employee) throws HibernateErrorException {
		em.merge(employee);
    }

    @Override
    public void delete(Employee employee) throws HibernateErrorException {
		Employee remove = em.getReference(Employee.class, employee.getId());
		em.remove(remove);
    }
}
