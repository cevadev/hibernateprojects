package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UpdateProduct2 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        // arreglo de datos, los productos que se encuentren en la BD sera actualizados
        // los productos que no se encuentren entonces seran agregados
        Object data[] = {
                "Tuercas",       10d,
                "Serrotillo",    125.1d,
                "Navaja multiusos",        300.2d,
                "Martillo de carpintero",           130.1d,
                "Tijeras",         120d,
        };

        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from com.ceva.Product where upper(name)=:name");
        for (int n=0; n<data.length; n+=2) {
            Product product = (Product) query.setParameter("name", ((String)data[n]).toUpperCase())
                    .uniqueResult();
            // validamos si el producto no se encuentra en BD
            if (product == null) {
                product = new Product();
                product.setName((String)data[n]);
            }
            // el producto se encuentra en la BD
            product.setPrice((Double)data[n+1]);
            session.save(product);
        }

        tx.commit();
        session.close();
    }
}
