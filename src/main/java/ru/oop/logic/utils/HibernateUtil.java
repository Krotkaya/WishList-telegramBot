package ru.oop.logic.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            System.out.println("Initializing Hibernate...");

            System.out.println("Step 1: Creating Configuration instance...");
            Configuration configuration = new Configuration();

            System.out.println("Step 2: Loading configuration file...");
            configuration.configure(); // Убедитесь, что файл доступен

            System.out.println("Step 3: Building SessionFactory...");
            sessionFactory = configuration.buildSessionFactory();

            System.out.println("SessionFactory initialized successfully.");
        } catch (Throwable ex) {
            System.err.println("Error initializing SessionFactory: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}