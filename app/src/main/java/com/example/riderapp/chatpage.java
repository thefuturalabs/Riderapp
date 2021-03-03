package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class chatpage extends AppCompatActivity {
    ImageButton home,post,chat;
    FloatingActionButton flot;

    SharedPreferences sf;

    String chat_id;
    String login_type;

    ListView list1;

    chatadapter adapter1;
    chatmodel m;
    public static ArrayList<chatmodel> modelArrayList=new ArrayList<chatmodel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);

       home=findViewById(R.id.home1);
       post=findViewById(R.id.post1);
       chat=findViewById(R.id.chat1);
       flot=findViewById(R.id.floatingActionButton);
       list1=findViewById(R.id.list1);

        adapter1=new chatadapter(this,modelArrayList);
        getShops();

       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(chatpage.this,Home.class);
               startActivity(intent);
           }
       });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chatpage.this,postview.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chatpage.this,Home.class);
                startActivity(intent);
            }
        });

        flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chatpage.this,chatsend.class);
                startActivity(intent);
            }
        });

           }

    private void getShops() {

        String BASE_URL="http://192.168.29.149/ride_php/";
        String GET_SHOPS_URL=BASE_URL+"chatdesignpage.php";

        JsonArrayRequest arrayRequest=new JsonArrayRequest(GET_SHOPS_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("res", String.valueOf(response));

                modelArrayList.clear();
                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject json = response.getJSONObject(i);
                        String chatid= json.getString("Chat_id");
                        String massage=json.getString("Massage");
                        String loginid = json.getString("Login_id");
                        String replay = json.getString("Replay");
                        String senddate = json.getString("Send_date");
                        String replaydate = json.getString("Replay_date");

                        //Toast.makeText(CustomerViewShopsActivity.this, reg_id, Toast.LENGTH_SHORT).show();

                        //Toast.makeText(CustomerViewShopsActivity.this, login_idd, Toast.LENGTH_SHORT).show();

                        m=new chatmodel(massage,loginid,replay,senddate,replaydate,chatid);
                        modelArrayList.add(m);
                        adapter1.notifyDataSetChanged();
                        list1.setAdapter(adapter1);
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