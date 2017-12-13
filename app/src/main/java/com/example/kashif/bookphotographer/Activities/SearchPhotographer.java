package com.example.kashif.bookphotographer.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import com.example.kashif.bookphotographer.Activities.Adapter.Custom_SearchPhotographer;
import com.example.kashif.bookphotographer.R;

public class SearchPhotographer extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "Daniyal ak", "M.Ahsan", "J.Jaffery", "Qamber", "Sadat Ali", "M.Farhan"

    } ;

    String[] gridViewString2 = {
            "Karchi", "Lahore", "Multan", "Islamabad", "Quetta", "Murre"

    } ;



    int[] gridViewImageId = {
            R.mipmap.contactlist_img_one , R.mipmap.contactlist_img_two , R.mipmap.contactlist_img_three , R.mipmap.contactlist_img_four , R.mipmap.contactlist_img_one , R.mipmap.contactlist_img_two
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);



        setContentView(R.layout.activity_search_photographer);


        Custom_SearchPhotographer adapterViewAndroid = new Custom_SearchPhotographer(SearchPhotographer.this, gridViewString, gridViewString2, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);



    }
}
