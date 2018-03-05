package com.example.kashif.bookphotographer.Activities.ModelClass;

import java.util.ArrayList;

/**
 * Created by Kashif on 1/19/2018.
 */

public class BookReservation {

  private  String  noRequest;
  private   String reservation_Detail_ID;
  private   String reservation_Status;
  private   String reservation_ID;
  private   String reservation_Date;


  private   String occasion_Date;
  private   String venue_Location;
  private   String customer_Message;
  private   String selected_Package;

  private   String customer_ID;
  private   String photographer_ID;
  private   String user_Email;
  private   String photographer_Name;


    public BookReservation() {
    }

    public BookReservation(String id, String userid, String photographerid, String todaydate , String noRequest) {
        this.reservation_ID = id;
        this.customer_ID = userid;
        this.photographer_ID = photographerid;
        this.reservation_Date = todaydate;
        this.noRequest = noRequest;
    }

    public BookReservation(String Reservation_Detail_ID , String Reservation_Status,  String occ, String ven, String msg, String pkg, String id, String userid, String photographerid, String useremail, String photographername, String todaydate) {

        this.reservation_Detail_ID = Reservation_Detail_ID;
        this.reservation_Status = Reservation_Status;
        this.occasion_Date = occ;
        this.venue_Location = ven;
        this.customer_Message = msg;
        this.selected_Package = pkg;
        this.reservation_ID = id;
        this.customer_ID = userid;
        this.photographer_ID = photographerid;
        this.user_Email = useremail;
        this.photographer_Name = photographername;
        this.reservation_Date = todaydate;
    }



    public String getNoRequest() {
        return noRequest;
    }

    public void setNoRequest(String noRequest) {
        this.noRequest = noRequest;
    }

    public String getReservation_Detail_ID() {
        return reservation_Detail_ID;
    }

    public void setReservation_Detail_ID(String reservation_Detail_ID) {
        this.reservation_Detail_ID = reservation_Detail_ID;
    }

    public String getReservation_Status() {
        return reservation_Status;
    }

    public void setReservation_Status(String reservation_Status) {
        this.reservation_Status = reservation_Status;
    }

    public String getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(String reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public String getReservation_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(String reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public String getOccasion_Date() {
        return occasion_Date;
    }

    public void setOccasion_Date(String occasion_Date) {
        this.occasion_Date = occasion_Date;
    }

    public String getVenue_Location() {
        return venue_Location;
    }

    public void setVenue_Location(String venue_Location) {
        this.venue_Location = venue_Location;
    }

    public String getCustomer_Message() {
        return customer_Message;
    }

    public void setCustomer_Message(String customer_Message) {
        this.customer_Message = customer_Message;
    }

    public String getSelected_Package() {
        return selected_Package;
    }

    public void setSelected_Package(String selected_Package) {
        this.selected_Package = selected_Package;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getPhotographer_ID() {
        return photographer_ID;
    }

    public void setPhotographer_ID(String photographer_ID) {
        this.photographer_ID = photographer_ID;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getPhotographer_Name() {
        return photographer_Name;
    }

    public void setPhotographer_Name(String photographer_Name) {
        this.photographer_Name = photographer_Name;
    }
}