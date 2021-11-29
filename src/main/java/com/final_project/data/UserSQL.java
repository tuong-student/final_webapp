package com.final_project.data;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.final_project.model.*;

public class UserSQL {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            try {
                // Hibernate setting
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL, "jdbc:sqlserver://localhost;databaseName=UserTest");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "huynhcongdat123");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                System.out.println("can't connect to DB");
                System.out.println(e);
            }
        }
        return sessionFactory;
    }
}
