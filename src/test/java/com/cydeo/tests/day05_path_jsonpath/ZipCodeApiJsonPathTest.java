package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeApiJsonPathTest {
    /**
     Given accept type is json
     and country path param value is "us"
     and postal code path param value is 22102
     When I send get request to http://api.zippopotam.us/{country}/{postal-code}
     Then status code is 200
     Then "post code" is "22102"
     And  "country" is "United States"
     And "place name" is "Mc Lean"
     And  "state" is "Virginia"
     */
    @BeforeAll
    public static void setUp() {
        System.out.println("Setting up base Url ... ");
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @DisplayName("GET us/zipCode - jsonPath")
    @Test
    public void zipCodeJsonPathTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country","us")
                .and().pathParam("postal-code", "22102")
                .when().get("/{country}/{postal-code}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());

        //assign response json payload/body to Jsonpath
        JsonPath jsonPath = response.jsonPath();
        verifyZipCode(jsonPath, "22102");
        //navigate the json and print/assert country value
        System.out.println("country name = " + jsonPath.getString("country"));
        assertEquals("United States" , jsonPath.getString("country"));

        //navigate the json and print/assert post code value
        System.out.println("post code = " + jsonPath.getString("'post code'"));
        String zipCode = jsonPath.getString("'post code'");
        assertEquals("22102", zipCode);

        //verify place name
        System.out.println("place name = " + jsonPath.getString("places[0].'place name'"));
        assertEquals("Mc Lean" , jsonPath.getString("places[0].'place name'"));

        //verify state is "Virginia"
        String state = jsonPath.getString("places[0].state");
        System.out.println("state = " + state);
        assertEquals("Virginia", state);



    }

    public void verifyZipCode(JsonPath jsonPath, String expZipCode) {
        assertEquals(expZipCode, jsonPath.getString("'post code'"));
    }

}