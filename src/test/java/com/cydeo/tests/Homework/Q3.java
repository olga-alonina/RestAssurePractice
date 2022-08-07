package com.cydeo.tests.Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class Q3 {
    /*Q3:

- Given accept type is Json
- Path param "id" value is 12345
- When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
- Then status code is 404

- And json body contains "{}"*/
    String url = "https://jsonplaceholder.typicode.com/posts/{id}";

    @DisplayName("practice Q3")
    @Test
    public void q3() {
//        - Given accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
//        - Path param "id" value is 12345
            .and().pathParams("id",12345)
//                - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
                .when().get(url);
//        - Then status code is 404
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
//                - And json body contains "{}"
        assertEquals("{}", response.body().asString());
    }
}
