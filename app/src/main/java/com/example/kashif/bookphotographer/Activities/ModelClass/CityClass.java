package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 2/25/2018.
 */

public class CityClass {

    private String city_ID;
        private String city_Name;


    public CityClass() {
    }

    public CityClass(String city_ID, String city_Name) {
        this.city_ID = city_ID;
        this.city_Name = city_Name;
    }

    public String getCity_ID() {
        return city_ID;
    }

    public void setCity_ID(String city_ID) {
        this.city_ID = city_ID;
    }

    public String getCity_Name() {
        return city_Name;
    }

    public void setCity_Name(String city_Name) {
        this.city_Name = city_Name;
    }
}
