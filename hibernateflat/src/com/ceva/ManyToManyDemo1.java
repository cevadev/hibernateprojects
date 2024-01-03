package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ManyToManyDemo1 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        // query para buscar un producto
        Query findProd = session.createQuery("from com.ceva.Product where upper(name)=:name");
        // query para buscar una categoria
        Query findCat  = session.createQuery("from Category where upper(name)=:name");

        String productName = "Martillo de carpintero";
        String categoryName = "Ferreteria";

        // buscamos el producto Martillo de carpinteria
        Product martillo = (Product) findProd.setParameter("name", productName.toUpperCase())
                .uniqueResult();
        // buscamos la categoria Ferreteria
        Category category = (Category) findCat.setParameter("name", categoryName.toUpperCase())
                .uniqueResult();

        // si la categoria no existe, la agregamos a la BD
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category.getProducts().add(martillo);
            // guardamos la categoria
            session.save(category);
        }
        // si encontramos la categoria no tiene productos
        else if (category.getProducts().size() == 0) {
            // Agregar por el lado de categoria
            // decimos category -> obtenemos lista de productos - > agregamos martillo
            category.addProduct(martillo);
            //category.getProducts().add(martillo);

            // Agregar por el lado de producto
            // decimos producto -> obtengo la lista de caterogias -> agregamos categoria
            //martillo.getCategories().add(category);
        }
        // si encontramos categoria y si tiene productos
        else {
            // esta operaciones de remove solo altera la relacion de muchos a muchos
            category.getProducts().remove(martillo);
            //martillo.getCategories().remove(category); // solo borra el registro de product_category
        }

        tx.commit();
        session.close();
    }
}
