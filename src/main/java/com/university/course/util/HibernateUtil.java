package com.university.course.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.university.course.model.Course;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:mem:coursedb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                
                // Connection pool settings
                settings.put(Environment.POOL_SIZE, "10");
                settings.put("hibernate.c3p0.min_size", "5");
                settings.put("hibernate.c3p0.max_size", "20");
                settings.put("hibernate.c3p0.timeout", "300");
                settings.put("hibernate.c3p0.max_statements", "50");
                settings.put("hibernate.c3p0.idle_test_period", "3000");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Course.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
                System.out.println("✓ Hibernate SessionFactory created successfully!");
                
            } catch (Exception e) {
                System.err.println("✗ Error creating SessionFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}