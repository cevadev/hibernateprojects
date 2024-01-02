package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Insertamos un nuevo registro en la tabla mapeada Customer
 */
public class Test5 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setFirstName("Julio");
        customer.setLastName("Gonzales Altuna");
        session.save(customer);

        tx.commit();
        session.close();
    }
}
