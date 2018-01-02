package com.example.kashif.bookphotographer.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kashif.bookphotographer.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kashif on 12/20/2017.
 */

public class Calender_DatePicker extends android.support.v4.app.Fragment {

    public  static  List<Date> dates;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.calender , null);

        final CalendarPickerView calendar_view = (CalendarPickerView)view.findViewById(R.id.calendar_view);
//getting current
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();

//add one year to calendar from todays date
        calendar_view.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE);



        //action while clicking on a date
        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                Toast.makeText(getActivity(),"Selected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDateUnselected(Date date) {

                Toast.makeText(getActivity(),"UnSelected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();
            }
        });



        //fetch dates
      dates  = calendar_view.getSelectedDates();


        //Displaying all selected dates while clicking on a button
        Button btn_show_dates = (Button)view.findViewById(R.id.btn_show_dates);
        btn_show_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i< calendar_view.getSelectedDates().size();i++){

                    //here you can fetch all dates
                    Toast.makeText(getActivity(),calendar_view.getSelectedDates().get(i).toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;

    }
}
