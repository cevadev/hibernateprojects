package com.ceva;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad Customer mapeada con Hibernate
 */
@Entity
public class Customer implements Serializable {
    private int id_customer;
    private String firstName;
    private String lastName;

    // indicamos cual es la primary key y como se genera el valor
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    // en cada get de la columna para hibernate agregamos la anotacion @Column
    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

