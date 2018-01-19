package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/19/2018.
 */

public class BookReservation {

    String occ;
    String ven;
    String msg;
    String pkg;
    String id;
    String userid;
    String photographerid;

    public BookReservation() {
    }

    public BookReservation(String id ,String occ, String ven, String msg, String pkg , String userid , String photographerid) {
        this.id = id;
        this.occ = occ;
        this.ven = ven;
        this.msg = msg;
        this.pkg = pkg;
        this.userid = userid;
        this.photographerid = photographerid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhotographerid() {
        return photographerid;
    }

    public void setPhotographerid(String photographerid) {
        this.photographerid = photographerid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public String getVen() {
        return ven;
    }

    public void setVen(String ven) {
        this.ven = ven;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
