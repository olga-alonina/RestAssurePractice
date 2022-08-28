package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.SpartanSecureTestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanSpecTest extends SpartanSecureTestBase {

    RequestSpecification requestSpec = given().accept(ContentType.JSON)
            .and().auth().basic("admin","admin");

    @Test
    public void allSpartansTest() {

//       given().accept(ContentType.JSON)
//                .and().auth().basic("admin","admin")

        given().spec(requestSpec)
                .when().get("/spartans")
                .then().log().all();
    }

    @Test
    public void singleSpartanTest() {

        given().spec(requestSpec)
                .and().pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().log().all();
    }

}
