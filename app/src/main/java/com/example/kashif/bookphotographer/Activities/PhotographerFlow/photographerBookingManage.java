package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    public static   String[] remarks;
    public static  String[] userEmail;
    String key;

    public static ArrayList<String> Order , Photographer , EventDate, EventVenue, Pckg , Id , Status , Remarks , useremail;
    static ArrayList<String> resId;
    ArrayList<String> refId;

    static int couter = 1;
    static ListView ListViewOrder;

    static FirebaseAuth firebaseAuth;


    static DatabaseReference databaseReference;

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
        refId = new ArrayList<String>();
        Remarks = new ArrayList<String>();
        useremail = new ArrayList<String>();


        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");

        ListViewOrder = (ListView) findViewById(R.id.ListViewPhoto);

       ListViewOrder.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

               AlertDialog.Builder  builder = new AlertDialog.Builder(photographerBookingManage.this);
               LayoutInflater inflater = photographerBookingManage.this.getLayoutInflater();
               final View v = inflater.inflate(R.layout.booking_confirmation, null);
               builder.setView(v);

               final AlertDialog b = builder.create();
               b.show();


               Button Accept = (Button)v.findViewById(R.id.btnAccpt);
               Button Reject = (Button)v.findViewById(R.id.btnReject);

               Accept.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {




                       databaseReference.child("ReservationDetail").child(resId.get(i))
                               .child(id[i]).child("reservation_Status").setValue("Accept");

                       resId.clear();
                       Order.clear();
                       Id.clear();
                       Photographer.clear();
                       EventDate.clear();
                       EventVenue.clear();
                       Pckg.clear();
                       Status.clear();
                       Remarks.clear();
                       useremail.clear();
                       couter++;

                       order = new String[0];

                       photographer = new String[0];
                       eventdate = new String[0];
                       eventvenue = new String[0];
                       pckg = new String[0];
                       id = new String[0];
                       status = new String[0];
                       remarks = new String[0];
                       userEmail = new String[0];

                       ListViewOrder.deferNotifyDataSetChanged();
                       ListViewOrder.invalidateViews();
                       b.dismiss();

                       MyProfile.reqCounter = 0;

                      photographerBookingManage.this.finish();
                      Intent intent = new Intent(photographerBookingManage.this , photographerBookingManage.class);
                      startActivity(intent);
                   }
               });


               Reject.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {




                       databaseReference.child("ReservationDetail").child(resId.get(i))
                               .child(id[i]).removeValue();


                       resId.clear();
                       Order.clear();
                       Id.clear();
                       Photographer.clear();
                       EventDate.clear();
                       EventVenue.clear();
                       Pckg.clear();
                       Status.clear();
                       Remarks.clear();
                       useremail.clear();
                       couter++;

                       order = new String[0];

                       photographer = new String[0];
                       eventdate = new String[0];
                       eventvenue = new String[0];
                       pckg = new String[0];
                       id = new String[0];
                       status = new String[0];
                        remarks = new String[0];
                        userEmail = new String[0];
                       ListViewOrder.deferNotifyDataSetChanged();
                       ListViewOrder.invalidateViews();
                       b.dismiss();

                       MyProfile.reqCounter = 0;


                       photographerBookingManage.this.finish();
                       Intent intent = new Intent(photographerBookingManage.this , photographerBookingManage.class);
                       startActivity(intent);

                   }
               });

               return false;
           }
       });


        getReqData(getApplicationContext());





    }

    public static void  BindData(Context context){

       PhotographerBookingCustom adapter = new PhotographerBookingCustom(context ,id , order , photographer , eventdate , eventvenue , pckg , status , remarks , userEmail);

       ListViewOrder.setAdapter(adapter);
       ListViewOrder.deferNotifyDataSetChanged();
        adapter.notifyDataSetChanged();



    }


   static public  void   getReqData(final Context context){




        databaseReference.child("ReservationDetail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                            BookReservation bookReservation = snapshot1.getValue(BookReservation.class);


                            if (bookReservation.getPhotographer_ID().equals(firebaseAuth.getCurrentUser().getUid())) {


                                Order.add(String.valueOf(couter));
                                Id.add(bookReservation.getReservation_Detail_ID());
                                Photographer.add(bookReservation.getUser_Email());
                                EventDate.add(bookReservation.getOccasion_Date());
                                EventVenue.add(bookReservation.getVenue_Location());
                                Pckg.add(bookReservation.getSelected_Package());
                                Status.add(bookReservation.getReservation_Status());
                                resId.add(bookReservation.getReservation_ID());
                                Remarks.add(bookReservation.getCustomer_Message());
                                useremail.add(bookReservation.getUser_Email());
                                couter++;

                                order = Order.toArray(new String[Order.size()]);

                                photographer = Photographer.toArray(new String[Photographer.size()]);
                                eventdate = EventDate.toArray(new String[EventDate.size()]);
                                eventvenue = EventVenue.toArray(new String[EventVenue.size()]);
                                pckg = Pckg.toArray(new String[Pckg.size()]);
                                id = Id.toArray(new String[Id.size()]);
                                status = Status.toArray(new String[Status.size()]);
                                remarks = Remarks.toArray(new String[Remarks.size()]);
                                userEmail = useremail.toArray(new String[useremail.size()]);



                            } else {


                            }


                        }
                        BindData(context);
                        
                    }


                }else {


                    order = new String[0];

                    photographer = new String[0];
                    eventdate = new String[0];
                    eventvenue = new String[0];
                    pckg = new String[0];
                    id = new String[0];
                    status = new String[0];
                    remarks = new String[0];
                    userEmail = new String[0];
                    BindData(context);


                }
            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}