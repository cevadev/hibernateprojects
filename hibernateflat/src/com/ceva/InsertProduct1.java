package com.ceva;

import org.hibernate.Session;

/**
 * Programa que agrega un producto a la tabla Product
 */

public class InsertProduct1 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        Product product = new Product();
        product.setName("Cople");
        product.setPrice(1);
        session.save(product);

        session.close();
    }
}
