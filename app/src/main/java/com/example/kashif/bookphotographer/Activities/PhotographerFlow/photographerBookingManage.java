package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.Adapter.PhotographerBookingCustom;
import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class photographerBookingManage extends AppCompatActivity {


    public static   String[] order;

    public static   String[] photographer;
    public static   String[] eventdate;
    public static   String[] eventvenue;
    public static   String[] pckg;
    public static   String[] id;
    public static   String[] status;


    public static ArrayList<String> Order , Photographer , EventDate, EventVenue, Pckg , Id , Status , resId;


    int couter = 1;
    ListView ListViewOrder;

    FirebaseAuth firebaseAuth;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_booking_manage);

        Order = new ArrayList<String>();
        Photographer = new ArrayList<String>();
        EventDate = new ArrayList<String>();
        EventVenue = new ArrayList<String>();
        Pckg = new ArrayList<String>();
        Id = new ArrayList<String>();
        Status = new ArrayList<String>();
        resId = new ArrayList<String>();



        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");

        ListViewOrder = (ListView) findViewById(R.id.ListViewPhoto);

        getReqData();

        ListViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




                databaseReference.child("ReservationDetail").child(resId.get(i))
                        .child(id[i]).removeValue();

                getReqData();
                ListViewOrder.invalidateViews();

            }
        });






    }

    public  void  BindData(){

       PhotographerBookingCustom adapter = new PhotographerBookingCustom(getApplicationContext() ,id , order , photographer , eventdate , eventvenue , pckg , status);
        ListViewOrder.setAdapter(adapter);

    }

    public void getReqData(){

        databaseReference.child("Reservation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                resId.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    BookReservation bookReservation = snapshot.getValue(BookReservation.class);



                    if (bookReservation.getPhotographer_ID().equals(firebaseAuth.getCurrentUser().getUid())){


                        resId.add(bookReservation.getReservation_ID());

                        getReqData2();

                    }else {

                        Toast.makeText(photographerBookingManage.this, "No Found Data", Toast.LENGTH_SHORT).show();
                    }


                }
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public  void getReqData2(){


        if (!resId.isEmpty()) {

            for (int i =0; i<resId.size(); i++) {
                databaseReference.child("ReservationDetail").child(resId.get(i)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Order.clear();
                        Id.clear();
                        Photographer.clear();
                        EventDate.clear();
                        Pckg.clear();
                        Status.clear();
                        couter = 1;



                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                            if (dataSnapshot1.exists()){

                                BookReservation bookReservation = dataSnapshot1.getValue(BookReservation.class);


                                Order.add(String.valueOf(couter));
                                Id.add(bookReservation.getReservation_Detail_ID());
                                Photographer.add(bookReservation.getPhotographer_Name());
                                EventDate.add(bookReservation.getOccasion_Date());
                                EventVenue.add(bookReservation.getVenue_Location());
                                Pckg.add(bookReservation.getSelected_Package());
                                Status.add(bookReservation.getReservation_Status());
                                couter++;


                                 order = Order.toArray(new String[Order.size()]);

                                photographer =  Photographer.toArray(new String[Photographer.size()]);
                                eventdate = EventDate.toArray(new String[EventDate.size()]);
                                eventvenue = EventVenue.toArray(new String[EventVenue.size()]);
                                pckg = Pckg.toArray(new String[Pckg.size()]);
                                id = Id.toArray(new String[Id.size()]);
                                status = Status.toArray(new String[Status.size()]);


                            }


                        }
                        BindData();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }else {

            Toast.makeText(this, "resID is Empty", Toast.LENGTH_SHORT).show();
        }
    }

}