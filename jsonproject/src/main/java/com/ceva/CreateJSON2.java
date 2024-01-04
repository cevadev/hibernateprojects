package com.ceva;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

/**
 * Uso del objeto factory para crear instancias de un objeto json
 */
public class CreateJSON2 {
    public static void main(String[] args) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject json = factory.createObjectBuilder()
                .add("firstName", "Fulano")
                .add("lastName", "De tal")
                .add("age", 21)
                // objeto address
                .add("address", factory.createObjectBuilder()
                        .add("streetAddress", "Calle del Pez")
                        .add("city", "Guantia")
                        .add("state", "Capital")
                        .add("postalCode", "90999"))
                // phoneNumber es un array
                .add("phoneNumber", factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("type", "home")
                                .add("number", "8 88 88 88"))
                        .add(factory.createObjectBuilder()
                                .add("type", "fax")
                                .add("number", "9 99 99 99")))
                .build();

        String result = json.toString();

        System.out.println(result);
    }
}
