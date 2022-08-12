package com.cydeo.tests.day07_deserialization;


import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPojoTest extends SpartanTestBase {

    @DisplayName(" SpartanSearchPojoTest ")
    @Test
    public void SpartanSearchPojoTest() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");
//        * Given accept type is json
        Response response = given().accept(ContentType.JSON)
//                * And query param nameContains value is "e"
//                * And query param gender value is "Female"
                .and().queryParams(queryMap)
//                * When I send get request to /spartans/search
                .when().get("/spartans/search");

        response.prettyPrint();
//                * Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//                * And content type is Json
        assertEquals("application/json", response.contentType());
//         * And json response data is as expected
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);
// total elements
        System.out.println("Total Element = " + spartanSearch.getTotalElement());
        System.out.println("all Spartans= " + spartanSearch.getContent());
        System.out.println("first spartan = " + spartanSearch.getContent().get(0));

        Spartan secondSpartan = spartanSearch.getContent().get(1);
        System.out.println("secondSpartan name= " + secondSpartan.getName());
        System.out.println("secondSpartan Phone = " + secondSpartan.getPhone());

        List<Spartan> spartanData = spartanSearch.getContent();
        System.out.println("spartan #2 = " + spartanData.get(1));

        List<String> names = new ArrayList<>();
        for (Spartan sp : spartanData) {
            names.add(sp.getName());
        }
        System.out.println("names = " + names);

        //usinf functional programming stream
        List<String> allNames = spartanData.stream().map(sp -> sp.getName()).collect(Collectors.toList());
        System.out.println("allNames = " + allNames);
    }
}