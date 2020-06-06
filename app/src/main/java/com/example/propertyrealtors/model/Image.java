package com.example.propertyrealtors.model;

import java.util.ArrayList;

public class Image {
    String imageAddress;

    public Image() {
    }

    public Image(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
