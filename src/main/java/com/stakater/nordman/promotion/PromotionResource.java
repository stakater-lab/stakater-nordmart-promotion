package com.stakater.nordman.promotion;

import java.net.*;
import java.util.*;

import javax.inject.*;
import javax.transaction.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.eclipse.microprofile.config.inject.*;

@Path("/promotions")
public class PromotionResource {
    @Inject
    @ConfigProperty(defaultValue = "http://localhost:8080")
    String baseUrl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotions() {
        List<Promotion> allPromotions = Promotion.listAll();
        return Response.ok(allPromotions).build();
    }
    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActive() {
        return Response.ok(Promotion.listActive()).build();
    }
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello, you!";
    }

    @GET
    @Path("{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotion(@PathParam("itemId") String itemId) {
        return Response.ok(Promotion.listByItemIds(itemId)).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPromotion(Promotion p) {
        try {
            p.persist();
            return Response.created(new URI(baseUrl + "/promotions/" + p.id)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}