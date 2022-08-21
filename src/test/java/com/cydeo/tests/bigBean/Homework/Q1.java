package com.cydeo.tests.bigBean.Homework;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class Q1 {
    /*- Given accept type is Json
- When user sends request to https://jsonplaceholder.typicode.com/posts
-Then print response body
- And status code is 200
- And Content - Type is Json*/
    String url = "https://jsonplaceholder.typicode.com/posts";

    @DisplayName("practice Q1")
    @Test
    public void q1() {
        //Given: accept type is Json
        Response response = given().log().all().accept(ContentType.JSON)
                //   - When user sends request to https://jsonplaceholder.typicode.com/posts
                .when().get(url);
        //Then print response body
        response.prettyPrint();
        //And status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        //And Content - Type is Json
        assertEquals("application/json; charset=utf-8",response.contentType());

    }
}
