package com.example.android.hackrajasthan.CityPart;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.hackrajasthan.R;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    private Activity context;
    private ArrayList<City> allCity;
    public CityAdapter(@NonNull Activity context, ArrayList<City> allCity) {
        super(context, R.layout.city_list_layout,allCity);
        this.context=context;
        this.allCity=allCity;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater=context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.city_list_layout,null,true);

        //
        TextView nameCity=(TextView)listItemView.findViewById(R.id.city_name_text);
        TextView aboutMe=(TextView)listItemView.findViewById(R.id.about_me_text);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.city_image);

        City currentCity=allCity.get(position);
        nameCity.setText(currentCity.getCityName());
        aboutMe.setText(currentCity.getCityAboutMe());
        String imageUrl=currentCity.getImageUrl();
        Glide.with(context).load(imageUrl).into(imageView);

        return listItemView;
    }
}
