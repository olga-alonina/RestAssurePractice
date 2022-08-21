package com.cydeo.tests.day10_db_vs_api_put_delete;


import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import java.util.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanAPIAndDBValidationTest extends SpartanTestBase {

    /**
     * given accept is json and content type is json
     * and body is:
     * {
     * "gender": "Male",
     * "name": "PostVSDatabase"
     * "phone": 1234567425
     * }
     * When I send POST request to /spartans
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * When I send database query
     * SELECT name, gender, phone
     * FROM spartans
     * WHERE spartan_id = newIdFrom Post request;
     * Then name, gender , phone values must match with POST request details
     */

    @DisplayName("POST /api/spartan -> then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest() {
        Map<String, Object> postRequestMap = new HashMap();
        postRequestMap.put("gender", "Female");
        postRequestMap.put("name", "Clis");
        postRequestMap.put("phone", "7735678954");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .and().post("/spartans");


        response.prettyPrint();

//        assertThat(response.statusCode(), equalTo(201));
//        assertThat(response.contentType(), equalTo("application/json"));


        assertThat(response.jsonPath().getString("success"), equalTo("A Spartan is Born!"));
        int newSpartanId = response.path("data.id");
        System.out.println("newSpartanId = " + newSpartanId);
        String query = "select Name, GENDER, PHONE from SPARTANS where SPARTAN_ID =  " + newSpartanId;
        String dbURL = ConfigurationReader.getProperty("spartan.db.url");
        String dbUsername = ConfigurationReader.getProperty("spartan.db.username");
        String dbPassword = ConfigurationReader.getProperty("spartan.db.password");
        DBUtils.createConnection(dbURL, dbUsername, dbPassword);

        Map<String, Object> listSpartans = DBUtils.getRowMap(query);
        System.out.println("listSpartans = " + listSpartans);

        assertThat(listSpartans.get("NAME"), equalTo(postRequestMap.get("name")));
        assertThat(listSpartans.get("GENDER"), equalTo(postRequestMap.get("gender")));
        assertThat(listSpartans.get("PHONE"), equalTo(postRequestMap.get("phone")));


        //disconnect from Database
        DBUtils.destroy();


    }
}
