package com.cydeo.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class SpartanSecureTestBase {
    //visible to child classes. also visible to same package
    protected static RequestSpecification requestSpec;

    @BeforeAll //@BeforeClass in junit 4
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.secure.api.url");

        requestSpec = given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin");

    }
}