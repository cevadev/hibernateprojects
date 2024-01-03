package com.ceva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Programa que crea una transaccion y dentro de ella realiza la sgtes operaciones:
 * 1. Buscan un cliente
 * 2. Agrega 3 productos
 * 3. Crea una nueva categoria para los productos
 * 4. Crea un ticket
 * 5. Agrega ticketitem
 */

public class TransactionDemo1 {
    // query que se va a llamar en varias ocasiones
    private static Query findProd;

    // metodo que busca un producto, si no lo encuentra lo crea.
    private static Product findOrCreateProduct(Session session, String name, double price) throws HibernateException {
        Product p = (Product) findProd.setParameter("name", name.toUpperCase()).uniqueResult();
        if (p == null) {
            p = new Product();
            p.setName(name);
            p.setPrice(price);
            session.save(p);
        }
        return p;
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        // agregamos 3 products
        Object products[] = {
                "Resistencia",  1d,
                "Capacitor",    1.5d,
                "Inductor",     1.5d
        };

        Query findCust = session.createQuery("from com.ceva.Customer where upper(firstName)=:firstName");
        findProd = session.createQuery("from com.ceva.Product where upper(name)=:name");
        Query findCat = session.createQuery("from com.ceva.Category where upper(name)=:name");

        // agregamos la categoria electronica
        String strElectronica = "Electronica";
        Category electronica = (Category) findCat.setParameter("name", strElectronica.toUpperCase())
                .uniqueResult();
        if (electronica == null) {
            // si no esta la categoria Electronica, entonces la creamos
            electronica = new Category();
            electronica.setName(strElectronica);

            for (int n=0; n<products.length; n+=2) {
                String s = (String) products[n];
                double d = (double) products[n+1];
                Product p = findOrCreateProduct(session, s, d);
                electronica.addProduct(p);
            }
            session.save(electronica);
        }

        // obtenemos al cliente John
        Customer customer = (Customer) findCust.setParameter("firstName", "John".toUpperCase()).uniqueResult();
        if (customer != null) {
            // creamos un ticket para el cliente John
            Ticket ticket = new Ticket();
            ticket.setTicketDate(new java.util.Date());
            ticket.setCustomer(customer);
            int nOrder = 1; // llevamos registro del orden de insercion de los productos
            // creamos los respectivos ticketitem
            for (Product p : electronica.getProducts()) {
                TicketItem ti = new TicketItem();
                ti.setnOrder(nOrder);
                ti.setProduct(p);
                ti.setPrice(p.getPrice());
                ticket.addTicketItem(ti);

                nOrder++;
            }
            session.save(ticket);
        }

        tx.commit();
        session.close();
    }
}
