package com.ceva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Una forma de configurar Hibernate
 * La opcion mas simple de crear un factory session
 */
public class HibernateUtil {
    // objeto sessionFactory la primera vez la inicializamos y las proximas veces usamos ek
    // factory creado
    private static SessionFactory sessionFactory;

    private static void createSessionFactory() {
        // service register lee el archivo hibernate.cfg para configurar hibernate
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    // metodo que crea una sesion en hibernate
    public static Session newSession() throws HibernateException {
        if (sessionFactory == null)
            // creamos la sesion por primera vez
            createSessionFactory();
        // utilizamos la session que ya existe
        return sessionFactory.openSession();
    }
}
