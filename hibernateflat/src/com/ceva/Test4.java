package com.ceva;

import org.hibernate.Session;

/**
 * Aplicamos la integridad referencia entre las tablas Company y CompanyStatus
 */
public class Test4 {
    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();

        // buscamos la company con el symbol MSFT
        Company company = (Company) session.createQuery("from com.ceva.Company where symbol=:symbol")
                .setParameter("symbol", "MSFT")
                .uniqueResult();

        if (company != null) {
            System.out.printf("id_company: %d, symbol: %s, name: %s, companyStatus: %s\n",
                    company.getId_company(),
                    company.getSymbol(),
                    company.getName(),
                    company.getCompanyStatus().getCode()
            );
        }
        session.close();
    }
}
