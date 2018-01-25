package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kashif.bookphotographer.R;

import java.util.ArrayList;

/**
 * Created by Kashif on 1/24/2018.
 */

public class ListofAddEvents extends BaseAdapter {

    public final ArrayList<String> PhotographerName;
    public final ArrayList<String> SelectedVenue;
    public final ArrayList<String> SelectedPackage;
    public final ArrayList<String> YourMessage;
    public Context mContext;


    public ListofAddEvents(Context context , ArrayList<String> photographerName, ArrayList<String> selectedVenue, ArrayList<String> selectedPackage, ArrayList<String> yourMessage) {

        this.mContext = context;
        this.PhotographerName = photographerName;
        this.SelectedVenue = selectedVenue;
        this.SelectedPackage = selectedPackage;
        this.YourMessage = yourMessage;
    }

    @Override
    public int getCount() {
        return PhotographerName.size(); }

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


            view = inflater.inflate(R.layout.book_add_eventcustom, null);
            obj.name = (TextView) view.findViewById(R.id.photographerNameList);
            obj.event = (TextView) view.findViewById(R.id.SelectedVanueList);
            obj.pckg = (TextView) view.findViewById(R.id.SelectedPkgList);
            obj.msg = (TextView) view.findViewById(R.id.SelectedMessageList);

            obj.name.setText(PhotographerName.get(i));
            obj.event.setText(SelectedVenue.get(i));
            obj.pckg.setText(SelectedPackage.get(i));
            obj.msg.setText(YourMessage.get(i));



            view.setTag(obj);


        } else {
            //gridView = (View) view;
            obj = (ViewHodler)view.getTag();
        }



        return view;
    }


    public  static  class  ViewHodler{

        TextView name, event, pckg, msg;




    }
}
