package com.example.kashif.bookphotographer.Activities.CustomerFlow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kashif.bookphotographer.R;

/**
 * Created by Kashif on 1/22/2018.
 */

public class Booking_Fragment extends android.support.v4.app.Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.booking_fragment , null);
        return view;
    }
}
