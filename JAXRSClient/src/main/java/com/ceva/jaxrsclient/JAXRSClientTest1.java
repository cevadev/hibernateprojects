package com.ceva.jaxrsclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
public class JAXRSClientTest1 {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product/14");

        /**
         * El request indicamos que solicitamos informacion en Json
         * .get() indicamos el que sera una solicitud GET. String.class indicamos que
         * el Json queremos obtenerlo como un string
         */
        String res = target.request(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(res);

        client.close();
    }
}
