package com.example.riderapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class postview extends AppCompatActivity {
    private ImageButton home,post,chat,camarabtn;
    private EditText discription,mileage,stay,food;
    private Button submit;
    private Uri mImageUri;
    private TextView date;
    String str_date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    String sdiscription,smilage,sstay,sfood;
    SharedPreferences sp;
    String login_id,login_type;

    Bitmap bitmap;
    String encodedImage;


    private static final int GALLERY_REQUEST = 1;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postview);

        date= (TextView) findViewById(R.id.textView8);

        sp=getSharedPreferences("login",MODE_PRIVATE);
        login_id=sp.getString("login_id","");
        login_type=sp.getString("login_type","");

        home=findViewById(R.id.imageButton1);
        post=findViewById(R.id.imageButton2);
        camarabtn=findViewById(R.id.imageButton);
        discription=findViewById(R.id.editTextTextPersonName);
        mileage=findViewById(R.id.editTextTextPersonName1);
        stay=findViewById(R.id.editTextTextPersonName3);
        food=findViewById(R.id.editTextTextPersonName2);
        submit=findViewById(R.id.button2);



        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        postview.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                str_date = month + "/" + day + "/" + year;
                date.setText(str_date);
            }
        };

        camarabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadPost();
                Toast.makeText(postview.this, "successfully", Toast.LENGTH_SHORT).show();
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(postview.this,Home.class);
                startActivity(intent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(postview.this,postview.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            camarabtn.setImageURI(mImageUri);
            InputStream inputStream= null;
            try {
                inputStream = getContentResolver().openInputStream(mImageUri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imagestore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    private void imagestore(Bitmap bitmap)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes= stream.toByteArray();
        encodedImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Toast.makeText(this, encodedImage, Toast.LENGTH_SHORT).show();
        Log.e("img",encodedImage);
    }

    private void UploadPost()
    {

        sp=getSharedPreferences("login",MODE_PRIVATE);
        login_id=sp.getString("login_id","");
        login_type=sp.getString("login_type","");

        sdiscription=discription.getText().toString();
        smilage= mileage.getText().toString();
        sstay= stay.getText().toString();
        sfood=food.getText().toString();


        String base_url=getResources().getString(R.string.base_url);
        String url=base_url+"postview.php";

        HashMap<String,String> params=new HashMap<>();
        params.put("user",login_id);
        params.put("discription",sdiscription);
        params.put("mileage",smilage);
        params.put("stay",sstay);
        params.put("food",sfood);
        params.put("image",encodedImage);
        params.put("date",str_date);
        Toast.makeText(this, encodedImage, Toast.LENGTH_SHORT).show();

        JSONObject parameter=new JSONObject(params);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, parameter, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    int success = response.getInt("success");

                    if(success==1) {

                        Log.e("MESSAGE","SUCCESS");

                        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Not Added", Toast.LENGTH_SHORT).show();
                        Log.e("MESSAGE","Failed");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.e("Exception","SUCCESS");

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.getMessage());
                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}