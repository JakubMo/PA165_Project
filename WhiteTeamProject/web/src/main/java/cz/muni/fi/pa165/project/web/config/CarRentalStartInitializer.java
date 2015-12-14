/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.web.config;

import cz.muni.fi.pa165.project.data.LoadDataFacade;
import java.io.IOException;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.jsp.jstl.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Initializes dispatcher servlet that uses {@link #CarRentalSpringMvcConfig} class for Spring MVC configuration
 * @author jakub
 */
public class CarRentalStartInitializer implements WebApplicationInitializer{
    
    final static Logger log = LoggerFactory.getLogger(CarRentalStartInitializer.class);

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        //context configured in config class
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(sc);
        context.register(CarRentalSpringMvcConfig.class);
        context.refresh();
        sc.addListener(new ContextLoaderListener(context));
        
        //register dispatcher servlet
        ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        //register filter setting utf-8 on all requests
        FilterRegistration.Dynamic encoding = sc.addFilter("encoding", CharacterEncodingFilter.class);
        encoding.setInitParameter("encoding", "utf-8");
        encoding.addMappingForUrlPatterns(null, false, "/*");
        
        //register message base name
        sc.setInitParameter(Config.FMT_LOCALIZATION_CONTEXT, CarRentalSpringMvcConfig.TEXTS);
        /*
        //load data to application
        try {
            context.getBean(LoadDataFacade.class).load();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }*/
    }
    
    
}
