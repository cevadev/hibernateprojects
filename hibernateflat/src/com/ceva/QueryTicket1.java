package com.ceva;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class QueryTicket1 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        // Creamos una Hibernate Query Language
        Query query = session.createQuery("select distinct i.product.name from Ticket t join t.items i where t.id_ticket=:id_ticket")
                .setParameter("id_ticket", 2);
        List<String> result = query.list();
        for (String s : result) {
            System.out.println(s);
        }

        session.close();
    }
}
