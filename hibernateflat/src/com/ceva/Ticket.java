package com.ceva;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@NamedQuery (name="ticket_products", query="select distinct i.product.name from com.ceva.Ticket t join t.items i where t.id_ticket=:id_ticket")
public class Ticket {
    private int id_ticket;
    private Customer customer;
    private java.util.Date ticketDate;
    // La relacion OneToMany se representa con colecciones
    // La clase Ticke tendra una coleccion de elementos TicketItem
    private Set<TicketItem> items = new HashSet<>();

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    /**
     * Establecemos la relacion muchos a uno
     * CascadeType.ALL significa que la clase Ticket deberia extender sus operaciones en cascada a
     * los campos, es decir, al momento de guardar cambios en ticket si tambien hay cambios en la
     * tabla customer estos tambien seran tomados en cuenta en la transaccion
     * Si solo se utiliza la anotacion ManyToOne hibernate asumiria que la referencia a customer
     *  deberia estar en la columna de nombre por eso agregamos la anotacion
     * @JoinColumn  con lo cual dejamos en claro que la referencia con la tabla Customer es por medio
     * del campo id_customer
     */
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name="id_customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP) // indicamos el tipo de fecha a usar, almacenamos fecha y hora
    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    /**
     * marcamos al getter de la coleccion Items con OneToMany
     * mappedBy especifica la entidad a la que pertenece la relacion
     * ahora la entidad Ticket tiene una relacion OneToMany con TicketItem
     * Por defecto se aplica un fetch de tipo lazy
     * FetchType.EAGER significa que hibernate no sera flojo al leer la informacion sino
     * impaciente.
     */
    @OneToMany(cascade=CascadeType.ALL, mappedBy="ticket", fetch = FetchType.EAGER)
    public Set<TicketItem> getItems() {
        return items;
    }

    public void setItems(Set<TicketItem> items) {
        this.items = items;
    }

    // metodo que permite establecer ambos lados de la relacion
    // al ticketitem le agrega el ticket
    // a la coleccion items agrega el ticketitem
    public void addTicketItem(TicketItem ti) {
        ti.setTicket(this);
        getItems().add(ti);
    }
}
