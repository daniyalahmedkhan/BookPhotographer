package com.example.kashif.bookphotographer.Activities.CustomerFlow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kashif.bookphotographer.Activities.Adapter.UserBookingManageCustom;
import com.example.kashif.bookphotographer.Activities.CustomerFlow.HomeActivity;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DatabaseReference;

public class UserBookingManage extends AppCompatActivity {


    public static   String[] order = HomeActivity.Order.toArray(new String[HomeActivity.Order.size()]);

    public static   String[] photographer =  HomeActivity.Photographer.toArray(new String[HomeActivity.Photographer.size()]);
  public   static   String[] eventdate = HomeActivity.EventDate.toArray(new String[HomeActivity.EventDate.size()]);
  public static   String[] eventvenue = HomeActivity.EventVenue.toArray(new String[HomeActivity.EventVenue.size()]);
  public static   String[] pckg = HomeActivity.Pckg.toArray(new String[HomeActivity.Pckg.size()]);

    String photographerName;
    int couter = 1;
    ListView ListViewOrder;
    Context context;
    DatabaseReference firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking_manage);



        ListViewOrder = (ListView) findViewById(R.id.ListView);



        UserBookingManageCustom adapter = new UserBookingManageCustom(getApplicationContext(), order , photographer , eventdate , eventvenue , pckg);
        ListViewOrder.setAdapter(adapter);




    }


}
