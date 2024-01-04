package com.ceva;

import javax.json.Json;
import javax.json.JsonObject;
public class CreateJSON {
    public static void main(String[] args) {
        // creamos un nuevo objeto Json
        JsonObject json = Json.createObjectBuilder()
                .add("name", "Fulano")
                .add("age", 21)
                .add("boolean", true)
                .build();
        String result = json.toString();

        System.out.println(result);
    }
}
