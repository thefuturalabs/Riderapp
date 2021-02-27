package com.example.riderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter extends ArrayAdapter<model> {

    Context context;
    List<model> arrayhomedesign;

    public adapter(@NonNull Context context,List<model> arrayhomedesign) {
        super(context,R.layout.homedesign,arrayhomedesign);

        this.context = context;
        this.arrayhomedesign = arrayhomedesign;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homedesign, null,true);

        Button join;

        ImageView image = view.findViewById(R.id.image);
        TextView bikemileage = view.findViewById(R.id.Bikemileage);
        TextView stay = view.findViewById(R.id.stay);
        TextView date = view.findViewById(R.id.date);
        TextView food = view.findViewById(R.id.food);
        TextView discription = view.findViewById(R.id.sdiscription);
        TextView login_id=view.findViewById(R.id.login_id);
        TextView post_id=view.findViewById(R.id.post_id);
        join = view.findViewById(R.id.join);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Trippdetails.class);
                intent.putExtra("bikemileage", "Bikemileage");
                intent.putExtra("stay", "stay");
                intent.putExtra("food", "food");
                intent.putExtra("discription", "discription");
                context.startActivity(intent);
            }
        });

        discription.setText(arrayhomedesign.get(position).getDiscription());
        bikemileage.setText(arrayhomedesign.get(position).getBikemilage());
        stay.setText(arrayhomedesign.get(position).getStay());
        food.setText(arrayhomedesign.get(position).getFood());
        date.setText(arrayhomedesign.get(position).getDate());
        login_id.setText(arrayhomedesign.get(position).getLogin_id());
        post_id.setText(arrayhomedesign.get(position).getPost_id());
        Glide.with(view.getContext()).load(arrayhomedesign.get(position).getImage()).into(image);

        return view;

    }
}
