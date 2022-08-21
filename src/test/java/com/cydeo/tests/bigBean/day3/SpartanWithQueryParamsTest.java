package com.cydeo.tests.bigBean.day3;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithQueryParamsTest {
    /*
        Given Accept type is Json
        And query parameter values are:
        gender|Male
        nameContains|ol
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    String url = "http://54.167.139.116:8000/api/spartans/search";
    @DisplayName("GET /api/spartans/search with query params")
    @Test
    public void SpartanWithQueryParams() {
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender", "Male")
                .and().queryParam("nameContains", "ol")
                .when().get(url);
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        assertTrue(response.asString().contains("Male"));
        assertTrue(response.asString().contains("ol"));
    }
}
