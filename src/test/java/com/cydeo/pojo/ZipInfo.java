package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ZipInfo {
    @JsonProperty("post code")
    private String postcode;
}
