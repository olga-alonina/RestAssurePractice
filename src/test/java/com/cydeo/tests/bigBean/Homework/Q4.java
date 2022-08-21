package com.cydeo.tests.bigBean.Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Q4 {
    /*
- Given accept type is Json
- Path param "id" value is 2
- When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}/comments
- Then status code is 200

- And header Content - Type is Json
- And json body contains "Presley.Mueller@myrl.com",  "Dallas@ole.me" , "Mallory_Kunze@marie.org"*/
    String url = "https://jsonplaceholder.typicode.com/posts/{id}/comments";

    @DisplayName("practice Q4")
    @Test
    public void q4() {
//            - Given accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
//- Path param "id" value is 2
                .and().pathParams("id", 2)
//            - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}/comments
                .when().get(url);
//            - Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//            - And header Content - Type is Json
        assertEquals("application/json; charset=utf-8",
                response.contentType());
//- And json body contains "Presley.Mueller@myrl.com",  "Dallas@ole.me" , "Mallory_Kunze@marie.org"
        assertTrue(response.body().asString().contains("Presley.Mueller@myrl.com"));
        assertTrue(response.body().asString().contains("Dallas@ole.me"));
        assertTrue(response.body().asString().contains("Mallory_Kunze@marie.org"));

    }
}
