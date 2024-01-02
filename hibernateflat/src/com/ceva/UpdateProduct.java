package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateProduct {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        // iniciamos la transaccion
        Transaction tx = session.beginTransaction();

        Product product = (Product) session.get(Product.class, 24);
        product.setPrice(135);
        session.save(product);

        // aplicamos commit de la transaccion
        tx.commit();

        session.close();
    }
}
