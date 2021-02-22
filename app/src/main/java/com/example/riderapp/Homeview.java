package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Homeview extends AppCompatActivity {
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeview);
        tabLayout=findViewById(R.id.tablayout);
        tabLayout.getTabAt(0).setIcon(R.mipmap.home1);
    }
}