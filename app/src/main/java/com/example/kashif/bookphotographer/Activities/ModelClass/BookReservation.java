package com.example.kashif.bookphotographer.Activities.ModelClass;

import java.util.ArrayList;

/**
 * Created by Kashif on 1/19/2018.
 */

public class BookReservation {

    String Reservation_Detail_ID;
    String Reservation_Status;
    String Reservation_ID;
    String Reservation_Date;


    String Occasion_Date;
    String Venue_Location;
    String Customer_Message;
    String Selected_Package;

    String Customer_ID;
    String Photographer_ID;
    String User_Email;
    String Photographer_Name;



    public BookReservation(String id, String userid, String photographerid, String todaydate) {
        this.Reservation_ID = id;
        this.Customer_ID = userid;
        this.Photographer_ID = photographerid;
        this.Reservation_Date = todaydate;
    }

    public BookReservation(String Reservation_Detail_ID , String Reservation_Status,  String occ, String ven, String msg, String pkg, String id, String userid, String photographerid, String useremail, String photographername, String todaydate) {

        this.Reservation_Detail_ID = Reservation_Detail_ID;
        this.Reservation_Status = Reservation_Status;
        this.Occasion_Date = occ;
        this.Venue_Location = ven;
        this.Customer_Message = msg;
        this.Selected_Package = pkg;
        this.Reservation_ID = id;
        this.Customer_ID = userid;
        this.Photographer_ID = photographerid;
        this.User_Email = useremail;
        this.Photographer_Name = photographername;
        this.Reservation_Date = todaydate;
    }

    public String getReservation_Detail_ID() {
        return Reservation_Detail_ID;
    }

    public void setReservation_Detail_ID(String reservation_Detail_ID) {
        Reservation_Detail_ID = reservation_Detail_ID;
    }

    public String getReservation_Status() {
        return Reservation_Status;
    }

    public void setReservation_Status(String reservation_Status) {
        Reservation_Status = reservation_Status;
    }

    public String getOccasion_Date() {
        return Occasion_Date;
    }

    public void setOccasion_Date(String occasion_Date) {
        Occasion_Date = occasion_Date;
    }

    public String getVenue_Location() {
        return Venue_Location;
    }

    public void setVenue_Location(String venue_Location) {
        Venue_Location = venue_Location;
    }

    public String getCustomer_Message() {
        return Customer_Message;
    }

    public void setCustomer_Message(String customer_Message) {
        Customer_Message = customer_Message;
    }

    public String getSelected_Package() {
        return Selected_Package;
    }

    public void setSelected_Package(String selected_Package) {
        Selected_Package = selected_Package;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getPhotographer_Name() {
        return Photographer_Name;
    }

    public void setPhotographer_Name(String photographer_Name) {
        Photographer_Name = photographer_Name;
    }

    public String getReservation_ID() {
        return Reservation_ID;
    }

    public void setReservation_ID(String reservation_ID) {
        Reservation_ID = reservation_ID;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getPhotographer_ID() {
        return Photographer_ID;
    }

    public void setPhotographer_ID(String photographer_ID) {
        Photographer_ID = photographer_ID;
    }

    public String getReservation_Date() {
        return Reservation_Date;
    }

    public void setReservation_Date(String reservation_Date) {
        Reservation_Date = reservation_Date;
    }
}