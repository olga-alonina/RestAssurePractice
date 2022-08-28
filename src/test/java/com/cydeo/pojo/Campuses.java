package com.cydeo.pojo;

import java.util.*;
import lombok.Data;

@Data
public class Campuses {
//    "id": 1,
//            "location": "VA",
//            "clusters":

    private Integer id;
    private String location;
    private List<Clusters>inClusters;
}
