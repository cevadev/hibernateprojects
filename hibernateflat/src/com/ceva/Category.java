package com.ceva;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
    private int id_category;
    private String name;

    private Set<Product> products = new HashSet<>();

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 1ra version
//    @ManyToMany (cascade=CascadeType.ALL)

    // 2da version: many-to-many unidireccional.
//    @ManyToMany (cascade=CascadeType.ALL)
//    @JoinTable (name="product_category",
//            joinColumns = @JoinColumn(name="id_category"),
//            inverseJoinColumns = @JoinColumn(name="id_product")
//    )

    // 3ra version: many-to-many bidireccional
/*    @ManyToMany(cascade=CascadeType.ALL, mappedBy="categories") */

//    @ManyToMany (cascade=CascadeType.ALL)
//    @JoinTable (name="product_category",
//        joinColumns = @JoinColumn(name="id_category"),
//        inverseJoinColumns = @JoinColumn(name="id_product")
//    )
/*
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        getProducts().add(product);
        product.getCategories().add(this);
    }

 */
}
