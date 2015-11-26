package cz.muni.fi.pa165.project.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for services.
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/26/2015
 */
@Configuration
@ComponentScan(basePackages="cz.muni.fi.pa165.project.service")
public class ServiceConfiguration {
    
    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        //dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }
    
    /*public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Vehicle.class, VehicleDTO.class);
            mapping(Vehicle.class, VehicleCreateDTO.class);
            
        }
    }*/
}
