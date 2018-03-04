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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ImageViewLarge = (ImageView) findViewById(R.id.ImageViewLarge);


        try {


            if (!((MyProfile.ImageLargeView.isEmpty()) && (MyProfile.ID.isEmpty()))){

                ImageViewLarge.setImageResource(R.mipmap.logo_passwordreset);
                Glide.with(getApplicationContext()).load(MyProfile.ImageLargeView).into(ImageViewLarge);


            }else {

                ImageViewLarge.setImageResource(R.mipmap.logo_passwordreset);
                Glide.with(getApplicationContext()).load(Photographer_Profile.ImageLargeView).into(ImageViewLarge);


            }


        }catch (Exception e ){}






    }
}
