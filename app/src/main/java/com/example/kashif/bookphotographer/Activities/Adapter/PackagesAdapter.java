package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kashif.bookphotographer.Activities.ModelClass.PackageClass;
import com.example.kashif.bookphotographer.R;

import java.util.ArrayList;

/**
 * Created by Kashif on 1/24/2018.
 */

public class PackagesAdapter extends BaseAdapter {

    public final ArrayList<PackageClass> arrData;
    public Context mContext;


    public PackagesAdapter(Context context , ArrayList<PackageClass> data) {

        this.mContext = context;
        this.arrData = data;

    }

    @Override
    public int getCount() {
        return arrData.size(); }

    @Override
    public Object getItem(int i) {

        return null;
    }

    public void addItems(ArrayList<PackageClass> arr)
    {
        if(arr.size()>0)
        {
            arrData.addAll(arr);
        notifyDataSetChanged();
        }
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

            view = inflater.inflate(R.layout.item_package, null);

            obj.txtPackageName = (TextView) view.findViewById(R.id.txtPackageName);
            obj.txtPackagePrice = (TextView) view.findViewById(R.id.txtPackagePrice);
            obj.txtPackageDescription = (TextView) view.findViewById(R.id.txtPackageDescription);
            obj.txtServiceDays = (TextView) view.findViewById(R.id.txtServiceDays);
            view.setTag(obj);


        } else {
            //gridView = (View) view;
            obj = (ViewHodler)view.getTag();
        }

        PackageClass entity = arrData.get(i);
        obj.txtPackageName.setText(entity.getPackage_Name());
        obj.txtPackagePrice.setText("Price : "+entity.getPackage_Price());
        obj.txtPackageDescription.setText("Description : "+entity.getPackage_Description());
        obj.txtServiceDays.setText("Service Days : "+entity.getServices_Days());


        return view;
    }


    public  static  class  ViewHodler{

        TextView txtPackageName, txtPackagePrice, txtPackageDescription, txtServiceDays;


    }
}
