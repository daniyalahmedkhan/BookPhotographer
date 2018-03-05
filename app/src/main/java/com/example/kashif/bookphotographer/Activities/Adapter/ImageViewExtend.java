package com.example.kashif.bookphotographer.Activities.Adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.CustomerFlow.Photographer_Profile;
import com.example.kashif.bookphotographer.Activities.PhotographerFlow.MyProfile;
import com.example.kashif.bookphotographer.R;

public class ImageViewExtend extends AppCompatActivity {

    ImageView ImageViewLarge;
   public static String ImageUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ImageViewLarge = (ImageView) findViewById(R.id.ImageViewLarge);

        ImageViewLarge.setImageResource(R.mipmap.logo_passwordreset);
        Glide.with(getApplicationContext()).load(ImageUrl).into(ImageViewLarge);





    }
}
