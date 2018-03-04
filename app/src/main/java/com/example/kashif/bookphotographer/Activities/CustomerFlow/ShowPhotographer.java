package com.example.kashif.bookphotographer.Activities.CustomerFlow;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kashif.bookphotographer.Activities.Adapter.Custom_SearchPhotographer;
import com.example.kashif.bookphotographer.R;

public class ShowPhotographer extends AppCompatActivity {


    GridView androidGridView;

   public static String CurrntID;

  public static   String[] gridViewString ;

  public static   String[] gridViewString2 ;



  public static   String[] gridViewImageId ;

  public  static  String[] id ;

    public ShowPhotographer() {
    }

    public ShowPhotographer(String[] gridViewString , String[] gridViewString2 , String[] gridViewImageId , String[] id) {

        this.gridViewString = gridViewString;
        this.gridViewString2 = gridViewString2;
        this.gridViewImageId = gridViewImageId;
        this.id = id;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_search_photographer);

        Custom_SearchPhotographer adapterViewAndroid = new Custom_SearchPhotographer(ShowPhotographer.this, gridViewString, gridViewString2, gridViewImageId , id);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        adapterViewAndroid.notifyDataSetChanged();

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 TextView name =  view.findViewById(R.id.Txt1);
                String name2 = name.getText().toString();
               // String id =

               CurrntID = null;
               CurrntID = id[i];
                Intent intent = new Intent(ShowPhotographer.this , Photographer_Profile.class);
                startActivity(intent);

               //Toast.makeText(ShowPhotographer.this, "" + name2 + "" + id[i], Toast.LENGTH_SHORT).show();
           }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ShowPhotographer.this , HomeActivity.class);
        startActivity(intent);
        this.finish();
    }
}
