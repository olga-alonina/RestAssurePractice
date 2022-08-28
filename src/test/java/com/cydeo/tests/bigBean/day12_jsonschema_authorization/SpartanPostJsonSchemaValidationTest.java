package com.cydeo.tests.bigBean.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanRestUtils;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


import com.cydeo.utils.SpartanTestBase;



/**
 given accept is json and content type is json
 and body is:
 {
 "gender": "Male",
 "name": "TestPost1"
 "phone": 1234567425
 }
 When I send POST request to /spartans
 Then status code is 201
 And body should match SpartanPostSchema.json schema
 */

public class SpartanPostJsonSchemaValidationTest extends SpartanTestBase {
    @DisplayName("POST /spartan -> json schema validation")
    @Test
    public void postSpartanSchemaTest() {
        Map<String, Object> newSpartan = new HashMap<>();
        newSpartan.put("gender", "Male");
        newSpartan.put("name","TestPost1");
        newSpartan.put("phone",1234567425L);

        int newSpartanID = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans")
                .then().assertThat().statusCode(201)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/SpartanPostSchema.json")
                )).log().all()
                .and().extract().jsonPath().getInt("data.id"); //will return id integer value
        //.and().extract().response(); //will return Response object
        //.and().extract().jsonPath(); //will return JsonPath object

        System.out.println("newSpartanID = " + newSpartanID);
        SpartanRestUtils.deleteSpartanById(newSpartanID);

    }

}