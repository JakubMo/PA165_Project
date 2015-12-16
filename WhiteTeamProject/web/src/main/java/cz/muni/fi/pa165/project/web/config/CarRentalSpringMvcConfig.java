package cz.muni.fi.pa165.project.web.config;

import cz.muni.fi.pa165.project.data.CarRentalDataConfiguration;
import cz.muni.fi.pa165.project.web.controllers.VehicleController;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring MVC configuration for Car Rental application
 * 
 * @author jakub
 */
@EnableWebMvc
@Configuration
@Import(CarRentalDataConfiguration.class)
//@ComponentScan(basePackages = "cz.muni.fi.pa165.web.controllers")
@ComponentScan(basePackages = {"cz.muni.fi.pa165.project.web.controllers"})
public class CarRentalSpringMvcConfig extends WebMvcConfigurerAdapter {
    
    final static Logger log = LoggerFactory.getLogger(CarRentalSpringMvcConfig.class);
    
    public final static String TEXTS = "Texts";
    
    /**
     * Maps the main page to index view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to index view");
        registry.addViewController("/").setViewName("index");
    }
    
    /**
     * Enables default Tomcat servlet.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default Tomcat servlet");
        configurer.enable();
    }
    
    /**
     * Mapping to /WEB-INF/jsp/ for view names with .jsp suffix
     * @return {@link #ViewResolver} viewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
       log.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /**
     * Registers base name for messages
     * @return {@link #MessageSource} messageSource
     */
    @Bean
    public MessageSource messageSource() {
        log.debug("registering base name 'Texts' for messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }
    
    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }
}
