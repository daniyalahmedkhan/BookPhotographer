package com.example.kashif.bookphotographer.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    EditText editText;
    String ESearch;
    public static ArrayList<String> name , loc , imgrl, id;
    TextView search;
    DatabaseReference databaseReference;

//    public static   String[] gridViewString ;
//
//    public static   String[] gridViewString2 ;
//
//
//
//    public static   String[] gridViewImageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        name = new ArrayList<String>();
        loc = new ArrayList<String>();
        imgrl = new ArrayList<String>();
        id = new ArrayList<String>();


        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        search = (TextView) findViewById(R.id.search);
        editText = (EditText) findViewById(R.id.EditTxt);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().isEmpty()){

                    editText.setError("Enter Name");

                }else {


                    ESearch = editText.getText().toString().trim();
                    getData();
                }


            }
        });




    }


    public void getData(){


        databaseReference.child("users").orderByChild("firstname").equalTo(ESearch).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                    UserModel userModel = snapshot.getValue(UserModel.class);


                    name.add(userModel.getFirstname());
                    id.add(userModel.getId());
                    loc.add(userModel.getCity());
                    imgrl.add(userModel.getImageUrl());




                }

//             img = String.valueOf(Glide.with(getApplicationContext()).load(imgrl));


                Intent intent = new Intent(HomeActivity.this , SearchPhotographer.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
