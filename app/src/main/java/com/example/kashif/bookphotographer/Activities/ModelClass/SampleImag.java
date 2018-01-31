package com.example.kashif.bookphotographer.Activities.ModelClass;

/**
 * Created by Kashif on 1/6/2018.
 */

public class SampleImag {


    String Photographer_ID;
    String Gallery_ID;
    String Date;
    String image1;
    String image2;

    public SampleImag() {
    }

    public SampleImag(String Photographer_ID , String Gallery_ID , String Date ,String image1, String image2) {

        this.image1 = image1;
        this.image2 = image2;
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
