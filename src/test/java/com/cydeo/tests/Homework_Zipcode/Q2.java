package com.cydeo.tests.Homework_Zipcode;


import com.cydeo.utils.ZipCodeApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q2 extends ZipCodeApiTestBase {
    @DisplayName("GET request to /us endpoint")
    @Test
    public void Q2() {
        Map<String, String> pathMapQ1 = new HashMap<>();
        pathMapQ1.put("us", "5000");
//    Given: Accept application/json
        Response response = given().accept(ContentType.JSON)
//    And path zipcode is 50000
                .and().pathParams(pathMapQ1)
//    When I send a GET request to /us endpoint
                .when().get("/us/{us}");
//    Then status code must be 404
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
//    And content type must be application/json
        assertEquals("application/json", response.contentType());

    }
}
