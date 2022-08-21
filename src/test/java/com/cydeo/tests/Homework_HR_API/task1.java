package com.cydeo.tests.Homework_HR_API;

import com.cydeo.utils.DBUtils;
import com.cydeo.utils.HRApiTestBase;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class task1 extends HRApiTestBase {
    //    1) POST a region then do validations.
//    Please use Map or POJO class, or JsonPath
    @DisplayName("POST /region> then validate in DB")
    @Test
    public void validateInDBTest() {
        Map<String, Object> postRequestMap = new HashMap();
        // * With json:
// * {
// *     "region_id":121,
        postRequestMap.put("region_id", "121");
// *     "region_name":"New Region"
        postRequestMap.put("region_name", "New Region");

        // * given :accept is json
        Response response = given().accept(ContentType.JSON)
                // * and content type is json
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                // * When I send post request to "/regions/"
                .and().post("/regions/");


// * }
        response.prettyPrint();
// * Then status code is 201
        assertThat(response.statusCode(), equalTo(201));
// * content type is json
        assertThat(response.contentType(), equalTo("application/json"));
// * region_id is 100
        assertThat(postRequestMap.get("region_id"), is("121"));
// * region_name is Test Region
        assertThat(postRequestMap.get("region_name"), is("New Region"));

        //disconnect from Database
        DBUtils.destroy();
    }

    //*******************************************
    @DisplayName("POST then Get /regions/121 -> then validate in DB")
    @Test
    public void validateInDBTest2() {
        Map<String, Object> postRequestMap = new HashMap();
        // * With json:
// * {
// *     "region_id":121,
        postRequestMap.put("region_id", "121");
// *     "region_name":"New Region"
        postRequestMap.put("region_name", "New Region");
// * given: accept is json
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
// * When I send get request to "/regions/121"
                .when().get("/regions/121");
// * Then status code is 200
        assertThat(response.statusCode(), equalTo(200));
// * content type is json
        assertThat(response.contentType(), equalTo("application/json"));
// * region_id is 121
        assertThat(postRequestMap.get("region_id"), is("121"));
// * region_name is new Region
        assertThat(postRequestMap.get("region_name"), is("New Region"));

    }
}
