package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ZipCodeApiTestBase {
    @BeforeAll

    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }
}

