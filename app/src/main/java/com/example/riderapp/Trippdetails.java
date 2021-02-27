package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;


public class Trippdetails extends AppCompatActivity {
    ImageButton location,logout,call;
    TextView mileage,stay,fooddetails,discription;
    SharedPreferences sp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trippdetails);

        sp=getSharedPreferences("user_login",MODE_PRIVATE);

        location=findViewById(R.id.location);
        logout=findViewById(R.id.logout);
        call=findViewById(R.id.call);


        mileage=findViewById(R.id.textView3);
        stay=findViewById(R.id.textView4);
        fooddetails=findViewById(R.id.textView5);
        discription=findViewById(R.id.textView6);


        String s=getIntent().getStringExtra("bikemileage");
        mileage.setText(s);

        String s1=getIntent().getStringExtra("stay");
        stay.setText(s1);

        String s2=getIntent().getStringExtra("food");
        mileage.setText(s2);

        String s3=getIntent().getStringExtra("discription");
        mileage.setText(s3);



        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:10.850516,76.271080"));
                Intent chooser= Intent.createChooser(intent,"Lauch map");
                startActivity(chooser);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                Intent intent=new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:00000000"));
                Toast.makeText(Trippdetails.this, "calling caption", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}