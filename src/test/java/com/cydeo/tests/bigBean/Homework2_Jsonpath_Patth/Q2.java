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

public class Q2 extends HRApiTestBase{

    @DisplayName("GET q={\"department_id\":80}")
    @Test
    public void Q2(){
        // - Given accept type is Json
        Response response = given().accept(ContentType.JSON)
        //- Query param value - q={"department_id":80}
                .and().queryParam("q", "{\"department_id\":80}")
        //- When users sends request to /employees
                .when().get("/employees");
        //- Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        // - And Content - Type is Json
        assertEquals("application/json", response.contentType());
        //- And all job_ids start with 'SA'
        List<String> listOfJobIds = jsonPath.getList("items.job_id");
        for (String jobIds : listOfJobIds) {
            assertTrue(jobIds.startsWith("SA"));
            System.out.println("jobIds = " + jobIds);
        }
//            - And all department_ids are 80
        List<Integer> listOfDepartmentId = jsonPath.getList("items.department_id");
        for (Integer departmentIds : listOfDepartmentId) {
            assertEquals(80, departmentIds);
            System.out.println("departmentIds = " + departmentIds);
        }
//            - Count is 25
        assertEquals(25,listOfDepartmentId.size());
    }
}
