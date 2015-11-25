package cz.muni.fi.pa165.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cz.muni.fi.pa165.project.enums.Category;

import java.math.BigDecimal;
import java.util.List;

/**
 * Employee entity
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/24/15.
 */

public class EmployeeCreateDTO {

	/**
	 * Employee id.
	 */
	@NotNull
	private Long id;

	/**
	 * Employee first name.
	 */
	@NotNull
	@Size(min = 1, max = 20)
	private String firstname;

	/**
	 * Employee last name.
	 */
	@NotNull
	@Size(min = 1, max = 30)
	private String lastname;

	/**
	 * Employee email.
	 */
	@NotNull
	@Size(min = 6, max = 50)
	private String email;

	/**
	 * Employee phone number.
	 */
	@NotNull
	@Size(min = 1, max = 20)
	private String phoneNumber;

	/**
	 * Employee role in organization.
	 */
	@NotNull
	@Size(min = 1, max = 20)
	private String role;

	/**
	 * Available credit of employee.
	 */
	@NotNull
	private BigDecimal credit;

	/**
	 * Category in which employee belongs to. Defines which cars he can borrow.
	 */
	@NotNull
	private Category category;

	/**
	 * List of the employee's drives
	 */
	private List<Long> drives;

	/**
	 * Constructor without parameters. Creates new object without preset attributes values.
	 */
	public EmployeeCreateDTO() {
	}

	/**
	 * Constructor with all attributes to be set.
	 *
	 * @param firstname   First name of employee.
	 * @param lastname    Last name of employee.
	 * @param email       Email of employee.
	 * @param phoneNumber Phone number of employee.
	 * @param role        Role of employee.
	 * @param credit      Credit of employee.
	 * @param category    Category in which employee belongs to.
	 * @param drives      List of employee's drives.
	 */
	public EmployeeCreateDTO(String firstname, String lastname, String email, String phoneNumber, String role, BigDecimal credit, Category category, List<Long> drives) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.credit = credit;
		this.category = category;
		this.drives = drives;
	}

	/**
	 * Getter for {@link #id}.
	 *
	 * @return {@link #id} of employee.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for {@link #id}.
	 *
	 * @param id to be set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for {@link #firstname}.
	 *
	 * @return {@link #firstname} of employee.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Setter for {@link #firstname}.
	 *
	 * @param firstname to be set.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Getter for {@link #lastname}.
	 *
	 * @return {@link #lastname} of employee.
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Setter for {@link #lastname}.
	 *
	 * @param lastname to be set.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Getter for {@link #email}.
	 *
	 * @return {@link #email} of employee.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for {@link #email}.
	 *
	 * @param email to be set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for {@link #phoneNumber}.
	 *
	 * @return {@link #phoneNumber} of employee.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for {@link #phoneNumber}.
	 *
	 * @param phoneNumber to be set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Getter for {@link #role}.
	 *
	 * @return {@link #role} of employee.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Setter for {@link #role}.
	 *
	 * @param role to be set.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Getter for {@link #credit}.
	 *
	 * @return {@link #credit} of employee.
	 */
	public BigDecimal getCredit() {
		return credit;
	}

	/**
	 * Setter for {@link #credit}.
	 *
	 * @param credit to be set.
	 */
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	/**
	 * Getter for {@link #category}.
	 *
	 * @return {@link #category} of employee.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Setter for {@link #category}.
	 *
	 * @param category to be set.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Getter for {@link #drives}.
	 *
	 * @return {@link #drives} of employee.
	 */
	public List<Long> getDrives() {
		return drives;
	}

	/**
	 * Setter for {@link #drives}
	 *
	 * @param drives to be set.
	 */
	public void setDrives(List<Long> drives) {
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
		if (o == null || !(o instanceof EmployeeDTO)) return false;

		EmployeeCreateDTO emploeeCreateDTO = (EmployeeCreateDTO) o;

		if (credit == null || email == null || firstname == null || id == null || lastname == null || phoneNumber == null || role == null || category == null)
			return false;
		if (credit.compareTo(emploeeCreateDTO.credit) != 0) return false;
		if (!email.equals(emploeeCreateDTO.email)) return false;
		if (!firstname.equals(emploeeCreateDTO.firstname)) return false;
		if (!id.equals(emploeeCreateDTO.id)) return false;
		if (!lastname.equals(emploeeCreateDTO.lastname)) return false;
		if (!phoneNumber.equals(emploeeCreateDTO.phoneNumber)) return false;
		if (!role.equals(emploeeCreateDTO.role)) return false;
		return category.equals(emploeeCreateDTO.category);

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