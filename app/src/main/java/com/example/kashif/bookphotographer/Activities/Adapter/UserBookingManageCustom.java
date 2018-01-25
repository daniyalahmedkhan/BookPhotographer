package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.UserBookingManage;
import com.example.kashif.bookphotographer.R;

import java.util.ArrayList;

/**
 * Created by Kashif on 1/20/2018.
 */

public class UserBookingManageCustom extends BaseAdapter {

    public Context mContext;
  public final  String[] order;
   public final String[] photographer;
   public final String[] eventdates;
   public final String[] eventvenue;
   public final String[] packages;


   public UserBookingManageCustom(Context context , String[] order , String[ ] photographer ,
                                  String[] eventdates , String[] eventvenue , String[] packages){




       mContext = context;
       this.order = order;
       this.photographer = photographer;
       this.eventdates = eventdates;
       this.eventvenue = eventvenue;
       this.packages = packages;
   }


    @Override
    public int getCount() {
        return photographer.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        ViewHodler obj;

        if (view == null) {

            obj = new ViewHodler();


            view = inflater.inflate(R.layout.user_manage_bookin_custom, null);
            obj.ord = (TextView) view.findViewById(R.id.Order);
            obj.pho = (TextView) view.findViewById(R.id.PhotographerName);
            obj.evd = (TextView) view.findViewById(R.id.EventDates);
            obj.evV = (TextView) view.findViewById(R.id.EventVenue);
            obj.pkg = (TextView) view.findViewById(R.id.SelectedPkg);
            obj.ord.setText(order[i]);
            obj.pho.setText(photographer[i]);
            obj.evd.setText(eventdates[i]);
            obj.evV.setText(eventvenue[i]);
            obj.pkg.setText(packages[i]);

            view.setTag(obj);

        } else {
            //gridView = (View) view;
            obj = (ViewHodler)view.getTag();
        }



        return view;
    }



    public  static  class  ViewHodler{

        TextView ord , pho , evd , evV , pkg;




    }
}