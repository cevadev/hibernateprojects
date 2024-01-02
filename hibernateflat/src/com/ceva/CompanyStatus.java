package com.ceva;

/**
 * Clase POJO
 * Archivo de configuracion de mapeo: CompanyStatus.hbm.xml
 */
public class CompanyStatus {
    private int id_companyStatus;
    private String code;
    private String description;

    public int getId_companyStatus() {
        return id_companyStatus;
    }

    public void setId_companyStatus(int id_companyStatus) {
        this.id_companyStatus = id_companyStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
