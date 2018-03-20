package com.example.android.hackrajasthan.UserCity;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.hackrajasthan.CityPart.City;
import com.example.android.hackrajasthan.R;


import java.util.ArrayList;

public class LocalAdapter extends ArrayAdapter<Local> {
    private Activity context;
    private ArrayList<Local> cityUser;
    public LocalAdapter(@NonNull Activity context, ArrayList<Local> cityUser) {
        super(context,R.layout.city_user_list_layout,cityUser);
        this.context=context;
        this.cityUser=cityUser;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater=context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.city_user_list_layout,null,true);

        //
        TextView nameUser=(TextView)listItemView.findViewById(R.id.user_name_text);
        TextView aboutMe=(TextView)listItemView.findViewById(R.id.user_about_me_text);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.user_profile_image);

        Local currentLocalCity=cityUser.get(position);
        nameUser.setText(currentLocalCity.getName());
        aboutMe.setText(currentLocalCity.getAbout());
        String imageUrl=currentLocalCity.getImageUrl();
        Glide.with(context).load(imageUrl).into(imageView);

        return listItemView;
    }
}
