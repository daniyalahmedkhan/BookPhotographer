package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/6/2018.
 */

public class SampleImag {

  private   String image_ID;
  private   String category_Des;
  private   String category_ID;
  private   String date;
  private   String image1;
  private   String image2;

    public SampleImag() {}

    public SampleImag(String category_Des, String category_ID) {
        this.category_Des = category_Des;
        this.category_ID = category_ID;
    }

    public SampleImag(String Image_ID , String Category_ID , String Date , String image1, String image2) {

        this.image_ID = Image_ID;
        this.category_ID = Category_ID;
        this.date = Date;
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getImage_ID() {
        return image_ID;
    }

    public void setImage_ID(String image_ID) {
        this.image_ID = image_ID;
    }

    public String getCategory_Des() {
        return category_Des;
    }

    public void setCategory_Des(String category_Des) {
        this.category_Des = category_Des;
    }

    public String getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(String category_ID) {
        this.category_ID = category_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
