package com.example.propertyrealtors.model;

public class PropertyModel {

    String keyId, propertyFor, propertyType, UID, propertySubType, city, project, bathroom, bedroom, balcony, totalfloor,
            floorNo, furnished, carpetArea, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, price, token_amount, property_status, ageOfconstruction, availablefrom, security, maintenance
            ,carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;
    String dateofposting, timeofposting;
    int imageCount;

    public PropertyModel() {
    }

    public PropertyModel(String keyId, String propertyFor, String propertyType, String UID, String propertySubType, String city, String project,
                         String bathroom, String bedroom, String balcony, String totalfloor, String floorNo, String furnished,
                         String carpetArea, String superArea, String roadWidth, String open_Sides, String construction_done,
                         String boundary_wall, String gated_colony, String plotArea, String plot_bredth, String plot_length,
                         String cafateria, String washroom, String cornerShop, String main_road_facing, String personal_washroom,
                         String lock_in_periodString, String price, String token_amount, String property_status, String ageOfconstruction,
                         String availablefrom, String security, String maintenance,
                         String carpetAreaParameter, String superAreaParameter, String plotAreaParameter, String maintenance_parameter,
                         String roadWidthParameter, int imageCount, String dateofposting, String timeofposting) {
        this.keyId = keyId;
        this.propertyFor = propertyFor;
        this.propertyType = propertyType;
        this.UID = UID;
        this.propertySubType = propertySubType;
        this.city = city;
        this.project = project;
        this.bathroom = bathroom;
        this.bedroom = bedroom;
        this.balcony = balcony;
        this.totalfloor = totalfloor;
        this.floorNo = floorNo;
        this.furnished = furnished;
        this.carpetArea = carpetArea;
        this.superArea = superArea;
        this.roadWidth = roadWidth;
        this.open_Sides = open_Sides;
        this.construction_done = construction_done;
        this.boundary_wall = boundary_wall;
        this.gated_colony = gated_colony;
        this.plotArea = plotArea;
        this.plot_bredth = plot_bredth;
        this.plot_length = plot_length;
        this.cafateria = cafateria;
        this.washroom = washroom;
        this.cornerShop = cornerShop;
        this.main_road_facing = main_road_facing;
        this.personal_washroom = personal_washroom;
        this.lock_in_periodString = lock_in_periodString;
        this.price = price;
        this.token_amount = token_amount;
        this.property_status = property_status;
        this.ageOfconstruction = ageOfconstruction;
        this.availablefrom = availablefrom;
        this.security = security;
        this.maintenance = maintenance;
        this.carpetAreaParameter = carpetAreaParameter;
        this.superAreaParameter = superAreaParameter;
        this.plotAreaParameter = plotAreaParameter;
        this.maintenance_parameter = maintenance_parameter;
        this.roadWidthParameter = roadWidthParameter;
        this.imageCount = imageCount;
        this.dateofposting = dateofposting;
        this.timeofposting = timeofposting;
    }

    public String getTimeofposting() {
        return timeofposting;
    }

    public void setTimeofposting(String timeofposting) {
        this.timeofposting = timeofposting;
    }

    public String getDateofposting() {
        return dateofposting;
    }

    public void setDateofposting(String dateofposting) {
        this.dateofposting = dateofposting;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getCarpetAreaParameter() {
        return carpetAreaParameter;
    }

    public void setCarpetAreaParameter(String carpetAreaParameter) {
        this.carpetAreaParameter = carpetAreaParameter;
    }

    public String getSuperAreaParameter() {
        return superAreaParameter;
    }

    public void setSuperAreaParameter(String superAreaParameter) {
        this.superAreaParameter = superAreaParameter;
    }

    public String getPlotAreaParameter() {
        return plotAreaParameter;
    }

    public void setPlotAreaParameter(String plotAreaParameter) {
        this.plotAreaParameter = plotAreaParameter;
    }

    public String getMaintenance_parameter() {
        return maintenance_parameter;
    }

    public void setMaintenance_parameter(String maintenance_parameter) {
        this.maintenance_parameter = maintenance_parameter;
    }

    public String getRoadWidthParameter() {
        return roadWidthParameter;
    }

    public void setRoadWidthParameter(String roadWidthParameter) {
        this.roadWidthParameter = roadWidthParameter;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getPropertyFor() {
        return propertyFor;
    }

    public void setPropertyFor(String propertyFor) {
        this.propertyFor = propertyFor;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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

    public String getCarpetArea() {
        return carpetArea;
    }

    public String getSuperArea() {
        return superArea;
    }

    public String getRoadWidth() {
        return roadWidth;
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

    public String getPlotArea() {
        return plotArea;
    }

    public String getPlot_bredth() {
        return plot_bredth;
    }

    public String getPlot_length() {
        return plot_length;
    }

    public String getCafateria() {
        return cafateria;
    }

    public String getWashroom() {
        return washroom;
    }

    public String getCornerShop() {
        return cornerShop;
    }

    public String getMain_road_facing() {
        return main_road_facing;
    }

    public String getPersonal_washroom() {
        return personal_washroom;
    }

    public String getLock_in_periodString() {
        return lock_in_periodString;
    }

    public String getPrice() {
        return price;
    }

    public String getToken_amount() {
        return token_amount;
    }

    public String getProperty_status() {
        return property_status;
    }

    public String getAgeOfconstruction() {
        return ageOfconstruction;
    }

    public String getAvailablefrom() {
        return availablefrom;
    }

    public String getSecurity() {
        return security;
    }

    public void setPropertySubType(String propertySubType) {
        this.propertySubType = propertySubType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public void setTotalfloor(String totalfloor) {
        this.totalfloor = totalfloor;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    public void setSuperArea(String superArea) {
        this.superArea = superArea;
    }

    public void setRoadWidth(String roadWidth) {
        this.roadWidth = roadWidth;
    }

    public void setOpen_Sides(String open_Sides) {
        this.open_Sides = open_Sides;
    }

    public void setConstruction_done(String construction_done) {
        this.construction_done = construction_done;
    }

    public void setBoundary_wall(String boundary_wall) {
        this.boundary_wall = boundary_wall;
    }

    public void setGated_colony(String gated_colony) {
        this.gated_colony = gated_colony;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public void setPlot_bredth(String plot_bredth) {
        this.plot_bredth = plot_bredth;
    }

    public void setPlot_length(String plot_length) {
        this.plot_length = plot_length;
    }

    public void setCafateria(String cafateria) {
        this.cafateria = cafateria;
    }

    public void setWashroom(String washroom) {
        this.washroom = washroom;
    }

    public void setCornerShop(String cornerShop) {
        this.cornerShop = cornerShop;
    }

    public void setMain_road_facing(String main_road_facing) {
        this.main_road_facing = main_road_facing;
    }

    public void setPersonal_washroom(String personal_washroom) {
        this.personal_washroom = personal_washroom;
    }

    public void setLock_in_periodString(String lock_in_periodString) {
        this.lock_in_periodString = lock_in_periodString;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setToken_amount(String token_amount) {
        this.token_amount = token_amount;
    }

    public void setProperty_status(String property_status) {
        this.property_status = property_status;
    }

    public void setAgeOfconstruction(String ageOfconstruction) {
        this.ageOfconstruction = ageOfconstruction;
    }

    public void setAvailablefrom(String availablefrom) {
        this.availablefrom = availablefrom;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}

