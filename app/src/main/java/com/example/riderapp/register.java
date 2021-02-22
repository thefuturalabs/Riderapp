package com.example.riderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.Map;

public class register extends AppCompatActivity {
    EditText name, address, mobile, email, password;
    TextView login;
    Button register;
    String sname, saddress, smobile, semail, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        address = findViewById(R.id.Address);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        login = findViewById(R.id.textView7);
        password = findViewById(R.id.password);
        register = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sname = name.getText().toString();
                saddress = address.getText().toString();
                smobile = mobile.getText().toString();
                semail = email.getText().toString();
                spassword = password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[0-9]{10}";

                if (sname.equals(""))
                {
                    Toast.makeText(register.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if (saddress.equals(""))
                {
                    Toast.makeText(register.this, "enter address", Toast.LENGTH_SHORT).show();
                }
                else if (!smobile.matches(mobilePattern))
                {
                    Toast.makeText(register.this, "enter mobile", Toast.LENGTH_SHORT).show();
                }
              else if (semail.matches(emailPattern))
              {
                   Toast.makeText(register.this, "enter email", Toast.LENGTH_SHORT).show();
               }
                else if (spassword.equals(""))
                {
                    Toast.makeText(register.this, "enter password", Toast.LENGTH_SHORT).show();
                }

                else {
                    String base_url = getResources().getString(R.string.base_url);
                    String url = base_url + "register.php";


                    Log.e("g",url);
                    Map<String, String> params = new HashMap<>();

                    params.put("name", sname);
                    params.put("address", saddress);
                    params.put("mobile", smobile);
                    params.put("email", semail);
                    params.put("password", spassword);

                    JSONObject parameter = new JSONObject(params);

                    Log.e("para", String.valueOf(parameter));

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameter, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.e("res", String.valueOf(response));
                            try {
                                int success = response.getInt("success");

                                if (success == 1) {
                                    Toast.makeText(register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(register.this, "Register feild", Toast.LENGTH_SHORT).show();
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
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonObjectRequest);

                }

            }

        });
    }
}