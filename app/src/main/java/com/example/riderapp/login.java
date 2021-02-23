package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class login extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView register;
    String susername,spassword;
    SharedPreferences sf;
    String login_id;
    String login_type;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.name);
        password=findViewById(R.id.password);
        login=findViewById(R.id.button);
        register=findViewById(R.id.textView);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        login_id=sp.getString("login_type", "");
        login_type=sp.getString("login_type","");
        if (login_type.equals("1"))
        {
            Intent intent=new Intent(login.this,Home.class);
            startActivity(intent);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                susername=username.getText().toString().trim();
                spassword=password.getText().toString().trim();
                if (username.equals(""))
                {
                    Toast.makeText(login.this, "Username Require", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(""))
                {
                    Toast.makeText(login.this, "Password Require", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String base_url=getResources().getString(R.string.base_url);
                    String url=base_url+"login.php";

                    Log.e("url",url);

                    HashMap<String,String> params=new HashMap<>();
                    params.put("username",susername);
                    params.put("password",spassword);

                    JSONObject parameter=new JSONObject(params);

                    Log.e("par", String.valueOf(parameter));

                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, parameter, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.e("res", String.valueOf(response));
                            int success = 0;
                            try {
                                success = response.getInt("success");

                                if (success == 1) {
                                    Toast.makeText(login.this, "success", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(login.this, Home.class);
                                    startActivity(intent);
                                    finish();

                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("login_id", response.getString("Login_id"));
                                    editor.putString("login_type", response.getString("Role"));
                                    editor.commit();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

                }
            }

        });

    }
}
