package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Employee entity
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */

@Entity
public class Employee {

	/**
	 * Employee id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Employee first name.
	 */
	@Column
	@NotNull
	private String firstname;

	/**
	 * Employee last name.
	 */
	@Column
	@NotNull
	private String lastname;

	/**
	 * Employee email.
	 */
	@Column
	@NotNull
	private String email;

	/**
	 * Employee phone number.
	 */
	@Column
	@NotNull
	private String phoneNumber;

	/**
	 * Employee role in organization.
	 */
	@Column
	@NotNull
	private String role;

	/**
	 * Available credit of employee.
	 */
	@Column
	@NotNull
	private BigDecimal credit;

	/**
	 * Category in which employee belongs to. Defines which cars he can borrow.
	 */
	@Enumerated
	@NotNull
	private Category category;

	/**
	 * List of the employee's drives
	 */
	@OneToMany(mappedBy = "employee")
	private List<Drive> drives;

	public Employee() {
	}

	public Employee(Long id, String firstname, String lastname, String email, String phoneNumber, String role, BigDecimal credit, Category category, List<Drive> drives) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.credit = credit;
		this.category = category;
		this.drives = drives;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Drive> getDrives() {
		return drives;
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", role='" + role + '\'' +
				", credit=" + credit +
				", category=" + category +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Employee)) return false;

		Employee employee = (Employee) o;

		if (credit == null || email == null || firstname == null || id == null || lastname == null || phoneNumber == null || role == null || category == null)
			return false;
		if (!credit.equals(employee.credit)) return false;
		if (!email.equals(employee.email)) return false;
		if (!firstname.equals(employee.firstname)) return false;
		if (!id.equals(employee.id)) return false;
		if (!lastname.equals(employee.lastname)) return false;
		if (!phoneNumber.equals(employee.phoneNumber)) return false;
		if (!role.equals(employee.role)) return false;
		if (!category.equals(employee.category)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = ((id == null) ? 1 : id.hashCode());
		result = 31 * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = 31 * result + (lastname == null ? 0 : lastname.hashCode());
		result = 31 * result + (email == null ? 0 : email.hashCode());
		result = 31 * result + (phoneNumber == null ? 0 : phoneNumber.hashCode());
		result = 31 * result + (role == null ? 0 : role.hashCode());
		result = 31 * result + (credit == null ? 0 : credit.hashCode());
		result = 31 * result + (category == null ? 0 : category.hashCode());
		return result;
	}
}