package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(1*1000);


                    Intent i=new Intent(getBaseContext(), Login.class);
                    startActivity(i);


                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }
}