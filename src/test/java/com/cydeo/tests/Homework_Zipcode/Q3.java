package com.cydeo.tests.Homework_Zipcode;

import com.cydeo.pojo.*;
import com.cydeo.utils.ZipCodeApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Q3 extends ZipCodeApiTestBase {
    @DisplayName("GET request to /us endpoint")
    @Test
    public void Q3() {
        Map<String, String> pathMapQ1 = new HashMap<>();
        pathMapQ1.put("state", "VA");
        pathMapQ1.put("city", "Fairfax");
//    Given:Accept application/json
        Response response = given().accept(ContentType.JSON)
//    And path state is va
//    And path city is farifax
                .and().pathParams(pathMapQ1)
//    When I send a GET request to /us endpoint
                .when().get("/us/{state}/{city}");
//    Then status code must be 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//    And content type must be application/json
        assertEquals("application/json", response.contentType());
//    And payload should: contains following information
        PlacesUs listOfInfo = response.body().as(PlacesUs.class);
//    country abbreviation is US
        assertEquals("US", listOfInfo.getCountryAbbreviation());
//    country  United States
        assertEquals("United States", listOfInfo.getCountry());

        List<Places> list = listOfInfo.getPlaces();
//    place name  Fairfax
        assertEquals("Fairfax", list.get(0).getPlaceName());
//    each places must: contains fairfax as a value
        for (Places eachPlace : list) {
            assertTrue(eachPlace.getPlaceName().toLowerCase().contains("fairfax"));
            //    each post code must start with 22
            assertTrue(eachPlace.getPostCode().startsWith("22"));
        }
    }
}
