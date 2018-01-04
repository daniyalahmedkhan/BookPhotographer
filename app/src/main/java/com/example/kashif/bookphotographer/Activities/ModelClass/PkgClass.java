package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/4/2018.
 */

public class PkgClass {

    String id;
    String name;
    String price;
    String days;
    String description;
    String type;

    public PkgClass() {
    }

    public PkgClass(String id, String name, String price, String days, String description, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.days = days;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
