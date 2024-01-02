package com.ceva;

import org.hibernate.Session;

/**
 * Consultando la tabla company con parametros
 */
public class Test3 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        /**
         * session.createQuery() nos retorna un objeto, hacemos el cast al tipo Company
         */
        Company company = (Company) session.createQuery("from com.ceva.Company where symbol=:symbol")
                .setParameter("symbol", "MSFT")
                .uniqueResult(); // no restorna un objeto, primer resultado del query

        if (company != null) {
            System.out.printf("id_company: %d, symbol: %s, name: %s\n", company.getId_company(), company.getSymbol(), company.getName());
        }

        session.close();
    }
}
