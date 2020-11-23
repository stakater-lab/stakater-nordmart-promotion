package com.stakater.nordman.promotion;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.*;

import io.quarkus.test.junit.*;

@QuarkusTest
public class PromotionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/promotions/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}