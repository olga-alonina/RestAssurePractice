package com.cydeo.tests.bigBean.day11_put_patch_request;

import com.cydeo.utils.SpartanRestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cydeo.utils.SpartanTestBase;

public class SpartanPATCHRequestTest extends SpartanTestBase {
    /**
     PATCH /api/spartans/{id}
     Partially Update A Spartan

     Given accept type is json
     And content type is json
     And path param id is 15
     And json body is
     {
     "phone": 1234567425
     }
     When i send PATCH request to /spartans/{id}
     Then status code 204
     */
    @DisplayName("PATCH /api/spartans/{id}")
    @Test
    public void spartanPatchTest() {
        Map<String, Long> requestMap = new HashMap<>();
        requestMap.put("phone" , 9999999999L);

        int spartanId = 15;

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .and().body(requestMap)
                .when().patch("/spartans/{id}")
                .then().statusCode(204)
                .and().body(emptyString());

        Map<String, Object> spartanMap = SpartanRestUtils.getSpartan(15);
        System.out.println("spartanMap = " + spartanMap);

        //assertThat(spartanMap.get("phone"), equalTo(4444444444L));
        //compare spartanMap from GET request matches phone number in PATCH request.
        assertThat(spartanMap.get("phone"), equalTo(requestMap.get("phone")));

    }

}