package com.ceva;


import org.hibernate.Session;
import org.hibernate.Transaction;
public class InsertTicket1 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Ticket ticket = new Ticket();
        // el objeto customer es nuevo pero tambien sera insertado en la BD
        ticket.setCustomer(customer);
        ticket.setTicketDate(new java.util.Date());

        // al metodo save() le pasamos el objeto ticket pero debido al atributo
        // CascadeType.ALL de la clase Ticket al momento de guardar los cambios en Ticket
        // estos extenderan al registro Customer automaticamente
        session.save(ticket);

        tx.commit();
        session.close();
    }
}
