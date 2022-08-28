package com.cydeo.tests.day15_data_driven_testing;


import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

import java.util.*;

public class CsvSourceDDTTest {
    @ParameterizedTest
    @CsvSource({"7,5,12", "3,99,102", "32,44,76", "38,41,79"})

    public void basicAddTest(int num1, int num2, int expSum) {
        System.out.println("num1, num2, expSum = " + num1 + " / " + num2 + " / " + expSum);
        int actSum = num1 + num2;
        System.out.println("actSum = " + actSum);
        assertThat(actSum, equalTo(expSum));

    }
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvSource({"North Chicago , IL", "Washington, DC", "New York City, NY", "Boston, MA"})

    public void cityAndStateZipCodeApiTest(String city, String state) {

        Map<String, String> list = new HashMap<>();
        list.put("state", state);
        list.put("city", city);

        given().accept(ContentType.JSON)
                .and().pathParams(list)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("'place name'", equalTo(city))
                .log().all();

    }
}
