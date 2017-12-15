package com.example.kashif.bookphotographer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.kashif.bookphotographer.R;

public class Photographer_Profile extends AppCompatActivity {

    RelativeLayout relativeLayout , Relative1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer__profile);

        relativeLayout = (RelativeLayout) findViewById(R.id.RelativeExp);
        Relative1 = (RelativeLayout) findViewById(R.id.Relative1);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Relative1.getVisibility() == View.GONE){

                    Relative1.setVisibility(View.VISIBLE);


                }else {


                    Relative1.setVisibility(View.GONE);

                }


            }
        });


    }
}
