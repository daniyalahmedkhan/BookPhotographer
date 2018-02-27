package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 2/25/2018.
 */

public class LocationClass {


    private String location_ID;
    private String location_Name;

    public LocationClass() {
    }

    public LocationClass(String location_ID, String location_Name) {
        this.location_ID = location_ID;
        this.location_Name = location_Name;
    }

    public String getLocation_ID() {
        return location_ID;
    }

    public void setLocation_ID(String location_ID) {
        this.location_ID = location_ID;
    }

    public String getLocation_Name() {
        return location_Name;
    }

    public void setLocation_Name(String location_Name) {
        this.location_Name = location_Name;
    }
}
