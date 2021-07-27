package com.panko.testapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BpiCurrency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("rate")
    private String rate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rate_float")
    private float rateFloat;

    public String getCode() {
        return code;
    }

    public String getRate() { return rate; }

    public float getRateFloat() { return rateFloat; }

    public String getDescription() {
        return description;
    }
}
