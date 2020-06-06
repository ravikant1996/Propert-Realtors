package com.example.propertyrealtors.model;

public class CommercialModel {
    String price, security, token_amount;
    String bathroom, bedroom, balcony, totalfloor, floorNo, furnished, RoadWidth, lock_in_periodString;
    String propertySubType, city, project, CarpetArea, SuperArea,  plotArea, plot_length, plot_bredth;
    String  open_Sides, construction_done, boundary_wall, gated_colony, cafateria, washroom, personal_washroom,
            cornerShop, main_road_facing, property_status, ageOfconstruction,  availablefrom;

    public CommercialModel(String property_status, String  ageOfconstruction, String  availablefrom, String price, String security,
                           String token_amount, String bathroom, String bedroom, String balcony,
                           String totalfloor, String floorNo, String furnished, String roadWidth, String lock_in_periodString,
                           String propertySubType, String city, String project, String carpetArea, String superArea, String plotArea,
                           String plot_length, String plot_bredth, String open_Sides, String construction_done, String boundary_wall,
                           String gated_colony, String cafateria, String washroom, String personal_washroom, String cornerShop,
                           String main_road_facing) {
        this.property_status = property_status;
        this.ageOfconstruction = ageOfconstruction;
        this.availablefrom = availablefrom;
        this.price = price;
        this.security = security;
        this.token_amount = token_amount;
        this.bathroom = bathroom;
        this.bedroom = bedroom;
        this.balcony = balcony;
        this.totalfloor = totalfloor;
        this.floorNo = floorNo;
        this.furnished = furnished;
        RoadWidth = roadWidth;
        this.lock_in_periodString = lock_in_periodString;
        this.propertySubType = propertySubType;
        this.city = city;
        this.project = project;
        CarpetArea = carpetArea;
        SuperArea = superArea;
        this.plotArea = plotArea;
        this.plot_length = plot_length;
        this.plot_bredth = plot_bredth;
        this.open_Sides = open_Sides;
        this.construction_done = construction_done;
        this.boundary_wall = boundary_wall;
        this.gated_colony = gated_colony;
        this.cafateria = cafateria;
        this.washroom = washroom;
        this.personal_washroom = personal_washroom;
        this.cornerShop = cornerShop;
        this.main_road_facing = main_road_facing;
    }

    public CommercialModel() {
    }

    public String getAgeOfconstruction() {
        return ageOfconstruction;
    }

    public String getAvailablefrom() {
        return availablefrom;
    }

    public String getProperty_status() {
        return property_status;
    }

    public String getPrice() {
        return price;
    }

    public String getSecurity() {
        return security;
    }

    public String getToken_amount() {
        return token_amount;
    }

    public String getBathroom() {
        return bathroom;
    }

    public String getBedroom() {
        return bedroom;
    }

    public String getBalcony() {
        return balcony;
    }

    public String getTotalfloor() {
        return totalfloor;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public String getFurnished() {
        return furnished;
    }

    public String getRoadWidth() {
        return RoadWidth;
    }

    public String getLock_in_periodString() {
        return lock_in_periodString;
    }

    public String getPropertySubType() {
        return propertySubType;
    }

    public String getCity() {
        return city;
    }

    public String getProject() {
        return project;
    }

    public String getCarpetArea() {
        return CarpetArea;
    }

    public String getSuperArea() {
        return SuperArea;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public String getPlot_length() {
        return plot_length;
    }

    public String getPlot_bredth() {
        return plot_bredth;
    }

    public String getOpen_Sides() {
        return open_Sides;
    }

    public String getConstruction_done() {
        return construction_done;
    }

    public String getBoundary_wall() {
        return boundary_wall;
    }

    public String getGated_colony() {
        return gated_colony;
    }

    public String getCafateria() {
        return cafateria;
    }

    public String getWashroom() {
        return washroom;
    }

    public String getPersonal_washroom() {
        return personal_washroom;
    }

    public String getCornerShop() {
        return cornerShop;
    }

    public String getMain_road_facing() {
        return main_road_facing;
    }
}
