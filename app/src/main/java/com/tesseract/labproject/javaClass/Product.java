package com.tesseract.labproject.javaClass;

/**
 * Created by BlackFlag on 8/31/2016.
 */
public class Product {
    String title;
    String des;
    String status;
    String username;
    String lat;
    String lon;

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Product(String title, String des, String status, String username, String lat, String lon) {
        this.title = title;
        this.des = des;
        this.status = status;
        this.username = username;
        this.lat = lat;
        this.lon = lon;
    }
}
