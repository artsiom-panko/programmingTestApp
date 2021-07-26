package com.panko.testapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("rate")
    private String rate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rate_float")
    private float rateFloat;

    private float maxRate;
    private float minRate;

    public String getCode() {
        return code;
    }

    public String getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }
}
