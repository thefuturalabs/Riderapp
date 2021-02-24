package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageButton home,post,chat;

    SharedPreferences sf;
    String login_id;
    String login_type;

    ListView list;

    adapter adapter1;
    model m;
    public static ArrayList<model> model1ArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home=findViewById(R.id.imageButton1);
        post=findViewById(R.id.imageButton2);

        list=findViewById(R.id.list);
        adapter1=new adapter(this,model1ArrayList);

        getShops();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Home.class);
                startActivity(intent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,postview.class);
                startActivity(intent);

            }

        });

    }

    private void getShops() {

        String BASE_URL="http://192.168.29.149/ride_php/";
        String GET_SHOPS_URL=BASE_URL+"viewdetails.php";

        JsonArrayRequest arrayRequest=new JsonArrayRequest(GET_SHOPS_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                model1ArrayList.clear();
                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject json = response.getJSONObject(i);
                        String postid = json.getString("post_id");
                        String loginid=json.getString("Login_id");
                        String discription = json.getString("Discription");
                        String bikemileage = json.getString("Bikemileage");
                        String food = json.getString("Food");
                        String Date = json.getString("date");
                        String img = json.getString("Post_img");
                        String stay = json.getString("Stay");

                        //Toast.makeText(CustomerViewShopsActivity.this, reg_id, Toast.LENGTH_SHORT).show();

                        //Toast.makeText(CustomerViewShopsActivity.this, login_idd, Toast.LENGTH_SHORT).show();

                        m=new model(discription,bikemileage,stay,food,Date,img,postid,login_id);
                        model1ArrayList.add(m);
                        adapter1.notifyDataSetChanged();
                        list.setAdapter(adapter1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(arrayRequest);
    }
}