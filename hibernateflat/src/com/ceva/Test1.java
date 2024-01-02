package com.ceva;

import org.hibernate.Session;
public class Test1 {
    public static void main(String[] args) {
        // abrimos la sesion
        Session session = HibernateUtil.newSession();

        // Creamos una instancia de la clase Company pasando el id_company a consultar
        Company company = session.get(Company.class, 10079);
        System.out.println("id=" + company.getId_company() + ", symbol=" + company.getSymbol() +", name=" + company.getName());

        session.close();
    }
}
