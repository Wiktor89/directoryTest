package net.directory.utilits;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 */
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory = createSessionFactory ();

    private static SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration ();
            configuration.configure ();
            ServiceRegistry registry = new ServiceRegistryBuilder ()
                    .applySettings (configuration.getProperties ()).buildServiceRegistry ();
            return configuration.buildSessionFactory (registry);
        } catch (Exception e) {
            throw new ExceptionInInitializerError (e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory ().close ();
    }
}
