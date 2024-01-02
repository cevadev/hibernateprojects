package com.ceva;

/**
 * Clase POJO
 * Archivo de configuracion de mapeo: Company.hbm.xml
 * Tambien se debe declarar la clase POJO en el archivo de configuracion de hibernate
 */
public class Company {
    private int id_company;
    private String symbol;
    private String name;
    private CompanyStatus companyStatus;

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public CompanyStatus getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(CompanyStatus companyStatus) {
        this.companyStatus = companyStatus;
    }
}
