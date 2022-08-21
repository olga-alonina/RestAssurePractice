package com.cydeo.tests.bigBean.Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Q5 {
    /*- Given accept type is Json
- Query Param "postId" value is 1
- When user sends request to  https://jsonplaceholder.typicode.com/comments
- Then status code is 200
- And header Content - Type is Json
- And header "Connection" value is "keep-alive"
- And json body contains "Lew@alysha.tv"*/
    String url = "https://jsonplaceholder.typicode.com/comments";

    @DisplayName("practice Q5")
    @Test
    public void q5() {
//    - Given accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
//- Query Param "postId" value is 1
                .and().queryParam("postId", 1)
//            - When user sends request to  https://jsonplaceholder.typicode.com/comments
                .when().get(url);
//            - Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//            - And header Content - Type is Json
        assertEquals("application/json; charset=utf-8", response.contentType());
//- And header "Connection" value is "keep-alive"
        assertEquals("keep-alive", response.header("Connection"));
//            - And json body contains "Lew@alysha.tv"
        assertTrue(response.body().asString().contains("Lew@alysha.tv"));
    }
}