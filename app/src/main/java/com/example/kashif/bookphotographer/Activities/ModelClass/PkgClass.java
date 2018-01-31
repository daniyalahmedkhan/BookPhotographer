package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/4/2018.
 */

public class PkgClass {


 private    String Package_ID;
 private    String Category_ID;
 private    String Photographer_ID;
 private    String Package_Name;
 private    String Package_Price;
 private    String Services_Days;
 private    String Package_Description;
 private    String type;

    public PkgClass() {}

    public PkgClass(String Category_ID , String Photographer_ID , String id, String name, String price, String days, String description, String type) {

        this.Category_ID  = Category_ID;
        this.Photographer_ID = Photographer_ID;
        this.Package_ID = id;
        this.Package_Name = name;
        this.Package_Price = price;
        this.Services_Days = days;
        this.Package_Description = description;
        this.type = type;
    }

    public String getID() {
        return Package_ID;
    }

    public void setID(String ID) {
        this.Package_ID = ID;
    }

    public String getPackage_Name() {
        return Package_Name;
    }

    public void setPackage_Name(String package_Name) {
        Package_Name = package_Name;
    }

    public String getPackage_Price() {
        return Package_Price;
    }

    public void setPackage_Price(String package_Price) {
        Package_Price = package_Price;
    }

    public String getServices_Days() {
        return Services_Days;
    }

    public void setServices_Days(String services_Days) {
        Services_Days = services_Days;
    }

    public String getPackage_Description() {
        return Package_Description;
    }

    public void setPackage_Description(String package_Description) {
        Package_Description = package_Description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
