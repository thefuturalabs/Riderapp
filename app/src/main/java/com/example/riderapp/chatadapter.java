package com.example.riderapp;

import android.content.Context;
import android.util.Log;
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

public class chatadapter extends ArrayAdapter<chatmodel> {
    Context context;
    List<chatmodel> arraychatdesign;

    public chatadapter(@NonNull Context context,  List<chatmodel> Arraychatdesign) {
        super(context,R.layout.chatdesign,Arraychatdesign);

        this.context = context;
        this.arraychatdesign = Arraychatdesign;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatdesign, null,true);


        TextView loginid = view.findViewById(R.id.loginid);
        TextView chatid = view.findViewById(R.id.chatid);
        TextView massage = view.findViewById(R.id.massage);
        TextView replay = view.findViewById(R.id.replay);
        TextView senddate = view.findViewById(R.id.senddate);
        TextView replaydate = view.findViewById(R.id.replaydate);

        Log.e("MESSAGE VALUE", arraychatdesign.get(position).getMassage());
        loginid.setText(arraychatdesign.get(position).getLogin_id());
        chatid.setText(arraychatdesign.get(position).getChat_id());
        massage.setText(arraychatdesign.get(position).getMassage());
        replay.setText(arraychatdesign.get(position).getReplay());
        senddate.setText(arraychatdesign.get(position).getSend_date());
        replaydate.setText(arraychatdesign.get(position).getReplay_date());
        return view;

    }
}
