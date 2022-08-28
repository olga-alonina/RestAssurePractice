package com.cydeo.tests.day14_specifications;


import com.cydeo.utils.BookItAPITestBase;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookItSpecTest extends BookItAPITestBase {
    /**
     Given accept type is json
     And header Authorization is access_token of teacher
     When I send GET request to /api/teachers/me
     Then status code is 200
     And content type is json
     And teacher info is presented in payload
     */
    @Test
    public void teacherInfoTest() {
        Map<String,String> teacherMap =new HashedMap<>();
                given().spec(teacherReqSpec)
                .when().get("/api/teachers/me")
                .then().spec(responseSpec)
                .and().extract().body().as(Map.class);

        Assertions.assertEquals("1816", teacherMap.get("id") );
        Assertions.assertEquals("Barbabas", teacherMap.get("firstName") );
        Assertions.assertEquals("Lyst", teacherMap.get("lastName") );
        Assertions.assertEquals("teacher", teacherMap.get("role") );

        //by using hamcreset matchers
        given().spec(teacherReqSpec)
                .when().get("/api/teachers/me")
                .then().spec(responseSpec)
                .and().body("id",       is(1816),
                        "firstName",is("Barbabas"),
                        "lastName" , is("Lyst"),
                        "role",      is("teacher"));

    }

}