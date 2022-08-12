package com.cydeo.pojo;/*
{
        "id": 10,
        "name": "Lorenza",
        "gender": "Female",
        "phone": 3312820936
        }*/

import lombok.Data;

@Data
public class Spartan {
    private  int id;
    private String name;
    private String gender;
    private long phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
