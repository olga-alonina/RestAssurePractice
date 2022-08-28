package com.cydeo.tests.bigBean.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanSecureTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanBasicAuthTest extends SpartanSecureTestBase {
    /**
     given accept type is json
     and basic auth credentials are admin , admin
     When user sends GET request to /spartans
     Then status code is 200
     And content type is json
     And print response
     */
    @DisplayName("GET /spartans with basic auth")
    @Test
    public void getSpartansWithAuthTest() {
        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().log().all();
    }

    /**
     given accept type is json
     When user sends GET request to /spartans
     Then status code is 401
     And content type is json
     And "message" is "Unauthorized"
     And log response
     */

    @Test
    public void getAllSpartansUnAuthorizedTest() {
        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("message", equalTo("Unauthorized"))
                .log().all();
    }

}