package com.ceva.jaxrsdemo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/test1")
public class Rest1 {

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html><body>Hello world</body></html>";
    }
}

