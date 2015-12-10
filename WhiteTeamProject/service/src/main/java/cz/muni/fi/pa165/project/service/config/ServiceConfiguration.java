package cz.muni.fi.pa165.project.service.config;

import cz.muni.fi.pa165.project.PersistenceLayerContext;
import cz.muni.fi.pa165.project.service.DriveServiceImpl;
import cz.muni.fi.pa165.project.service.facade.DriveFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration for services.
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/26/2015
 */
@Configuration
@Import(PersistenceLayerContext.class)
@ComponentScan(basePackageClasses={DriveServiceImpl.class, DriveFacadeImpl.class})
public class ServiceConfiguration {
    
    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
