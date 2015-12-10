package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.EmployeeService;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * * Implementation of {@link EmployeeFacade} interface.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/15/15.
 */
@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade {

	final static Logger log = LoggerFactory.getLogger(EmployeeFacadeImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public void register(EmployeeDTO employeeDTO, String unencryptedPassword) throws DataAccessExceptionImpl {
		if (employeeDTO == null) {
			throw new IllegalArgumentException("Employee to be created is null.");
		}
		if (employeeDTO.getId() != null) {
			throw new IllegalArgumentException("New employee ID must be null.");
		}
		validateEmployee(employeeDTO);
		Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
		employeeService.registerEmployee(employee, unencryptedPassword);
		employeeDTO.setId(employee.getId());
	}

	@Override
	public Collection<EmployeeDTO> getAll() throws DataAccessExceptionImpl {
		return beanMappingService.mapTo(employeeService.getAll(), EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO getByEmail(String email) throws DataAccessExceptionImpl {
		if (email == null) {
			throw new IllegalArgumentException("Given email is null.");
		}
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Given email is empty.");
		}
		if (!validEmail(email)) {
			throw new IllegalArgumentException("Given email is not valid.");
		}
		Employee employee = employeeService.getByEmail(email);
		return (employee == null) ? null : beanMappingService.mapTo(employee, EmployeeDTO.class);
	}

	@Override
	public Collection<EmployeeDTO> getAllByLastName(String lastName) throws DataAccessExceptionImpl {
		if (lastName == null) {
			throw new IllegalArgumentException("Employee last name to be found is null.");
		}
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("Employee last name to be found is empty.");
		}
		return beanMappingService.mapTo(employeeService.getAllByLastName(lastName), EmployeeDTO.class);
	}

	@Override
	public void update(EmployeeDTO employeeDTO) throws DataAccessExceptionImpl {
		if (employeeDTO == null) {
			throw new IllegalArgumentException("Employee to be updated is null.");
		}
		if (employeeDTO.getId() == null) {
			throw new IllegalArgumentException("Updated employee ID can't be null.");
		}
		validateEmployee(employeeDTO);
		Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
		employeeService.update(employee);
	}

	@Override
	public void delete(EmployeeDTO employeeDTO) throws DataAccessExceptionImpl {
		if (employeeDTO == null) {
			throw new IllegalArgumentException("Employee to be deleted is null.");
		}
		if (employeeDTO.getId() == null) {
			throw new IllegalArgumentException("Deleted employee ID can't be null.");
		}
		Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
		employeeService.delete(employee);
	}

	@Override
	public boolean authenticate(EmployeeAuthenticateDTO employeeAuthenticateDTO) {
		return employeeService.authenticate(
				employeeService.get(employeeAuthenticateDTO.getId()), employeeAuthenticateDTO.getPassword());
	}

	private void validateEmployee(EmployeeDTO employeeDTO) {
		if (employeeDTO.getFirstname() == null) {
			throw new IllegalArgumentException("First name of employee is null.");
		}
		if (employeeDTO.getFirstname().isEmpty()) {
			throw new IllegalArgumentException("First name of employee is empty.");
		}
		if (!employeeDTO.getFirstname().chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("First name of employee contains non-alphanumeric characters");
		}
		if (employeeDTO.getLastname() == null) {
			throw new IllegalArgumentException("Last name of employee is null.");
		}
		if (employeeDTO.getLastname().isEmpty()) {
			throw new IllegalArgumentException("Last name of employee is empty.");
		}
		if (!employeeDTO.getLastname().chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Last name of employee contains non-alphanumeric characters");
		}
		if (employeeDTO.getEmail() == null) {
			throw new IllegalArgumentException("Email of employee is null.");
		}
		if (employeeDTO.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email of employee is empty.");
		}
		if (!validEmail(employeeDTO.getEmail())) {
			throw new IllegalArgumentException("Email of employee is not valid.");
		}
		if (employeeDTO.getCategory() == null) {
			throw new IllegalArgumentException("Category of employee is null.");
		}
		if (employeeDTO.getPhoneNumber() == null) {
			throw new IllegalArgumentException("Phone number of employee is null.");
		}
		if (employeeDTO.getPhoneNumber().isEmpty()) {
			throw new IllegalArgumentException("Phone number of employee is empty.");
		}
		if (employeeDTO.getRole() == null) {
			throw new IllegalArgumentException("Role of employee is null.");
		}
		if (employeeDTO.getRole().isEmpty()) {
			throw new IllegalArgumentException("Role of employee is empty.");
		}
		if (employeeDTO.getCredit() == null) {
			throw new IllegalArgumentException("Credit of employee is null.");
		}
		if (employeeDTO.getCredit().compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Credit of employee is less than zero.");
		}
	}

	private boolean validEmail(String email) {
		String EMAIL_PATTERN =
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
