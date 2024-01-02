package com.ceva;

import java.util.List;
import org.hibernate.Session;

/**
 * Obtenemos todos los registros de la tabla company
 */
public class Test2 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        //
        List<Company> list = session.createQuery("from com.ceva.Company").list();
        for (Company company : list) {
            System.out.printf("id:%d, symbol:%s, name:%s\n", company.getId_company(), company.getSymbol(), company.getName());
        }

        session.close();
    }
}
