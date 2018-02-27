package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 2/20/2018.
 */

public class AddressClass {


  private String country_ID;
  private String country_Name;




    public AddressClass() {
    }

    public AddressClass(String country_Name, String country_ID) {
        this.country_ID = country_ID;
        this.country_Name = country_Name;
    }




    public String getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(String country_ID) {
        this.country_ID = country_ID;
    }

    public String getCountry_Name() {
        return country_Name;
    }

    public void setCountry_Name(String country_Name) {
        this.country_Name = country_Name;
    }
}
