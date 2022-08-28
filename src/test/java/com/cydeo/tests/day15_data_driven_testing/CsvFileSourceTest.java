package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class CsvFileSourceTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvFileSource(resources ="/ZipCodes.csv", numLinesToSkip = 1)

    public void zipCodeTest(String state, String city, int zip_count) {
        Map<String, String> list = new HashMap<>();
        list.put("state", state);
        list.put("city", city);

given().accept(ContentType.JSON)
        .and().pathParams(list)
        .when().get("/us/{state}/{city}")
        .then().assertThat().statusCode(HttpStatus.SC_OK)
        .and().contentType(ContentType.JSON)
        .and().body("places", hasSize(zip_count))
        .log().all();

    }
}
