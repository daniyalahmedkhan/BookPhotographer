package com.example.kashif.bookphotographer.Activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kashif.bookphotographer.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class BookingActivity extends AppCompatActivity {


    EditText Ocassions, EditPck;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    public  static List<Date> dates;
    public String dat;

    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);


        setContentView(R.layout.activity_booking);

        EditPck = (EditText) findViewById(R.id.EditPck);


        Ocassions = (EditText) findViewById(R.id.Ocassions);

        Ocassions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Calender_DatePicker fragment1 = new Calender_DatePicker();
//                fragmentTransaction.replace(android.R.id.content, fragment1, "fragment");
//                fragmentTransaction.commit();

  //              showChangeLangDialog2();

            }
        });

        EditPck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showChangeLangDialog();
            }
        });

    }


    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.packgs, null);
        dialogBuilder.setView(dialogView);


        RadioGroup radioGroup;

        radioGroup = (RadioGroup) dialogView.findViewById(R.id.radioGroup);


        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                EditPck.setText(age);

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });


        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    public void showChangeLangDialog2() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.calender, null);
        dialogBuilder.setView(dialogView);


        final CalendarPickerView calendar_view = (CalendarPickerView)dialogView.findViewById(R.id.calendar_view);
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

                Toast.makeText(getApplicationContext(),"Selected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();

               // dat = date.toString();
            }

            @Override
            public void onDateUnselected(Date date) {

                Toast.makeText(getApplicationContext(),"UnSelected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();
            }
        });


         dat  = String.valueOf(calendar_view.getSelectedDates());


        //Displaying all selected dates while clicking on a button
        Button btn_show_dates = (Button)dialogView.findViewById(R.id.btn_show_dates);
        btn_show_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i< calendar_view.getSelectedDates().size();i++){

                    //here you can fetch all dates
                    Toast.makeText(getApplicationContext(),calendar_view.getSelectedDates().get(i).toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });







        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();


                Ocassions.setText(dat);


            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });


        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    public void onRadioButtonClicked(View v) {

        switch (v.getId()) {


            case R.id.Radio1:
                //Toast.makeText(BookingActivity.this , " You have selected Age 2-9" , Toast.LENGTH_SHORT).show();
                age = "Silver";
                break;

            case R.id.Radio2:
                // Toast.makeText(Question_1.this , " You have selected Age 10-12" , Toast.LENGTH_SHORT).show();
                age = "Golden";
                break;

            case R.id.Radio3:
                // Toast.makeText(Question_1.this , " You have selected Age 13-15" , Toast.LENGTH_SHORT).show();
                age = "Platinum";
                break;

            default:
                break;


        }


    }
}