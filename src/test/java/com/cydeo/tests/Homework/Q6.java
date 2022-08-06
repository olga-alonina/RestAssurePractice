package com.cydeo.tests.Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Q6 {
    /*- Given accept type is Json
- Query Param "postId" value is 333
- When user sends request to  https://jsonplaceholder.typicode.com/comments

- And header Content - Type is Json

- And header "Content-Length" value is 2
- And json body contains "[]"*/
    String url = "https://jsonplaceholder.typicode.com/comments";

    @DisplayName("practice Q6")
    @Test
    public void q6() {

//    - Given accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
//- Query Param "postId" value is 333
                .and().queryParam("postId", 333)
//            - When user sends request to  https://jsonplaceholder.typicode.com/comments
                .when().get(url);
//            - And header Content - Type is Json
        assertEquals("application/json; charset=utf-8", response.contentType());
//- And header "Content-Length" value is 2
        assertEquals("2", response.header("Content-Length"));
//            - And json body contains "[]"
        assertTrue(response.body().asString().contains("[]"));
    }
}
