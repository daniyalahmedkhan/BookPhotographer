package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.kashif.bookphotographer.Activities.PhotographerFlow.MyProfile;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kashif on 1/20/2018.
 */

public class PhotographerBookingCustom extends BaseAdapter {

    public Context mContext;
    public  String[] id;
    public   String[] order;
    public  String[] user;
    public  String[] eventdates;
    public  String[] eventvenue;
    public  String[] packages;
    public   String[] status;
    public   String[] remarks;
    DatabaseReference databaseReference;

    public PhotographerBookingCustom(Context context , String[] id,  String[] order , String[ ] user ,
                                   String[] eventdates , String[] eventvenue , String[] packages , String[] status, String[] remarks){




        mContext = context;
        this.id = id;
        this.order = order;
        this.user = user;
        this.eventdates = eventdates;
        this.eventvenue = eventvenue;
        this.packages = packages;
        this.status = status;
        this.remarks = remarks;
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");

    }

    public String[] getId() {
        return id;
    }

    @Override
    public int getCount() {
        return user.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        ViewHodler obj;

        if (view == null) {

           obj = new ViewHodler();


            view = inflater.inflate(R.layout.photograher_manage_bookin_custom, null);

            obj.usr = (TextView) view.findViewById(R.id.PhotographerName);
            obj.evd = (TextView) view.findViewById(R.id.EventDates);
            obj.evV = (TextView) view.findViewById(R.id.EventVenue);
            obj.pkg = (TextView) view.findViewById(R.id.SelectedPkg);
            obj.status = (TextView) view.findViewById(R.id.Status);
            obj.remarks = (TextView)  view.findViewById(R.id.UserRemarks);





            obj.usr.setText(user[i]);
            obj.evd.setText(eventdates[i]);
            obj.evV.setText(eventvenue[i]);
            obj.pkg.setText(packages[i]);
            obj.status.setText(status[i]);
            obj.remarks.setText(remarks[i]);

            view.setTag(obj);

        } else {
            //gridView = (View) view;
            notifyDataSetChanged();
            obj = (ViewHodler)view.getTag();
        }




        return view;
    }

    public  static  class  ViewHodler{

        TextView usr , evd , evV , pkg , status , remarks;




    }
}
