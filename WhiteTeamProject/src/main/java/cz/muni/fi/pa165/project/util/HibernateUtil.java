package cz.muni.fi.pa165.project.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
* @author Tomas Borcin | tborcin@redhat.com | created: 10/25/15.
*/

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
        private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
                    Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    
                    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
