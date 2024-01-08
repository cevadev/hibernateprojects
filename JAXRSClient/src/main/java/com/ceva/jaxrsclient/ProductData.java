package com.ceva.jaxrsclient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
public class ProductData {
    public int id_product = -1;
    public String name;
    @JsonProperty
    public double price;
    public String description;
    private Map<String,String> errors;

    public String getName() {
        return name != null ? name : "";
    }

    @JsonIgnore
    public String getPrimaryKey() {
        return id_product != -1 ? String.valueOf(id_product) : "";
    }

    @JsonIgnore
    public String getPrice() {
        return String.format("%.2f", price);
    }

    public String getDescription() {
        return description != null ? description : "";
    }

    public void setError(String name, String message) {
        if (errors == null)
            errors = new HashMap<>();
        errors.put(name, message);
    }

    public String getError(String name) {
        if (errors == null)
            return "";
        String message = errors.get(name);
        return message != null ? message : "";
    }
}
