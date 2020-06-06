package com.example.propertyrealtors.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCityModel {
    @SerializedName("result")
    @Expose
    private List<City> result = null;

    public List<City> getResult() {
        return result;
    }

    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResult(List<City> result) {
        this.result = result;
    }

}
