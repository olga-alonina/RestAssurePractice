package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    /*When user sends GET Request to :
https://sandbox.api.service.nhs.uk/hello-world/hello/world

Then status code should be 200
And "Hello World!" message should be in the response*/
    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest() {
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);
        // print respond body
        response.prettyPrint();
        // print status code
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

      Assertions.assertEquals(200, response.statusCode());

      // declare status code variable and assign the response  then assert
        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);

        Assertions.assertTrue(response.asString().contains("Hello World"));
    }

}