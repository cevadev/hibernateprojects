package com.ceva;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class QueryTicket2 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        // Llamamos al NamedQuery ticket_products
        Query query = session.getNamedQuery("ticket_products")
                .setParameter("id_ticket", 2);
        List<String> result = query.list();
        for (String s : result) {
            System.out.println(s);
        }

        session.close();
    }
}
