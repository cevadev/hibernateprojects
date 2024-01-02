package com.ceva;

import java.util.HashSet;
import java.util.Set;
public class Product {
    private int id_product;
    private String name;
    private double price;
//    private Set<Category> categories = new HashSet<>();

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
/*
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
 */
}
