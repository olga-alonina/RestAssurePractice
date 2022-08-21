package com.cydeo.tests.bigBean.day08_hamcrest;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {
    /**
     * given accept type is json
     * and path id is 24
     * When i send get request to /spartans/{id}
     * then status code is 200
     * and content type is application/json
     * and id" is 24,
     * "name" is "Julio",
     * "gender" is "Male",
     * "phone" is 9393139934
     */
    @DisplayName("GET /spartans/{id} -> hamcrest assertions")
    @Test
    public void singleSpartanTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("/spartans/{id}")
                .then().statusCode(is(200))
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("id", is(24),
                        "name", equalTo("Julio"),
                        "gender", is("Male"),
                        "phone", is(9393139934L));

    }

    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And header date contains 2022
     * And json response data is as expected
     * totalElement is 34
     * has ids: 94, 98,91, 81
     * has names: Jocelin, Georgianne, Catie, Marylee,Elita
     * every gender is Female
     * every name contains e
     */
    @DisplayName("GET /spartans/search -> hamcrest assertion")
    @Test
    public void searchTest() {
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().header("Date", containsString(LocalDate.now().getYear() + ""))
                .and().header("Date", containsString("2022"))
                .and().body("totalElement", is(equalTo(34)),
                        "content.id", hasItems(94, 98, 91, 81),
                        "content.name", hasItems("Jocelin", "Georgianne", "Catie", "Marylee", "Elita"),
                        "content.gender", everyItem(is("Female")),
                        "content.name", everyItem(containsStringIgnoringCase("e")))
                .log().all();
    }

}