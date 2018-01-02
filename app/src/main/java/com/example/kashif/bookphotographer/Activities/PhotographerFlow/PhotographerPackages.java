package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kashif.bookphotographer.R;

public class PhotographerPackages extends AppCompatActivity {

    Button btnNEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_packages);

        btnNEXT = (Button) findViewById(R.id.NEXT);

        btnNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PhotographerPackages.this , SampleImages.class);
                startActivity(intent);
            }
        });



    }

}
