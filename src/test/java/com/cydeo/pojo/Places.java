package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//         "place name": "Fairfax",
//         "longitude": "-77.2649",
//         "state": "Virginia",
//         "state abbreviation": "VA",
//         "latitude": "38.8604"

@Data
public class Places {

    @JsonProperty("place name")
    private String placename;
    private String longitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateabbreviation;
    private String latitude;
    @JsonProperty("post code")
    private String postCode;


}
