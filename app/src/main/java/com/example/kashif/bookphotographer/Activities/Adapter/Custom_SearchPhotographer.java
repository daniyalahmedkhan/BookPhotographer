package com.example.kashif.bookphotographer.Activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.R;

/**
 * Created by Kashif on 12/14/2017.
 */

public class Custom_SearchPhotographer extends BaseAdapter {

    public Context mContext;
    public final String[] gridViewString;
    public final String[] gridViewString2;
    public final String[] gridViewImageId;
    public final String[] id;


    public Custom_SearchPhotographer(Context context , String[] gridViewString , String[] gridViewString2 , String[] gridViewImageId , String[] id){



        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
        this.gridViewString2 = gridViewString2;
        this.id = id;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {


        View gridView;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        ViewHodler obj;


        if (view == null) {

            obj = new ViewHodler();

            gridView = new View(mContext);
            view = inflater.inflate(R.layout.custom_search_photographer, null);
            obj.textView1 = (TextView) view.findViewById(R.id.Txt1);
            obj.textView2 = (TextView) view.findViewById(R.id.Txt2);
            obj.imageView1 = (ImageView) view.findViewById(R.id.Img1);
            obj.textView1.setText(gridViewString[i]);
            obj.textView2.setText(gridViewString2[i]);
           // obj.imageView1.setImageResource(gridViewImageId[i]);
            Glide.with(mContext).load(gridViewImageId[i]).into(obj.imageView1);

            view.setTag(obj);

        } else {
            //gridView = (View) view;
            obj = (ViewHodler)view.getTag();
        }




        return view;



    }



   public  static  class  ViewHodler{

        TextView textView1 , textView2;
        ImageView imageView1;



   }

}


