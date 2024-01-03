package com.ceva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * Operaciones dentro de una transaccion con validaciones
 */
public class TransactionDemo2 {
    private static Query findProd;

    private static Product findOrCreateProduct(Session session, String name, double price) throws HibernateException {
        Product p = (Product) findProd.setParameter("name", name.toUpperCase()).uniqueResult();
        if (p == null) {
            p = new Product();
            p.setName(name);
            p.setPrice(price);
            session.save(p);

            // validaciones
            if (p.getPrice() <= 0d) {
                 throw new HibernateException("Producto invalido");

                // manejamos la excepcion
                //session.getTransaction().rollback(); // ante cualquier cosa anormal, hacemos rollback
                //return null;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        // colocamos toda la operacion en un bloque try
        try {
            Object products[] = {
                    "Resistencia",  1d,
                    "Capacitor",    1.5d,
                    "Inductor",     -1.5d
            };

            Query findCust = session.createQuery("from com.ceva.Customer where upper(firstName)=:firstName");
            findProd = session.createQuery("from com.ceva.Product where upper(name)=:name");
            Query findCat = session.createQuery("from Category where upper(name)=:name");

            String strElectronica = "Electronica";
            Category electronica = (Category) findCat.setParameter("name", strElectronica.toUpperCase())
                    .uniqueResult();
            if (electronica == null) {
                electronica = new Category();
                electronica.setName(strElectronica);

                for (int n=0; n<products.length; n+=2) {
                    String s = (String) products[n];
                    double d = (double) products[n+1];
                    Product p = findOrCreateProduct(session, s, d);
                    // validamos, ya que findOrCreateProduct() puede retornar null
                    if (p != null)
                        // si no es nulo, agregamos elp producto
                        electronica.addProduct(p);
                }
                session.save(electronica);
            }

            Customer customer = (Customer) findCust.setParameter("firstName", "John".toUpperCase()).uniqueResult();
            if (customer != null) {
                Ticket ticket = new Ticket();
                ticket.setTicketDate(new java.util.Date());
                ticket.setCustomer(customer);
                int nOrder = 1;
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
        } catch (HibernateException e) {
            System.out.println("Se produjo un error al realizar la transaccion.");
            tx.rollback(); // revertimos todas las operaciones
        }
        session.close();
    }
}
