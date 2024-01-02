package com.ceva;

import org.hibernate.Session;

/**
 * Clase que Abre y cierra una sesion en Hibernate
 */
public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        session.close();
    }
}
