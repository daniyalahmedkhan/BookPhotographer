package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.kashif.bookphotographer.R;

public class MyProfile extends AppCompatActivity {

    RelativeLayout RelativeBronze, RelativeSilver, RelativeGold, RelativePlatinum, RelativeDiamond;
    RelativeLayout RBronzeDetail, RSilverDetail, RGoldDetail, RPlatinumDetail, RDiamondDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        RelativeBronze = (RelativeLayout) findViewById(R.id.RelativeBronze);
        RelativeSilver = (RelativeLayout) findViewById(R.id.RelativeSilver);
        RelativeGold = (RelativeLayout) findViewById(R.id.RelativeGolden);
        RelativePlatinum = (RelativeLayout) findViewById(R.id.RelativePlatinum);
        RelativeDiamond = (RelativeLayout) findViewById(R.id.RelativeDiamond);

        RBronzeDetail = (RelativeLayout) findViewById(R.id.RelativeBronzeDetail);
        RSilverDetail = (RelativeLayout) findViewById(R.id.RelativeSilverDetail);
        RGoldDetail = (RelativeLayout) findViewById(R.id.RelativeGoldenDetail);
        RPlatinumDetail = (RelativeLayout) findViewById(R.id.RelativePlatinumDetail);
        RDiamondDetail = (RelativeLayout) findViewById(R.id.RelativeDiamondDetail);



        RelativeBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RBronzeDetail.getVisibility() == View.GONE){

                    RBronzeDetail.setVisibility(View.VISIBLE);

                }else {


                    RBronzeDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RSilverDetail.getVisibility() == View.GONE){

                    RSilverDetail.setVisibility(View.VISIBLE);

                }else {


                    RSilverDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RGoldDetail.getVisibility() == View.GONE){

                    RGoldDetail.setVisibility(View.VISIBLE);

                }else {


                    RGoldDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativePlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RPlatinumDetail.getVisibility() == View.GONE){

                    RPlatinumDetail.setVisibility(View.VISIBLE);

                }else {


                    RPlatinumDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeDiamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RDiamondDetail.getVisibility() == View.GONE){

                    RDiamondDetail.setVisibility(View.VISIBLE);

                }else {


                    RDiamondDetail.setVisibility(View.GONE);
                }

            }
        });


    }
}
