package com.cydeo.tests.day4_path_jsonpath;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HRApiPathMethodTest extends HRApiTestBase {
    @Test
    public void ReadCountriesUsingPathTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("countries");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        System.out.println("first country id = " + response.path("items[0].country_id"));
        System.out.println("first country name = " + response.path("items[0].country_name"));

        assertEquals("AR", response.path("items[0].country_id"));
        assertEquals("Argentina", response.path("items[0].country_name"));

        //all names
        ArrayList<String> allNames = response.path("items.country_name");


    }

}
