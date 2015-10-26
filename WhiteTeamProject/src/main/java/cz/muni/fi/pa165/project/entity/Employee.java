package cz.muni.fi.pa165.project.entity;

import java.math.BigDecimal;

/**
 * @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
 */

//TODO resolve: anotations or xml config
//@Entity
//@Table(name = "employee")
public class Employee {

	//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@Column(name = "id")
	private Long id;

	//	@Column(name = "first_name")
	private String firstname;

	//	@Column(name = "last_name")
	private String lastname;

	//	@Column(name = "email")
	private String email;

	//	@Column(name = "phone_number")
	private String phoneNumber;

	//	@Column(name = "role")
	private String role;

	//	@Column(name = "credit")
	private BigDecimal credit;

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
				'}';
	}

	//TODO equals and hashcode
}