package com.example.kashif.bookphotographer.Activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.Activities.ModelClass.PkgClass;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class BookingActivity extends AppCompatActivity {


    EditText Ocassions, Venue , Msg;
    AlertDialog alertDialog;
    Spinner EditPck;
    AlertDialog.Builder builder;
    public  static List<Date> dates;
    public String id, Pkg, occ , ven , msg;
    Button BtnSubmit;
    DatabaseReference firebaseDatabase;

    String UserID , PhotographerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_booking);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("allusers");


        EditPck = (Spinner) findViewById(R.id.EditPck);


        Ocassions = (EditText) findViewById(R.id.Ocassions);
        Venue = (EditText) findViewById(R.id.Venue);
        Msg = (EditText) findViewById(R.id.Msg);
        BtnSubmit = (Button) findViewById(R.id.BtnSubmit);

        ArrayAdapter<String> adp = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,Photographer_Profile.arrayList);
        EditPck.setAdapter(adp);


        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            BookRes();

            }
        });

        EditPck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Pkg = adapterView.getItemAtPosition(i).toString();
               // Toast.makeText(adapterView.getContext(), city, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public  void BookRes(){


        if(!(Ocassions.getText().toString().isEmpty() && Venue.getText().toString().isEmpty()

                && Msg.getText().toString().isEmpty())){


            occ = Ocassions.getText().toString().trim();
            ven = Venue.getText().toString().trim();
            msg = Msg.getText().toString().trim();
            id  = firebaseDatabase.push().getKey();
            UserID  = LoginActivity.uid;
            PhotographerID = SearchPhotographer.CurrntID;



            ForUser();
            ForPhotographer();

        }else {

            Toast.makeText(BookingActivity.this , "Can't Leave as Empty " , Toast.LENGTH_SHORT).show();



        }

    }


    public void ForUser(){


        BookReservation bookReservation = new BookReservation(id , occ , ven , msg , Pkg , UserID , PhotographerID);

        firebaseDatabase.child("bookres").child("foruser").child(LoginActivity.uid).child(id).setValue(bookReservation, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){


                    Toast.makeText(BookingActivity.this , "Error in Saving" , Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent = new Intent(BookingActivity.this , HomeActivity.class);
                    startActivity(intent);


                }


            }
        });

    }


    public  void  ForPhotographer(){
        BookReservation bookReservation = new BookReservation(id , occ , ven , msg , Pkg , UserID , PhotographerID);

        firebaseDatabase.child("bookres").child("forphotographer").child(SearchPhotographer.CurrntID).child(id).setValue(bookReservation, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {



            }
        });


    }



}