package com.example.propertyrealtors.model;

public class ResidentialModel {
    String id, refId, facing, society, location, flooring, overLooking, landmark, water,
            coverdArea, pricePSS, carparking, electricityAvailability;
    int imageCount;
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool,covered_areaParameter;

    public ResidentialModel() {
    }

    public ResidentialModel(String id, String refId, String facing, String society, String location, String flooring, String overLooking, String landmark, String water, String coverdArea, String pricePSS, String carparking, String electricityAvailability, int imageCount, String gym, String clubHouse, String park, String parking,
                            String lift, String powerBackup, String gasPipeline, String swimPool, String covered_areaParameter) {
        this.id = id;
        this.refId = refId;
        this.facing = facing;
        this.society = society;
        this.location = location;
        this.flooring = flooring;
        this.overLooking = overLooking;
        this.landmark = landmark;
        this.water = water;
        this.coverdArea = coverdArea;
        this.pricePSS = pricePSS;
        this.carparking = carparking;
        this.electricityAvailability = electricityAvailability;
        this.imageCount = imageCount;
        this.gym = gym;
        this.clubHouse = clubHouse;
        this.park = park;
        this.parking = parking;
        this.lift = lift;
        this.powerBackup = powerBackup;
        this.gasPipeline = gasPipeline;
        this.swimPool = swimPool;
        this.covered_areaParameter = covered_areaParameter;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getClubHouse() {
        return clubHouse;
    }

    public void setClubHouse(String clubHouse) {
        this.clubHouse = clubHouse;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getLift() {
        return lift;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }

    public String getPowerBackup() {
        return powerBackup;
    }

    public void setPowerBackup(String powerBackup) {
        this.powerBackup = powerBackup;
    }

    public String getGasPipeline() {
        return gasPipeline;
    }

    public void setGasPipeline(String gasPipeline) {
        this.gasPipeline = gasPipeline;
    }

    public String getSwimPool() {
        return swimPool;
    }

    public void setSwimPool(String swimPool) {
        this.swimPool = swimPool;
    }

    public String getCovered_areaParameter() {
        return covered_areaParameter;
    }

    public void setCovered_areaParameter(String covered_areaParameter) {
        this.covered_areaParameter = covered_areaParameter;
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
