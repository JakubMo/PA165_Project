package cz.muni.fi.pa165.project.web.security;

import cz.muni.fi.pa165.project.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * This class serves as authentication provider and uses EmployeeFacade
 * authentization
 *
 * @author jakub
 */
@Component
public class CarRentalAuthenticationProvider implements AuthenticationProvider {

    final static Logger log = LoggerFactory.getLogger(CarRentalAuthenticationProvider.class);

    @Autowired
    EmployeeFacade employeeFacade;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        try {
            String name = auth.getName();
            String password = auth.getCredentials().toString();
            log.debug("Authenticating for name:" + name + ", password: " + password);
            log.debug("Authentication details: " + auth.getDetails().toString());
            EmployeeAuthenticateDTO employeeAuthenticateDTO = new EmployeeAuthenticateDTO();
            employeeAuthenticateDTO.setEmail(name);
            employeeAuthenticateDTO.setPassword(password);
            if (employeeFacade.authenticate(employeeAuthenticateDTO)) {
                EmployeeDTO employeeDTO = employeeFacade.getByEmail(name);
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("USER"));
                if (employeeDTO.getRole().equals("ADMIN")) {
                    grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
                }
                Authentication newAuth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);

                log.debug("User: " + name + " was successfully authentized.");
                return newAuth;
            } else {
                log.debug("Authentization failed for user: " + name);
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

}
