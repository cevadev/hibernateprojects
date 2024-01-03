package com.ceva;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Category {
    private int id_category;
    private String name;

    // Collecion que nos permite declarar la relacion bidireccional
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

    // 1ra version - bidireccional
    //@ManyToMany (cascade=CascadeType.ALL)

    // 2da version: many-to-many unidireccional. inverseJoinColumns representa el campo que no
    //              posee la asociacion
    // La manera como funciona en esta segunda version es solamente colocando en
    // joinColumns el nombre de la columna que le corresponde a la llave primaria de la clase
    // que estamos mapeando
//    @ManyToMany (cascade=CascadeType.ALL)
//    @JoinTable(name="product_category",
//            joinColumns = @JoinColumn(name="id_category"),
//            inverseJoinColumns = @JoinColumn(name="id_product")
//    )

    // 3ra version: many-to-many bidireccional es decir
    //              en la clase products hay una coleccion a las categorias
//    @ManyToMany(cascade=CascadeType.ALL, mappedBy="categories")

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable (name="product_category",
        joinColumns = @JoinColumn(name="id_category"),
        inverseJoinColumns = @JoinColumn(name="id_product")
    )

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        // establecemos la relacion en ambos lados de la relacion
        getProducts().add(product);
        product.getCategories().add(this);
    }
}
