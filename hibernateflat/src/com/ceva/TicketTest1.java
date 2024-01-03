package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class TicketTest1 {
    private static void createTicket(Session session) {
        // 1. Obtenemos el Customer con id 1
        Customer customer = session.get(Customer.class, 1);

        // buscamos los sgtes productos
        String itemNames[] = {"Nivel", "Tijeras", "Navaja multiusos", "Serrotillo"};
        // por cada elementos del array itemNames creamos un objeto Product y lo guardamos en el array
        Product products[] = new Product[itemNames.length];
        // query para buscamos los productos del array itemName en la BD
        Query query = session.createQuery("from com.ceva.Product where upper(name)=:name");
        for (int n=0; n<itemNames.length; n++) {
            Product p = (Product) query.setParameter("name", itemNames[n].toUpperCase())
                    .uniqueResult();
            if (p == null) {
                System.out.println("Producto no encontrado: " + itemNames[n]);
                return;
            }
            products[n] = p;
        }

        // Construimos un objeto Ticket
        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        // agregamos al Ticket los TicketItems
        for (int n=0; n<products.length; n++) {
            Product p = products[n];
            // construimos TicketItem
            TicketItem ti = new TicketItem();
            ti.setnOrder(n+1);
            ti.setProduct(p);
            ti.setPrice(p.getPrice());

            // agregamos el objeto Ticket para la relacion con TicketItem
            ti.setTicket(ticket);
            // agregamos el TicketItem a la coleccion items del objeto Ticket para la relacion con Ticket
            ticket.getItems().add(ti);
        }
        ticket.setTicketDate(new java.util.Date()); // fecha de Ticket
        session.save(ticket);
    }

    private static void readTicket(Session session) {
        // leemos el ticket con ud 2
        Ticket ticket = session.get(Ticket.class, 2);

        // imprimimos la cabecera del ticket
        System.out.println("id_ticket: " + ticket.getId_ticket());
        System.out.println("customer : " + ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
        System.out.println("date     : " + ticket.getTicketDate());
        System.out.println("# items  : " + ticket.getItems().size());

        /**
         * imprimimos el detalle del ticket es decir lo ticke items
         * la coleccion getItems es una lazy collection. es decir, cuando se lee un ticket
         * enrealidad no lee toda la informacion de los tickets, en el momento que se solictan
         * los datos, en ese momento ejecuta el query para obtener la informacion.
         *
         */
        for (TicketItem ti : ticket.getItems()) {
            System.out.println(ti.getnOrder() + " - " + ti.getProduct().getName() + "  $" + ti.getPrice());
        }
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        //createTicket(session);
         readTicket(session);

        tx.commit();
        session.close();
    }
}
