package com.example.kashif.bookphotographer.Activities.ModelClass;


public class UserModel  {


    //// User Data ///
   private String User_Name;
   private String User_ID;
   private String Email;
   private String Pass;
   private String Type;
   private String Date;


    //// Photographer Data ////

  private   String Photographer_ID ,First_Name, Last_Name, Gender, Country, City , Address ,  Profile_Img , Contact_No;

    /// Location Data ///

 private    String  Location_ID;
 private    String  Location_Desc;
 private    String  City_ID;
 private    String  Location_Status;



    //// City Data  ///


  private   String City_Des;
  private   String Country_ID;

    /// Country Data ///

  private   String Country_Desc;


    public UserModel() {}

    public UserModel(String User_Name , String id, String email, String pass, String type , String Date) {
        this.User_Name = User_Name;
        this.User_ID = id;
        this.Email = email;
        this.Pass = pass;
        this.Type = type;
        this.Date = Date;

    }





    public UserModel(String id, String email, String pass, String type, String firstname, String lastname, String gender , String imageUrl , String Location_ID , String Contact_No , String Date) {
        this.Photographer_ID = id;
        this.Email = email;
        this.Pass = pass;
        this.Type = type;
        this.First_Name = firstname;
        this.Last_Name = lastname;
        this.Gender = gender;
        this.Profile_Img  = imageUrl;
        this.Location_ID = Location_ID;
        this.Contact_No = Contact_No;
        this.Date  = Date;
    }

    public UserModel(String photographer_ID  , String location_ID, String location_Desc, String city_ID, String location_Status) {
        this.Photographer_ID = photographer_ID;
        this.Location_ID = location_ID;
        this.Location_Desc = location_Desc;
        this.City_ID = city_ID;
        this.Location_Status = location_Status;
    }

    public UserModel(String photographer_ID , String city_ID, String city_Des, String country_ID) {
        this.Photographer_ID = photographer_ID;
        this.City_ID = city_ID;
        this.City_Des = city_Des;
        this.Country_ID = country_ID;
    }


    public UserModel(String Photographer_ID, String country_ID, String country_Desc) {
        this.Photographer_ID = Photographer_ID;
        this.Country_ID = country_ID;
        this.Country_Desc = country_Desc;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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
