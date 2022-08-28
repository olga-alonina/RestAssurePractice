package com.cydeo.tests.BookIT;

import com.cydeo.pojo.Campuses;
import com.cydeo.pojo.Clusters;
import com.cydeo.pojo.PlacesUs;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class US1Get_All_Campuses {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    @Test
    @DisplayName("US1 AC1")
    public void positiveScenario() {
        /*AC#1
Given I have a token with valid credentials and I am signed in
When I request the following "GET /api/campuses"
Then I should get status code "200"
Then I should get the following : [
    {
        "id": integer,
        "location": "string",
        "clusters": [
            {
                "id": integer,
                "name": "string",
                "rooms": [
                    {
                        "id": integer,
                        "name": "string",
                        "description": "string",
                        "capacity": integer,
                        "withTV": boolean,
                        "withWhiteBoard": boolean
                    },
                    ...
                ]
            },
            ...
        ]
    },
    ...
]*/
        Map<String, String> loginParams = new HashMap<>();
        loginParams.put("email", ConfigurationReader.getProperty("teacher_email"));
        loginParams.put("password", ConfigurationReader.getProperty("teacher_password"));

        Response response = given().accept(ContentType.JSON)
                .and().params(loginParams)
                .when().get(baseURI + "/sign");
        //collect token info as Map
        Map<String, Object> tokenMap = response.body().as(Map.class);
        String token = (String) tokenMap.get("accessToken");
        //send request to get campuses
        given().accept(ContentType.JSON)
                .and().header("Authorization", "Bearer " + token)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/AllCampuses.json")
                )).log().all();
    }

    @Test
    @DisplayName("US1 AC2")
    public void negativeScenario() {
        /*
AC#2 (negative)
Given I have invalid session token
When I request the following "GET /api/campuses"
Then I should get status code "401"*/
        Map<String, String> loginParams = new HashMap<>();
        loginParams.put("email", ConfigurationReader.getProperty("teacher_email"));
        loginParams.put("password", ConfigurationReader.getProperty("teacher_password"));
        //send get request to get accessToken
        Response response = given().accept(ContentType.JSON)
                .and().params(loginParams)
                .when().get(baseURI + "/sign");
        //collect token info as Map
        Map<String, Object> tokenMap = response.body().as(Map.class);
        String token = (String) tokenMap.get("accessToken");
        String incorrectToken = token + "something";
        //send request to get campuses
        Response response2 = given().accept(ContentType.JSON)
                .and().header("Authorization", "Bearer " + incorrectToken)
                .when().get("/api/campuses");
        assertEquals(422, response2.statusCode());

    }
}
