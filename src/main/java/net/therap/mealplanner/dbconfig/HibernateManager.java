package net.therap.mealplanner.dbconfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

/**
 * @author bashir
 * @since 10/18/16
 */
public class HibernateManager {

    private static SessionFactory sessionFactory = null;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

    public void initializeSessionFactory() {
        System.out.println("Initializing Session Factory");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Session getSession() {
        try {
            if (null != getSessionFactory().getCurrentSession()) {
                return getSessionFactory().getCurrentSession();
            } else {
                return getSessionFactory().openSession();
            }
        } catch (Exception e) {
            return getSessionFactory().openSession();
        }
    }

    public static void closeSession() {
        sessionFactory.getCurrentSession().close();
    }

}
