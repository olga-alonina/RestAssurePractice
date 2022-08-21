package com.cydeo.tests.bigBean.Homework2_Jsonpath_Patth;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Q1 extends HRApiTestBase {
    @DisplayName("GET /countries/US")
    @Test
    public void Q1(){
        //   - Given accept type is Json
        Response response = given().accept(ContentType.JSON)
            //- Path param value - US
                .and().pathParam("value", "US")
            //- When users sends request to /countries
                .when().get("/countries/{value}");
            //- Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
            //   - And Content - Type is Json
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        //- And country_id is US
        assertEquals("US", jsonPath.get("country_id"));
        //- And Country_name is United States of America
        assertEquals("United States of America", jsonPath.get("country_name"));
        //- And Region_id is 2
        assertEquals("2", jsonPath.getString("region_id"));
    }
}