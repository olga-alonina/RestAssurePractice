package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class PlacesUs {
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private String country;
    @JsonProperty("place name")
    private String placeName;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;


    @JsonProperty("post code")
    private String postCode;

    private List<Places> places;

}
