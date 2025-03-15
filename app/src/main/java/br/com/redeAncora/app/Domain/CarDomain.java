package br.com.redeAncora.app.Domain;

import java.io.Serializable;

public class CarDomain implements Serializable {
    private String title;
    private String description;
    private String picUrl;
    private String TotalCapacity;
    private String EngineOutput;
    private String HighestSpeed;
    private double price;
    private double rating;


    public CarDomain() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTotalCapacity() {
        return TotalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        TotalCapacity = totalCapacity;
    }

    public String getEngineOutput() {
        return EngineOutput;
    }

    public void setEngineOutput(String engineOutput) {
        EngineOutput = engineOutput;
    }

    public String getHighestSpeed() {
        return HighestSpeed;
    }

    public void setHighestSpeed(String highestSpeed) {
        HighestSpeed = highestSpeed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
