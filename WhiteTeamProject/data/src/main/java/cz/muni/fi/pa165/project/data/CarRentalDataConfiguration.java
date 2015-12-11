package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * ServiceConfig configuration extended by LoadDataFacade bean
 * 
 * @author jakub
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses={LoadDataFacadeImpl.class})
public class CarRentalDataConfiguration {
    
}
