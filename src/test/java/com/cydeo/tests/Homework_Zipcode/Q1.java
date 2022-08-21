package com.cydeo.tests.Homework_Zipcode;


import com.cydeo.pojo.Places;
import com.cydeo.pojo.PlacesUs;
import com.cydeo.utils.ZipCodeApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Q1 extends ZipCodeApiTestBase {
    @DisplayName("GET request to /us endpoint")
    @Test
    public void Q1() {
        Map<String, String> pathMapQ1 = new HashMap<>();
        pathMapQ1.put("post code", "22031");
            //    Given :Accept application/json
        Response response = given().accept(ContentType.JSON)
                //    And path zipcode is 22031
                .and().pathParams(pathMapQ1)
                //    When I send a GET request to /us endpoint
                .when().get("us/{post code}");

        response.prettyPrint();
//    Then status code must be 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//    And content type must be application/json
        assertEquals("application/json", response.contentType());

//    And Server header is cloudflare
        assertEquals("cloudflare", response.header("Server"));
//    And Report-To header exists
        assertTrue(response.header("Report-To").length() > 0);

        PlacesUs listOfInfo = response.body().as(PlacesUs.class);

        //    And body should :contains following information
        //    post code is 22031
        assertEquals("22031", listOfInfo.getPostCode());
        //    country  is United States
        assertEquals("United States", listOfInfo.getCountry());
        //    country abbreviation is US
        assertEquals("US", listOfInfo.getCountryAbbreviation());

        //    place name is Fairfax
        List<Places> list = listOfInfo.getPlaces();
        assertEquals("Fairfax", list.get(0).getPlaceName());
        //    state is Virginia
        assertEquals("Virginia", list.get(0).getState());
        //    latitude is 38.8604
        assertEquals("38.8604", list.get(0).getLatitude());

    }
}
