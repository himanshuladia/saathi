package com.example.android.hackrajasthan.LocalInformation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.hackrajasthan.CityPart.City;
import com.example.android.hackrajasthan.CityPart.CityAdapter;
import com.example.android.hackrajasthan.MainActivity;
import com.example.android.hackrajasthan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LocalDetailInformationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameView;
    private TextView aboutMeView;
    private TextView interestedView;
    private TextView languageView;
    private TextView differentlyView;
    private TextView itineraryView;
    private TextView emailView;
    private TextView genderView;
    private TextView rateView;
    private TextView ratingView;
    private String mCurrentCity;
    private String mCurrentuid;
    private ProgressDialog progressDialog;
    private DatabaseReference mCityLocalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detail_information);

        imageView=(ImageView)findViewById(R.id.profile);
        rateView=(TextView)findViewById(R.id.rate);
        nameView=(TextView)findViewById(R.id.local_name);
        aboutMeView=(TextView)findViewById(R.id.local_about_me);
        interestedView=(TextView)findViewById(R.id.local_interested);
        languageView=(TextView)findViewById(R.id.local_language);
        differentlyView=(TextView)findViewById(R.id.local_differently);
        itineraryView=(TextView)findViewById(R.id.local_itinerary);
        emailView=(TextView)findViewById(R.id.local_email);
        genderView=(TextView)findViewById(R.id.local_gender);
        ratingView=(TextView)findViewById(R.id.local_rating);
        progressDialog=new ProgressDialog(this);
        Intent intent=getIntent();
        mCurrentCity=intent.getStringExtra("currentCity");
        mCurrentuid=intent.getStringExtra("currentCityUid");
        mCityLocalDatabase= FirebaseDatabase.getInstance().getReference().child("City").child(mCurrentuid).child("Local");

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.village_progress_dialog));
        progressDialog.show();
        mCityLocalDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot citySnapshot: dataSnapshot.getChildren())
                {


                    String about=citySnapshot.child("About").getValue().toString();
                    String ImageUrl=citySnapshot.child("ImageUrl").getValue().toString();
                    String available=citySnapshot.child("AvailableForDifferentlyAbled").getValue().toString();
                    String email=citySnapshot.child("EmailID").getValue().toString();
                    String gender=citySnapshot.child("Gender").getValue().toString();
                    String interest=citySnapshot.child("Interests").getValue().toString();
                    String language=citySnapshot.child("Language").getValue().toString();
                    String Name=citySnapshot.child("Name").getValue().toString();
                    String PhoneNumber=citySnapshot.child("PhoneNumber").getValue().toString();
                    String Rate=citySnapshot.child("Rate").getValue().toString();
                    String itinerary=citySnapshot.child("itinerary").getValue().toString();

                    rateView.setText(Rate);
                    nameView.setText(Name);
                    aboutMeView.setText(about);
                    interestedView.setText(interest);
                    languageView.setText(language);
                    differentlyView.setText(available);
                    itineraryView.setText(itinerary);
                    genderView.setText(gender);
                    emailView.setText(email);

                    Glide.with(getApplicationContext()).load(ImageUrl).into(imageView);
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
