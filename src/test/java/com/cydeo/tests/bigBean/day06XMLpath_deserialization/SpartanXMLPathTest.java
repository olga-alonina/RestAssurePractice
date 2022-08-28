package com.cydeo.tests.bigBean.day06XMLpath_deserialization;

import com.cydeo.utils.SpartanTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanXMLPathTest extends SpartanTestBase {

    @DisplayName(" SpartanXMLPathTest ")
    @Test
    public void SpartanXMLPathTest() {
        /**
         Given accept type is application/xml
         When i send GET request to /api/spartans
         Then status code is 200
         And content type is XML
         And spartan at index 0 is matching:
         id > 107
         name>Ezio Auditore
         gender >Male
         phone >7224120202

         */
        //   Given accept type is application/xml
        Response response = given().accept(ContentType.XML)
     //   When i send GET request to /api/spartans
                .when().get("/spartans");
//   Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        //And content type is XML
        assertEquals("application/xml", response.contentType());

    XmlPath xmlPath = response.xmlPath();
        //And spartan at index 0 is matching:
        //id > 1
        System.out.println(xmlPath.getInt("List.item[0].id"));
        System.out.println(xmlPath.getString("List.item[0].name"));
        System.out.println(xmlPath.getString("List.item[0].gender"));
        System.out.println(xmlPath.getLong("List.item[0].phone"));

        List<Integer> all_Id= xmlPath.getList("List.item.id");
        System.out.println("all_Id.size() = " + all_Id.size());
        List<String> allNames = xmlPath.getList("List.item.name");
        System.out.println("allNames.size() = " + allNames.size());
        
//        id = 103
        assertEquals(103,xmlPath.getInt("List.item[0].id"));
//        name= sdfdsf
        assertEquals("sdfdsf" ,xmlPath.getString("List.item[0].name"));
//        gender = Male
        assertEquals("Male" ,xmlPath.getString("List.item[0].gender"));
//        phone = 456723455678
        assertEquals(456723455678L ,xmlPath.getLong("List.item[0].phone"));

    }
}
