package com.cydeo.tests.bigBean.day2;

import io.restassured.http.ContentType;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {
    /**
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in JSON format
     */
    String url = "http://54.167.139.116:8000/api/spartans";

    @DisplayName("spartanHeaderTest")
    @Test

    public void spartanHeaderTest() {
        when().get(url)
                .then().statusCode(200)
                // .and().contentType("application/json");
                .and().contentType(ContentType.JSON);
    }

    /**
     * given accept header application/xml
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in xml format
     */
    @Test
    @DisplayName("Get api/spartans header ")
    public void ApiSpartansHeaders() {
        given().accept("application/xml")
                .when().get(url)
                .then().statusCode(200)
                .and().contentType(ContentType.XML);

    }

    /**
     * given accept header application/json
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I read headers
     */
    @Test
    @DisplayName("Read api/spartans header ")
    public void readSpartansHeaders() {
       Response response =  given().accept(ContentType.JSON)
                .when().get(url);
        System.out.println("response.getHeader(Date) = " + response.getHeader("Date"));
        System.out.println("response.getHeader(Content-Type) = " + response.getHeader("Content-Type"));
        System.out.println("response.getHeader(Connection) = " + response.getHeader("Connection"));
// read all headers
        Headers headers = response.getHeaders();
        System.out.println("headers = " + headers);
        //ensure header keep-alive is present
        assertNotNull(response.getHeader("Keep-Alive"));

    }
}