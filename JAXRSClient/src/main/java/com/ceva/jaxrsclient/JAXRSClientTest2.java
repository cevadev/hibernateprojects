package com.ceva.jaxrsclient;

import java.util.List;
import java.util.Map;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
public class JAXRSClientTest2 {
    private static void testGet(Client client) {
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product/7");
        ProductData productData = target.request(MediaType.APPLICATION_JSON)
                .get(ProductData.class);
        System.out.println("id_product:  " + productData.id_product);
        System.out.println("name:        " + productData.name);
        System.out.println("price:       " + productData.price);
        System.out.println("description: " + productData.description);
        System.out.println();
    }

    private static void testList(Client client) {
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product");

        List<ProductData> list = target.request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ProductData>>() {});

        for (ProductData productData : list) {
            System.out.println("id_product:  " + productData.id_product);
            System.out.println("name:        " + productData.name);
            System.out.println("price:       " + productData.price);
            System.out.println("description: " + productData.description);
            System.out.println();
        }
    }

    private static void testInsert(Client client) {
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product");

        ProductData newProduct = new ProductData();
        newProduct.name = "Calibre";
        newProduct.description = "Calibre 3M";
        newProduct.price = 13.33;

        // Hacemos put al registro newProduct q sera de tipo json
        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(newProduct, MediaType.APPLICATION_JSON));

        // indicamos que la info del response lo queremos como un mapa
        Map map = response.readEntity(new GenericType<Map<String,String>>() {});
        System.out.println("result: " + map.get("result"));
        System.out.println("id_product: " + map.get("id_product"));
    }

    private static void testUpdate(Client client) {
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product").path("108");

        // info a actualizar
        ProductData newProduct = new ProductData();
        newProduct.id_product = 58;
        newProduct.name = "Calibre";
        newProduct.description = "Calibre Metalico 3M";
        newProduct.price = 50.99;

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newProduct, MediaType.APPLICATION_JSON));
        System.out.println(response.readEntity(String.class));
    }

    private static void testDelete(Client client) {
        WebTarget target = client.target("http://localhost:8080/SimpleStore-RestJaxRs/rest/product").path("58");

        Response response = target.request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println(response.readEntity(String.class));
    }

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        testGet(client);
//        testList(client);
//        testInsert(client);
//        testUpdate(client);
//        testDelete(client);

        client.close();
    }
}
