package com.cydeo.tests.day3;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SingleSpartanNotFound {
    /*Given accept type is Json
    And Id parameter value is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json
    And "Not Found" message should be in response payload
     */
    String url = "http://54.167.139.116:8000/api/spartans";
@DisplayName("GET /api/spartans/{id} with missing id")
@Test
public void getSingleSpartanNotFound() {
    Response response = given().accept(ContentType.JSON)
            .and().pathParams("id", 500)
            .when().get (url+"/{id}");

    System.out.println("response.statusCode() = " + response.statusCode());
    //1 var
    assertEquals(404, response.statusCode());
    //2 var
    assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    //1 var
    assertEquals("application/json", response.contentType());
    //2 var
    assertEquals(ContentType.JSON. toString(), response.contentType());

    assertTrue(response.asString().contains("Not Found"));
    System.out.println("response = " + response.asString());


}
}
