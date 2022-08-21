package com.cydeo.tests.bigBean.day2;


import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static  io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiHelloTest {
    /**
     *
     When I send GET request to http://54.167.139.116:8000/api/hello
     Then status code should be 200
     And response body should be equal to "Hello from Sparta"
     And content type is "text/plain;charset=UTF-8"
     */
    String url = "http://54.167.139.116:8000/api/hello";
    @DisplayName("helloApiTest")
    @Test

    public void helloApiTest(){
        Response response = when().get(url);
        //        Then status code should be 200
        assertEquals(200, response.statusCode());
        //        And response body should be equal to "Hello from Sparta"
        assertEquals("Hello from Sparta",response.body().asString());
        //        And content type is "text/plain;charset=UTF-8"
        assertTrue(response.contentType().contains("text/plain;charset=UTF-8"));
    }
    /**
     *
     When I send GET request to http://54.167.139.116:8000/api/hello
     Then status code should be 200
     And content type is "text/plain;charset=UTF-8"
     */

    @DisplayName("GET api/hello - bdd")
    @Test
    public void helloApiBddTest() {
        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=UTF-8");

    }
}
