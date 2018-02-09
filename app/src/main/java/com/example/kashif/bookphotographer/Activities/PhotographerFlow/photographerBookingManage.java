package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kashif.bookphotographer.Activities.Adapter.PhotographerBookingCustom;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class photographerBookingManage extends AppCompatActivity {


    public static   String[] order = MyProfile.Order.toArray(new String[MyProfile.Order.size()]);

    public static   String[] photographer =  MyProfile.Photographer.toArray(new String[MyProfile.Photographer.size()]);
    public static   String[] eventdate = MyProfile.EventDate.toArray(new String[MyProfile.EventDate.size()]);
    public static   String[] eventvenue = MyProfile.EventVenue.toArray(new String[MyProfile.EventVenue.size()]);
    public static   String[] pckg = MyProfile.Pckg.toArray(new String[MyProfile.Pckg.size()]);
    public static   String[] id = MyProfile.id.toArray(new String[MyProfile.id.size()]);
    public static   String[] status = MyProfile.status.toArray(new String[MyProfile.status.size()]);

    ListView ListViewOrder;

    FirebaseAuth firebaseAuth;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_booking_manage);


        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");

        ListViewOrder = (ListView) findViewById(R.id.ListViewPhoto);

        final PhotographerBookingCustom adapter = new PhotographerBookingCustom(getApplicationContext() ,id , order , photographer , eventdate , eventvenue , pckg , status);
        adapter.notifyDataSetChanged();
        ListViewOrder.setAdapter(adapter);


        ListViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                databaseReference.child("ReservationDetail").child(MyProfile.resId.get(i))
                        .child(id[i]).removeValue();


//                adapter.notifyDataSetChanged();
            }
        });



    }
}
