package cz.muni.fi.pa165.project.dto;

/**
 * Employee to be authenticated entity.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/22/15.
 */
public class EmployeeAuthenticateDTO {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
