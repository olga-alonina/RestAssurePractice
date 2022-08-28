package com.cydeo.tests.bigBean.day12_jsonschema_authorization;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.cydeo.utils.SpartanTestBase;

public class SpartanGetJsonSchemaValidationTest extends SpartanTestBase {
    /**
     given accept type is json
     and path param id is 15
     when I send GET request to /spartans/{id}
     Then status code is 200
     And json payload/body matches SingleSpartanSchema.json
     */
    @DisplayName("GET /spartans/{id} and json schema validation")
    @Test
    public void singleSpartanSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/SingleSpartanSchema.json")))
                .and().log().all();
    }

    /**
     given accept type is json
     when I send GET request to /spartans
     Then status code is 200
     And json payload/body matches AllSpartansSchema.json
     */

    @DisplayName("GET /spartans and json schema validation")
    @Test
    public void allSpartansJsonSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/AllSpartansSchema.json")
                )).log().all();
    }

    /**
     given accept type is json
     And query params: nameContains "e" and gender "Female"
     when I send GET request to /spartans/search
     Then status code is 200
     And json payload/body matches SearchSpartansSchema.json
     */

    @DisplayName("GET /spartans/search and json schema validation")
    @Test
    public void searchSpartanSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then().assertThat().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/SearchSpartansSchema.json")
                )).log().all();
    }
}








