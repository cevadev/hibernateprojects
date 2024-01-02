package com.ceva;

import org.hibernate.Session;

/**
 * Programa que primero valida que el registro a insertar no exista en la BD
 * Si no existe, entonces lo agrega.
 */
public class InsertProduct2 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        // 1. creacion del producto
        Product product = new Product();
        product.setName("Cople");
        product.setPrice(1);

        // 2. verificacion de exsistencia previa del producto en la BD. Retornamos un objeto Number
        //    lo convertimos a valor int
        int count = ((Number) session.createQuery("select count(*) from com.ceva.Product where upper(name)=:name")
                .setParameter("name", product.getName().toUpperCase())
                .uniqueResult()).intValue();
        if (count == 0) {
            // No existe producto en el BD por lo tanto save()
            session.save(product);
        }

        session.close();
    }
}
