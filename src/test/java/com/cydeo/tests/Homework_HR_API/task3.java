package com.cydeo.tests.Homework_HR_API;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class task3 extends HRApiTestBase {
    //    3) POST a region then Database validations. Please use Map
    @DisplayName("POST a region then Database validations")
    @Test

    public void postNewSpartanValidateDBTest() {
        // * With json:
// * {
// * "region_id":200,
// * "region_name":"Alohamora Region"
// * }
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("region_id", 200);
        requestMap.put("region_name", "Alohamora Region");
// * given: accept is json
        Response response = given().accept(ContentType.JSON)
// * and content type is json
                .and().contentType(ContentType.JSON)
// * When I send post request to "/regions/"
                .and().body(requestMap)
                .and().post("/regions/");

        response.prettyPrint();
// * Then status code is 201
       response.then(). assertThat().statusCode(HttpStatus.SC_CREATED)
// * content type is json
               .and().assertThat().contentType(ContentType.JSON);
// * When I connect to HR database
        String dbURL = ConfigurationReader.getProperty("hr.db.url");
        String dbUsername = ConfigurationReader.getProperty("hr.db.username");
        String dbPassword = ConfigurationReader.getProperty("hr.db.password");
        DBUtils.createConnection(dbURL, dbUsername, dbPassword);
// and execute query "SELECT region_id, region_name FROM regions WHERE region_id = 200"
        String query = "select  region_id, region_name from regions where region_id = 200";
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap);
        assertThat(rowMap.get("REGION_NAME"),equalTo(requestMap.get("region_name")));
        // * Then region_name from database should match region_name from POST request
    }

    @DisplayName("PUT request then DELETE")
    @Test
    public void Put_DeleteTest2() {
        // * With json body:
        // *    {
        // *      "region_id": 200,
        // *      "region_name": "Alohamora Region"
// *    }
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("region_id", "200");
        requestMap.put("region_name", "Alohamora Region");
        // * Given :accept type is Json
        Response response = given().accept(ContentType.JSON)
                // *When i send DELETE request to /regions/200
                .and().body(requestMap)
                .when().delete("/regions/200");
        // * Then status code is 200
        assertThat(response.statusCode(), is(200));

    }
}

