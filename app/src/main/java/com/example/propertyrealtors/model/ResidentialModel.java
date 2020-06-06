package com.example.propertyrealtors.model;

public class ResidentialModel {
    String id, refId, facing, society, location, flooring, overLooking, landmark, water,
            coverdArea, pricePSS, carparking, electricityAvailability;
    int imageCount;

    public ResidentialModel() {
    }

    public ResidentialModel(String id, String facing, String coverdArea, String society, String location,
                            String flooring, String overLooking, String landmark, String water, String refId,
                            int imageCount, String pricePSS, String carparking, String electricityAvailability) {
        this.id = id;
        this.facing = facing;
        this.coverdArea = coverdArea;
        this.society = society;
        this.location = location;
        this.flooring = flooring;
        this.overLooking = overLooking;
        this.landmark = landmark;
        this.water = water;
        this.refId = refId;
        this.imageCount = imageCount;
        this.pricePSS = pricePSS;
        this.carparking = carparking;
        this.electricityAvailability = electricityAvailability;
    }

    public String getElectricityAvailability() {
        return electricityAvailability;
    }

    public void setElectricityAvailability(String electricityAvailability) {
        this.electricityAvailability = electricityAvailability;
    }

    public void setPricePSS(String pricePSS) {
        this.pricePSS = pricePSS;
    }

    public String getCarparking() {
        return carparking;
    }

    public void setCarparking(String carparking) {
        this.carparking = carparking;
    }

    public String getPricePSS() {
        return pricePSS;
    }

    public String getCoverdArea() {
        return coverdArea;
    }

    public void setCoverdArea(String coverdArea) {
        this.coverdArea = coverdArea;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFlooring() {
        return flooring;
    }

    public void setFlooring(String flooring) {
        this.flooring = flooring;
    }

    public String getOverLooking() {
        return overLooking;
    }

    public void setOverLooking(String overLooking) {
        this.overLooking = overLooking;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }
}
