package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/4/2018.
 */

public class PkgClass {


  private String package_ID;
  private String category_ID;
  private String package_Name;
  private    String package_Price;
  private   String services_Days;
  private String package_Description;
  private    String type;

    public PkgClass() {}

    public PkgClass(String package_ID ,  String category_ID , String package_Name , String package_Price , String services_Days, String package_Description, String type  ) {
        this.package_ID = package_ID;
        this.category_ID = category_ID;
        this.package_Name = package_Name;
        this.package_Price = package_Price;
        this.services_Days = services_Days;
        this.package_Description = package_Description;
        this.type = type;
    }


    public String getPackage_ID() {
        return package_ID;
    }

    public void setPackage_ID(String package_ID) {
        this.package_ID = package_ID;
    }

    public String getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(String category_ID) {
        this.category_ID = category_ID;
    }


    public String getPackage_Name() {
        return package_Name;
    }

    public void setPackage_Name(String package_Name) {
        this.package_Name = package_Name;
    }

    public String getPackage_Price() {
        return package_Price;
    }

    public void setPackage_Price(String package_Price) {
        this.package_Price = package_Price;
    }

    public String getServices_Days() {
        return services_Days;
    }

    public void setServices_Days(String services_Days) {
        this.services_Days = services_Days;
    }

    public String getPackage_Description() {
        return package_Description;
    }

    public void setPackage_Description(String package_Description) {
        this.package_Description = package_Description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
