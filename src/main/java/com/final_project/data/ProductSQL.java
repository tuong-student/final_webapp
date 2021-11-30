package com.final_project.data;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.final_project.model.*;

public class ProductSQL {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            try {
                // Hibernate setting
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL,
                        "jdbc:sqlserver://SQL5080.site4now.net;databaseName=db_a7c9ad_demoshop271");
                settings.put(Environment.USER, "db_a7c9ad_demoshop271_admin");
                settings.put(Environment.PASS, "demoshop271");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Product.class);

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
