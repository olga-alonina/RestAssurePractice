package com.cydeo.pojo;


import lombok.Data;

@Data
public class Rooms {
    //"id": 111,
//        "name": "mit",
//        "description": "mens et manus",
//        "capacity": 6,
//        "withTV": true,
//        "withWhiteBoard": true
    private Integer idRooms;
    private String nameRoom;
    private String description;
    private Integer capacity;
    private Boolean withTV;
    private Boolean withWhiteBoard;

}
