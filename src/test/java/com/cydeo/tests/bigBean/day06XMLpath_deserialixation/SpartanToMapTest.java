package com.cydeo.tests.bigBean.day06XMLpath_deserialixation;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanToMapTest extends SpartanTestBase {
    /**
     * Given accept type is application/json
     * And Path param id = 10
     * When i send GET request to /api/spartans
     * Then status code is 200
     * And content type is json
     * And spartan data matching:
     * id > 10
     * name>Lorenza
     * gender >Female
     * phone >3312820936
     */
    @DisplayName("SpartanToMapTest ")
    @Test
    public void SpartanToMapTest() {
//        Given: accept type is application/json
        Response response = given().accept(ContentType.JSON)
//        And Path param id = 10
                .and().pathParam("id", 10)
//        When i send GET request to /api/spartans
                .when().get("/spartans/{id}");
        response.prettyPrint();
//        Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//        And content type is json
        assertEquals("application/json", response.contentType());
//        And spartan data matching:
        JsonPath jsonPath = response.jsonPath();

        Map<String, Object>spartanMap = response.body().as(Map.class);
        System.out.println(spartanMap);
        System.out.println("spartanMap.keySet() = " + spartanMap.keySet());
//        id > 10
        assertEquals(10, spartanMap.get("id"));
//        name>Lorenza
        assertEquals("Lorenza", spartanMap.get("name"));

//        gender >Female
        assertEquals("Female", spartanMap.get("gender"));

//        phone >3312820936
        assertEquals(3312820936L, spartanMap.get("phone"));

    }
}
