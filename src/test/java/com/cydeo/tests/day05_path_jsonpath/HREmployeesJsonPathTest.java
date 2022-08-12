package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HRApiTestBase {

    /**
     Given accept type is Json
     And query param limit is 200
     when I send GET request to /employees
     Then I can use jsonPath to query and read values from json body
     */

    @DisplayName("GET /employees?limit=200 => jsonPath filters")
    @Test
    public void jsonPathEmployeesTest() {
Response response = given().accept(ContentType.JSON)
        .and().queryParam("limit", 200)
        .when().get("/employees");
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("100", jsonPath.getString("items[0].employee_id"));
        assertEquals("Steven", jsonPath.getString("items[0].first_name"));
        assertEquals("SKING", jsonPath.getString("items[0].email"));

        //Working with JsonPath lists:
        //get all the emails into a list and print out
      List<String> emails = jsonPath.getList("items.email");
        System.out.println("emails = " + emails);
        //assert TGATES is among emails:
        assertTrue(emails.contains("TGATES"));

        //get all employee first names who work for department_id 90
        List<String> namesAtDept90 = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println("namesAtDept90 = " + namesAtDept90);
        System.out.println("namesAtDept90.size() = " + namesAtDept90.size());

    }

}