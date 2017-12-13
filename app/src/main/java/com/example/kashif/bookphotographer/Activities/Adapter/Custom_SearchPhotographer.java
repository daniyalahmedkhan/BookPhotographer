package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kashif.bookphotographer.R;

/**
 * Created by Kashif on 12/14/2017.
 */

public class Custom_SearchPhotographer extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final String[] gridViewString2;
    private final int[] gridViewImageId;


    public Custom_SearchPhotographer(Context context , String[] gridViewString , String[] gridViewString2 , int[] gridViewImageId){



        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
        this.gridViewString2 = gridViewString2;

    }

    @Override
    public int getCount() {
        return gridViewString.length;
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


        View gridView;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.custom_search_photographer, null);
            TextView textView1 = (TextView) gridView.findViewById(R.id.Txt1);
            TextView textView2 = (TextView) gridView.findViewById(R.id.Txt2);
            ImageView imageView1 = (ImageView) gridView.findViewById(R.id.Img1);
            textView1.setText(gridViewString[i]);
            textView2.setText(gridViewString2[i]);
            imageView1.setImageResource(gridViewImageId[i]);
        } else {
            gridView = (View) view;
        }

        return gridView;
    }
}
