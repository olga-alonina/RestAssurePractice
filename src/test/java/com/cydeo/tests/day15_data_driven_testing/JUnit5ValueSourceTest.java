package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class JUnit5ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 44, 44, 3331, 5546, 443, 998, 22})

    public void numberTest(int num) {
        System.out.println("num = " + num);
        assertThat(num, is(greaterThan(0)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Olga", "Helena", "Astra", "Alex", "Anna"})

    public void nameTest(String str) {
        System.out.println("Hi" + str);
        assertThat(str, not(blankOrNullString()));
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");

    }

    @ParameterizedTest
    @ValueSource(ints = {22102, 22031, 22034, 11209, 15090, 15237, 12345, 20879, 21224, 33433})
    public void zipCodeTest(int zipCode) {
given(). accept(ContentType.JSON)
        .and().pathParam("postal-code", zipCode)
        .when().get("/us/{postal-code}")
        .then().assertThat().statusCode(HttpStatus.SC_OK)
        .log().all();
    }
}

