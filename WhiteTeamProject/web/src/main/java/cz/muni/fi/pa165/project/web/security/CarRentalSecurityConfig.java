package cz.muni.fi.pa165.project.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration for Spring MVC project
 * 
 * @author jakub
 */
@Configuration
@EnableWebSecurity
public class CarRentalSecurityConfig extends WebSecurityConfigurerAdapter {
    
    final static Logger log = LoggerFactory.getLogger(CarRentalSecurityConfig.class);
    
    @Autowired
    CarRentalAuthenticationProvider carRentalAuthenticationProvider;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(carRentalAuthenticationProvider);
        log.debug("added custom authentication provider");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
       
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                    .formLogin()
                        .loginPage("/login/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/j_spring_security_check")
                        .failureUrl("/login?error")
                        .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/login?logout")
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .csrf();
        
        log.debug("authorization settings defined");
    }
}
