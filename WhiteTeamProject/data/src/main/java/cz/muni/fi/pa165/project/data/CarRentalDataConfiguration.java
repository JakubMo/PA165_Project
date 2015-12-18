package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.service.config.ServiceConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * ServiceConfig configuration extended by LoadDataFacade bean
 * 
 * @author jakub
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses={LoadDataFacadeImpl.class})
public class CarRentalDataConfiguration {
	final static Logger log = LoggerFactory.getLogger(CarRentalDataConfiguration.class);

	@Autowired
	LoadDataFacade loadDataFacade;

	@PostConstruct
	public void dataLoading() throws IOException {
		log.debug("dataLoading()");
		loadDataFacade.load();
	}
}
