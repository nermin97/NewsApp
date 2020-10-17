package com.softraysolutions.hibernate.util;

import com.softraysolutions.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class Db {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:mem:softraysolutions;DB_CLOSE_DELAY=-1");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                settings.put(Environment.SHOW_SQL, true);
                settings.put(Environment.HBM2DDL_AUTO, "create");

                conf.setProperties(settings);
                conf.addAnnotatedClass(User.class);
                conf.addAnnotatedClass(News.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
