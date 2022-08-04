package com.cydeo.tests.day01_intro;


import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReqResApiTest {
    String url = "https://reqres.in/api/users";

    /*  When User sends GET Request to
     * https://reqres.in/api/users
     *
     * Then Response status code should be 200
     * And Response body should contain "George"
     * And Header Content type should be json*/
    @DisplayName("Get all users")
    @Test
    public void usersGetTest() {
        //when user send get respond
        Response response = when().get(url);
        // then response status should be 200
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
        //dbb syntax
        response.then().statusCode(200);
        //Then Response status code should be 200
        response.then().assertThat().statusCode(200);
        //And Response body should contain "George"
        Assertions.assertTrue(response.asString().contains("George"));
        //And Header Content type should be json
        Assertions.assertTrue(response.contentType().contains("json"));
    }
    //    When User Sends get request to API Endpoint:
//            "https://reqres.in/api/users/5"
//    Then Response status code should be 200
//    And Response body should contain user info "Charles"

    @DisplayName("Get single user")
    @Test
    public void getSingleUserApiTest() {
        String url = "https://reqres.in/api/users/5";
        //when user send get respond
        Response response = when().get(url);
        // then response status should be 200
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
        //And Response body should contain user info "Charles"
        Assertions.assertTrue(response.asString().contains("Charles"));
    }
    @DisplayName("Negative test")
    @Test
    public void getSingleUserNegativeApiTest() {
        //When User Sends get request to API Endpoint: "https://reqres.in/api/users/50"
        Response response = when().get(url + "/50");

        //Then Response status code should be 404
        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(404, response.statusCode());

        //And Response body should contain user info "{}"
        assertTrue(response.asString().contains("{}"));
    }
}
