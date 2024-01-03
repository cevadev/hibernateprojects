package com.ceva;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TicketItem {
    private int id_ticketItem;
    private int nOrder; // para ordenar los items independente de su primary key
    private Ticket ticket;
    private Product product;
    private double price;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_ticketItem() {
        return id_ticketItem;
    }

    public void setId_ticketItem(int id_ticketItem) {
        this.id_ticketItem = id_ticketItem;
    }

    @Column
    public int getnOrder() {
        return nOrder;
    }

    public void setnOrder(int nOrder) {
        this.nOrder = nOrder;
    }

    // TicketItem tiene una relacion ManyToOne con Ticket
    @ManyToOne
    @JoinColumn(name="id_ticket")
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @ManyToOne
    @JoinColumn(name="id_product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
