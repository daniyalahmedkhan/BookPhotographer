package com.example.kashif.bookphotographer.Activities.ModelClass;


public class UserModel  {


    //// User Data ///
   private String user_Name , user_ID , email , pass , type , date;

    //// Photographer Data ////

  private   String Photographer_ID ,First_Name, Last_Name, Gender, Country, City , Address ,  Profile_Img , Contact_No;

    /// Location Data ///

 private    String  Location_ID  , Location_Desc, City_ID, Location_Status;



    //// City Data  ///


  private   String City_Des , Country_ID;

    /// Country Data ///

  private   String Country_Desc;


  /// Default Constructor
    public UserModel() {}



    /// Customer constructor for saving and fethcing data from firebase..
    public UserModel(String user_Name , String id, String email, String pass, String type , String date) {
        this.user_Name = user_Name;
        this.user_ID = id;
        this.email = email;
        this.pass = pass;
        this.type = type;
        this.date = date;

    }




    /// Photographer constructor for saving and fethcing data from firebase..
    public UserModel(String id, String email, String pass, String type, String firstname, String lastname, String gender , String imageUrl , String Location_ID , String Contact_No , String Date , String city_Des) {
        this.Photographer_ID = id;
        this.email = email;
        this.pass = pass;
        this.type = type;
        this.First_Name = firstname;
        this.Last_Name = lastname;
        this.Gender = gender;
        this.Profile_Img  = imageUrl;
        this.Location_ID = Location_ID;
        this.Contact_No = Contact_No;
        this.date  = Date;
        this.City_Des = city_Des;
    }


    /// Photographer location constructor for saving and fethcing data from firebase..
    public UserModel(String photographer_ID  , String location_ID, String location_Desc, String city_ID, String location_Status) {
        this.Photographer_ID = photographer_ID;
        this.Location_ID = location_ID;
        this.Location_Desc = location_Desc;
        this.City_ID = city_ID;
        this.Location_Status = location_Status;
    }


    /// Photographer city constructor for saving and fethcing data from firebase..
    public UserModel(String photographer_ID , String city_ID, String city_Des, String country_ID) {
        this.Photographer_ID = photographer_ID;
        this.City_ID = city_ID;
        this.City_Des = city_Des;
        this.Country_ID = country_ID;
    }


    /// Photographer Country constructor for saving and fethcing data from firebase..

    public UserModel(String Photographer_ID, String country_ID, String country_Desc) {
        this.Photographer_ID = Photographer_ID;
        this.Country_ID = country_ID;
        this.Country_Desc = country_Desc;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotographer_ID() {
        return Photographer_ID;
    }

    public void setPhotographer_ID(String photographer_ID) {
        Photographer_ID = photographer_ID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProfile_Img() {
        return Profile_Img;
    }

    public void setProfile_Img(String profile_Img) {
        Profile_Img = profile_Img;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public String getLocation_ID() {
        return Location_ID;
    }

    public void setLocation_ID(String location_ID) {
        Location_ID = location_ID;
    }

    public String getLocation_Desc() {
        return Location_Desc;
    }

    public void setLocation_Desc(String location_Desc) {
        Location_Desc = location_Desc;
    }

    public String getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(String city_ID) {
        City_ID = city_ID;
    }

    public String getLocation_Status() {
        return Location_Status;
    }

    public void setLocation_Status(String location_Status) {
        Location_Status = location_Status;
    }

    public String getCity_Des() {
        return City_Des;
    }

    public void setCity_Des(String city_Des) {
        City_Des = city_Des;
    }

    public String getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(String country_ID) {
        Country_ID = country_ID;
    }

    public String getCountry_Desc() {
        return Country_Desc;
    }

    public void setCountry_Desc(String country_Desc) {
        Country_Desc = country_Desc;
    }
}
