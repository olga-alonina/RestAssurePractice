package com.cydeo.tests.Homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Q2 {
    /*Q2:
- Given accept type is Json
- Path param "id" value is 1
- When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
- Then status code is 200

-And json body contains "repellat provident"
- And header Content - Type is Json
- And header "X-Powered-By" value is "Express"
- And header "X-Ratelimit-Limit" value is 1000
- And header "Age" value is more than 100

- And header "NEL" value contains "success_fraction"*/
    String url = "https://jsonplaceholder.typicode.com/posts/{id}";

    @DisplayName("practice Q2")
    @Test
    public void q2() {
        //Given: accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
      //Path param "id" value is 1
                .and().pathParams("id",1)  
                //- When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
                .when().get(url);
        //Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        //-And json body contains "repellat provident"
        assertTrue(response.body().asString().contains("repellat provident"));
        //And header Content - Type is Json
        assertEquals("application/json; charset=utf-8",
                response.header(ContentType.JSON.toString()));
//        - And header "X-Powered-By" value is "Express"
        assertEquals( "Express", response.header("X-Powered-By") );
//                - And header "X-Ratelimit-Limit" value is 1000
        assertEquals("1000", response.header("X-Ratelimit-Limit"));
//                - And header "Age" value is more than 100
        assertEquals("100", response.header("Age"));
    }
}
