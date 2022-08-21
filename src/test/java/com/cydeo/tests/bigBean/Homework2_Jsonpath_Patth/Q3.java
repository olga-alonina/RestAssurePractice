package com.cydeo.tests.bigBean.Homework2_Jsonpath_Patth;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Q3 extends HRApiTestBase {

    @DisplayName("GET q= region_id 3")
    @Test
    public void Q3() {
//    - Given accept type is Json
        Response response = given().accept(ContentType.JSON)
//    Query param value q= region_id 3
                .and().queryParam("q", "{\"region_id\":3}")
//            - When users sends request to /countries
                .when().get("/countries");
//- Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
//            - And all regions_id is 3
        List<Integer> allRegion = jsonPath.getList("items.region_id");
        for (Integer region : allRegion) {
            assertEquals(3, region);
        }
//            - And count is 6
        assertEquals(6, allRegion.size());
//            - And hasMore is false
        assertFalse(jsonPath.getBoolean("hasMore"));
//            - And Country_name are;
//    Australia,China,India,Japan,Malaysia,Singapore
        List<String> allNames = jsonPath.getList("items.country_name");
        System.out.println("allNames = " + allNames);
        assertTrue(allNames.contains("Australia")
                && allNames.contains("China")
                && allNames.contains("India") && allNames.contains("Japan")
                && allNames.contains("Malaysia")
                && allNames.contains("Singapore"));
    }
}