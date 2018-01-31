package com.example.kashif.bookphotographer.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.Adapter.ListofAddEvents;
import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.Activities.ModelClass.PkgClass;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class BookingActivity extends AppCompatActivity {


    EditText Ocassions, Venue , Msg;

    Spinner EditPck;

    public  static List<Date> dates;


    Button BtnSubmit , BtnAdd;
    DatabaseReference firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ListView ListofEvents;

    ArrayList<String> arrOC, arrVEN , arrPKG , arrMSG;

    String PushId , UId , PID , TodayDate , OCC , VEN , MSG , PKG , UEMAIL , PNAME ;

    ArrayList<String> id, Pkg, occ , ven , msg ,UserID , PhotographerID , UserEmail, PhotographerName;



    Calendar mCalender;
    int month , day , year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_booking);


        /// Getting Current Date and Time //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        TodayDate = dateFormat.format(cal.getTime());
        ///

        mCalender = Calendar.getInstance();
        day = mCalender.get(Calendar.DAY_OF_MONTH);
        month = mCalender.get(Calendar.MONTH);
        year = mCalender.get(Calendar.YEAR);


        arrOC = new ArrayList<String>();
        arrVEN = new ArrayList<String>();
        arrPKG = new ArrayList<String>();
        arrMSG = new ArrayList<String>();

        id = new ArrayList<String>();
        Pkg = new ArrayList<String>();
        occ = new ArrayList<String>();
        ven = new ArrayList<String>();
        msg = new ArrayList<String>();
        UserID = new ArrayList<String>();
        PhotographerID = new ArrayList<String>();
        UserEmail = new ArrayList<String>();
        PhotographerName = new ArrayList<String>();



        final ListofAddEvents listofAddEvents = new ListofAddEvents(BookingActivity.this, arrOC , arrVEN , arrPKG, arrMSG);


        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("allusers");


        EditPck = (Spinner) findViewById(R.id.EditPck);


        Ocassions = (EditText) findViewById(R.id.Ocassions);
        Venue = (EditText) findViewById(R.id.Venue);
        Msg = (EditText) findViewById(R.id.Msg);
        BtnSubmit = (Button) findViewById(R.id.BtnSubmit);
        BtnAdd = (Button) findViewById(R.id.BtnAdd);

        ListofEvents = (ListView) findViewById(R.id.ListofEvents);


        ArrayAdapter<String> adp = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,Photographer_Profile.arrayList);
        EditPck.setAdapter(adp);


        Ocassions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        i1 = i1+1;

                        //Toast.makeText(BookingActivity.this , " " +i2 + "/" + i1 + "/" + i , Toast.LENGTH_SHORT ).show();
                      Ocassions.setText(i2 + "/" + i1 + "/" + i);

                    }
                } , year , month , day);
                datePickerDialog.show();
            }
        });



        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!(Ocassions.getText().toString().isEmpty() && Venue.getText().toString().isEmpty()

                        && Msg.getText().toString().isEmpty())){


                   arrOC.add(Ocassions.getText().toString().trim());
                   arrVEN.add(Venue.getText().toString().trim());
                   arrMSG.add(Msg.getText().toString().trim());
                   arrPKG.addAll(Pkg);

                    listofAddEvents.notifyDataSetChanged();
                    ListofEvents.setAdapter(listofAddEvents);

                    Ocassions.setText("");
                    Venue.setText("");
                    Msg.setText("");


                }else {

                    Toast.makeText(BookingActivity.this , "Can't Leave as Empty " , Toast.LENGTH_SHORT).show();


                }

            }
        });





        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            BookRes();



            }
        });

        EditPck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                Pkg.add(adapterView.getItemAtPosition(i).toString());
               // Toast.makeText(adapterView.getContext(), city, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

}


    public  void BookRes(){


        if(!(arrOC.isEmpty() && arrMSG.isEmpty() && arrMSG.isEmpty() && arrPKG.isEmpty())){




            occ.addAll(arrOC);
            ven.addAll(arrVEN);
            msg.addAll(arrMSG);
            PushId = firebaseDatabase.push().getKey();
            UserID.add(LoginActivity.uid);
            PhotographerID.add(SearchPhotographer.CurrntID);
            UserEmail.add(LoginActivity.email);
            PhotographerName.add(Photographer_Profile.Fname);
           // Reservation();




        }else {

            Toast.makeText(BookingActivity.this , "No Event Added" , Toast.LENGTH_SHORT).show();



        }

    }

//    public void Reservation(){
//
//        for (int i =1; i<UserID.size(); i++){
//
//            UId = UserID.get(i);
//            PID = PhotographerID.get(i);
//
//
//
//        BookReservation bookReservation = new BookReservation(PushId , UId , PID , TodayDate);
//
//        firebaseDatabase.child("Reservation").child(PushId).setValue(bookReservation, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//
//                if (databaseReference.equals(databaseError)){
//
//
//                    Toast.makeText(BookingActivity.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
//
//                }else {
//
//
//                    //ReservationDetail();
//                }
//
//            }
//        });
//
//        }
//
//
//
//    }

//
//    public void ReservationDetail(){
//
//
//        for (int i =0; i<ListofEvents.getAdapter().getCount(); i++){
//
//
//
//
//            UId = UserID.get(1);
//            PID = PhotographerID.get(1);
//            OCC = occ.get(i);
//            VEN = ven.get(i);
//            MSG = msg.get(i);
//            PKG = Pkg.get(i);
//            UEMAIL = UserEmail.get(1);
//            PNAME = PhotographerName.get(1);
//
//            BookReservation bookReservation = new BookReservation(OCC , VEN , MSG , PKG , PushId , UId , PID , UEMAIL , PNAME , TodayDate);
//
//
//            firebaseDatabase.child("ReservationDetail").child(PushId).child(String.valueOf(i)).setValue(bookReservation, new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//
//
//                    if (databaseReference.equals(databaseError)){
//
//
//                        Toast.makeText(BookingActivity.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
//
//                    }else {
//
//                        Intent intent = new Intent(BookingActivity.this , HomeActivity.class);
//                        startActivity(intent);
//
//                    }
//
//
//
//
//                }
//            });
//
//        }
//
//
//
//    }
//



}