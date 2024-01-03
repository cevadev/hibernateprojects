package com.ceva;

import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase no emplead anotaciones, todas sus asociaciones estan registradas en el archivo
 * Product.hbm.xml
 */
public class Product {
    private int id_product;
    private String name;
    private double price;
    // Set no es la unica forma de colocarle colecciones a las clases, se puede utilizar list, map
    // utilizamos set porque es la coleccion que no repite elementos, si quisiera repertirlos
    // podriamos usar List
    private Set<Category> categories = new HashSet<>();

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
