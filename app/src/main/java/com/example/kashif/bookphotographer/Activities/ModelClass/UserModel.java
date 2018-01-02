package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/1/2018.
 */

public class UserModel  {


    //// User Data ///
    String id;
    String email;
    String pass;
    String type;


    //// Photographer Data ////

    String firstname, lastname, gender, country, city, add;

    public UserModel() {
    }

    public UserModel(String id, String email, String pass, String type) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }


    public UserModel(String id, String email, String pass, String type, String firstname, String lastname, String gender, String country, String city, String add) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.type = type;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.add = add;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
