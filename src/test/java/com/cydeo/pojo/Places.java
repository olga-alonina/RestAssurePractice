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
    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateabbreviation() {
        return stateabbreviation;
    }

    public void setStateabbreviation(String stateabbreviation) {
        this.stateabbreviation = stateabbreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

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
