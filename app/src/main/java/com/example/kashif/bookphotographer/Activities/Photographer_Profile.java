package com.example.kashif.bookphotographer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.kashif.bookphotographer.R;

public class Photographer_Profile extends AppCompatActivity {

    RelativeLayout relativeLayout , Relative1;
    Button BtnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer__profile);

        relativeLayout = (RelativeLayout) findViewById(R.id.RelativeExp);
        Relative1 = (RelativeLayout) findViewById(R.id.Relative1);
        BtnRes = (Button) findViewById(R.id.BtnRes);

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

        BtnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent   intent = new Intent(Photographer_Profile.this , BookingActivity.class);
                startActivity(intent);

            }
        });


    }
}
