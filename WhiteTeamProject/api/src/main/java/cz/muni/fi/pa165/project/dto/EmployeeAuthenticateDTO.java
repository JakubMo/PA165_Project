package cz.muni.fi.pa165.project.dto;

/**
 * Employee to be authenticated entity.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/22/15.
 */
public class EmployeeAuthenticateDTO {
	private Long id;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
