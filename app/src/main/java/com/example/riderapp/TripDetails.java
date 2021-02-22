package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class TripDetails extends AppCompatActivity {
    TabLayout tabLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetails);
        tabLayout1=findViewById(R.id.tablayout);
        tabLayout1.getTabAt(0).setIcon(R.mipmap.home1);

    }
}