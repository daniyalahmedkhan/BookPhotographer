package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/6/2018.
 */

public class SampleImag {


  private   String Category_Des;
  private   String Category_ID;
  private   String Date;
  private   String Image1;
  private   String Image2;

    public SampleImag() {}

    public SampleImag(String category_Des, String category_ID) {
        Category_Des = category_Des;
        Category_ID = category_ID;
    }

    public SampleImag(String Category_ID , String Date , String image1, String image2) {


        this.Category_ID = Category_ID;
        this.Date = Date;
        this.Image1 = image1;
        this.Image2 = image2;
    }

    public String getCategory_Des() {
        return Category_Des;
    }

    public void setCategory_Des(String category_Des) {
        Category_Des = category_Des;
    }

    public String getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(String category_ID) {
        Category_ID = category_ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }
}
