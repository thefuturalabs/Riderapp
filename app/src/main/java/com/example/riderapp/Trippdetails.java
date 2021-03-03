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
    TextView kilometer,stay,fooddetails,discription;
    SharedPreferences sp;

    String kilo,st,food,desc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trippdetails);

        sp=getSharedPreferences("login",MODE_PRIVATE);

        location=findViewById(R.id.location);
        logout=findViewById(R.id.logout);
        call=findViewById(R.id.call);


        kilometer=findViewById(R.id.kilometer);
        stay=findViewById(R.id.stay);
        fooddetails=findViewById(R.id.fooddetails);
        discription=findViewById(R.id.discription);

        Intent intent=getIntent();
        kilo=intent.getStringExtra("kilometer");
        st=intent.getStringExtra("stay");
        food=intent.getStringExtra("food");
        desc=intent.getStringExtra("discription");

        kilometer.setText(kilo);
        stay.setText(st);
        fooddetails.setText(food);
        discription.setText(desc);


       /* String s=getIntent().getStringExtra("kilometer");
        kilometer.setText(s);

        String s1=getIntent().getStringExtra("stay");
        stay.setText(s1);

        String s2=getIntent().getStringExtra("food");
        fooddetails.setText(s2);

        String s3=getIntent().getStringExtra("discription");
        discription.setText(s3);*/



        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:10.850516,76.271080"));
                Intent chooser= Intent.createChooser(intent,"Lauch map");
                Toast.makeText(Trippdetails.this, "Location Loading", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Trippdetails.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
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