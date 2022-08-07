package com.cydeo.utils;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {
    @BeforeAll

    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.api.url");
    }
}

