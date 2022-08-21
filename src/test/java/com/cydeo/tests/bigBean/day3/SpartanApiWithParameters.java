package com.cydeo.tests.bigBean.day3;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithParameters {
    /*   Given accept type is Json
         And Id parameter value is 5
         When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json
         And "Blythe" should be in response payload(body)
      */
    String url = "http://54.167.139.116:8000/api/spartans";

    @DisplayName("GET /api/spartans/{id}")
    @Test
    public void getSingleSpartan() {
        int id = 5;
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get(url + "/{id}");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("Content-Type = " + response.getHeader("Content-Type"));

        assertEquals("application/json",  response.contentType());
        assertTrue(response.asString().contains("Blythe"));

    }
}
