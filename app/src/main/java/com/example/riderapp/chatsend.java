package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class chatsend extends AppCompatActivity {
    EditText massage;
    Button send;
    SharedPreferences sp;
    String login_id,login_type;
    String smassage,sreplay,ssenddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatsend);

        massage=findViewById(R.id.editTextTextPersonName1);
        send=findViewById(R.id.button3);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sp=getSharedPreferences("login",MODE_PRIVATE);
                login_id=sp.getString("login_id","");
                login_type=sp.getString("login_type","");

                smassage=massage.getText().toString();

                String base_url=getResources().getString(R.string.base_url);
                String url=base_url+"chatsend.php";

                /*Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),chatpage.class);
                startActivity(intent);

*/
                if (smassage.equals("null"))
                {
                    Toast.makeText(chatsend.this, "error", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                         Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(getApplicationContext(),chatpage.class);
                         startActivity(intent);
                }

                HashMap<String,String> params=new HashMap<>();
                params.put("user",login_id);
                params.put("massage",smassage);

                JSONObject parameter=new JSONObject(params);

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, parameter, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            int success = response.getInt("success");

                            if(success==1) {

                                Log.e("MESSAGE","SUCCESS");

                                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),chatpage.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Not Added", Toast.LENGTH_SHORT).show();
                                /*Log.e("MESSAGE","Failed");*/

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            /*Log.e("Exception","SUCCESS");*/

                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                /*Log.e("VOLLEY", error.getMessage());*/
                            }
                        });

                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}