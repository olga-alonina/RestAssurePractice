package com.cydeo.pojo;
/*
        "id": 10,
        "name": "Lorenza",
        "gender": "Female",
        "phone": 3312820936
        */

import lombok.Data;

@Data
public class Spartan {
    private int id;
    private String name;
    private String gender;
    private long phone;
    //    private String email;

}