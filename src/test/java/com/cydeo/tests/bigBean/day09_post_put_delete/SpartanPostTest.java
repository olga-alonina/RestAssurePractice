package com.cydeo.tests.bigBean.day09_post_put_delete;


import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;

import java.util.*;

import static com.cydeo.utils.SpartanRestUtils.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostTest extends SpartanTestBase {

    /**
     * given accept: is json and content type is json
     * and body is:
     * {
     * "gender": "Male",
     * "name": "TestPost1"
     * "phone": 1234567425
     * }
     * When I send POST request to /spartans
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * Data name, gender , phone matches my request details
     */

    @DisplayName("POST new spartan using string json")
    @Test
    public void addNewSpartanAsJsonTest() {
        String jsonBody = "{\n" +
                "     \"gender\": \"Male\",\n" +
                "     \"name\": \"TestPost1\",\n" +
                "     \"phone\": 1234567425\n" +
                "     }";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody) //set request body
                .when().post("/spartans");

        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"), equalTo("TestPost1"));

        assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
        assertThat(jsonPath.getLong("data.phone"), equalTo(1234567425L));

        //extract the id of newly added spartan
        int spartanId = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanId);
        //delete the spartan after post
        deleteSpartanById(spartanId);
    }

    /**
     * {
     * "gender": "Male",
     * "name": "TestPost1"
     * "phone": 1234567425
     * }
     */
    @DisplayName("POST /spartans using map - SERIALIZATION")
    @Test
    public void addNewSpartanAsMapTest() {
        Map<String, Object> spartanPostMap = new HashMap<>();
        spartanPostMap.put("gender", "Male");
        spartanPostMap.put("name", "TestPost1");
        spartanPostMap.put("phone", 1234567425L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanPostMap)  //Map to Json - serialization
                .when().post("/spartans");

        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));

        assertThat(jsonPath.getString("data.name"), equalTo(spartanPostMap.get("name")));

        assertThat(jsonPath.getString("data.gender"), equalTo(spartanPostMap.get("gender")));
        assertThat(jsonPath.getLong("data.phone"), equalTo(spartanPostMap.get("phone")));

        //extract the id of newly added spartan
        int spartanId = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanId);
        //delete the spartan after post
        deleteSpartanById(spartanId);

    }

    @DisplayName("POST new spartan using string json")
    @Test
    public void addNewSpartanAsPOJOTest() {
        Spartan newSpartan = new Spartan();
        newSpartan.setName("Olga");
        newSpartan.setGender("Female");
        newSpartan.setPhone(3124051098L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");
        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));

        assertThat(jsonPath.getString("data.name"), equalTo(newSpartan.getName()));
        assertThat(jsonPath.getString("data.gender"), equalTo(newSpartan.getGender()));
        assertThat(jsonPath.getLong("data.phone"), equalTo(newSpartan.getPhone()));

        //extract the id of newly added spartan
        int spartanId = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanId);
        //delete the spartan after post
        deleteSpartanById(spartanId);

    }

}