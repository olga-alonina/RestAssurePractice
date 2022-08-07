package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HRApiTestBase {
    @BeforeAll

    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("hr.api.url");
    }
}

