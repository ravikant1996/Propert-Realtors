package com.example.propertyrealtors.model;

import com.google.gson.annotations.SerializedName;

public class City {
    String city;

    public City() {
    }

    public City(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
