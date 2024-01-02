package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;
public class DeleteProduct {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        Product product = (Product) session.createQuery("from com.ceva.Product where upper(name)=:name")
                .setParameter("name", "Tuercas".toUpperCase())
                .uniqueResult();
        if (product != null) {
            session.delete(product);
        }

        tx.commit();
        session.close();
    }
}
