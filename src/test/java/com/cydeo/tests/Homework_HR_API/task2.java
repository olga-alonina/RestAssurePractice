package com.cydeo.tests.Homework_HR_API;

import com.cydeo.utils.DBUtils;
import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.With;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class task2 extends HRApiTestBase {
    //    2) PUT request then DELETE
    @DisplayName("PUT request then DELETE")
    @Test
    public void Put_DeleteTest() {
        // * With json body:
// *    {
// *      "region_id": 100,
// *      "region_name": "Wooden Region"
// *    }
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("region_id", "121");
        requestMap.put("region_name", "Wooden Region");
// * Given :accept type is Json
        Response response = given().accept(ContentType.JSON)
// * And content type is json
                .and().contentType(ContentType.JSON)
// * When I send PUT request to /regions/121
                .and().body(requestMap)
                .when().put("/regions/121");

// * Then status code is 200
        assertThat(response.statusCode(), is(200));
// * And content type is json
        assertThat(response.contentType(), is("application/json"));
// * region_id is 121
        assertThat(requestMap.get("region_id"), is("121"));
// * region_name is Wooden Region
        assertThat(requestMap.get("region_name"), is("Wooden Region"));


    }

    //**************************
    @DisplayName("PUT request then DELETE")
    @Test
    public void Put_DeleteTest2() {
        // * With json body:
        // *    {
        // *      "region_id": 100,
        // *      "region_name": "Wooden Region"
// *    }
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("region_id", "121");
        requestMap.put("region_name", "Wooden Region");
        // * Given :accept type is Json
        Response response = given().accept(ContentType.JSON)
                // *When i send DELETE request to /regions/121
                .and().body(requestMap)
                .when().delete("/regions/121");
            // * Then status code is 200
        assertThat(response.statusCode(), is(200));


    }
}
