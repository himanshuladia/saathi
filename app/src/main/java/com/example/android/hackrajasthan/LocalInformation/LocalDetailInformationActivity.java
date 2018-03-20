package com.example.android.hackrajasthan.LocalInformation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView name;
    private TextView aboutMe;
    private TextView interested;
    private TextView language;
    private TextView differently;
    private TextView itinerary;
    private TextView email;
    private TextView gender;
    private TextView rate;
    private TextView rating;
    private String mCurrentCity;
    private String mCurrentuid;
    private ProgressDialog progressDialog;
    private DatabaseReference mCityLocalDatabase;
    private HashMap<String,String> mNameUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detail_information);

        imageView=(ImageView)findViewById(R.id.profile);
        rate=(TextView)findViewById(R.id.rate);
        name=(TextView)findViewById(R.id.local_name);
        aboutMe=(TextView)findViewById(R.id.local_about_me);
        interested=(TextView)findViewById(R.id.local_interested);
        language=(TextView)findViewById(R.id.local_language);
        differently=(TextView)findViewById(R.id.local_differently);
        itinerary=(TextView)findViewById(R.id.local_itinerary);
        email=(TextView)findViewById(R.id.local_email);
        gender=(TextView)findViewById(R.id.local_gender);
        rating=(TextView)findViewById(R.id.local_rating);
        progressDialog=new ProgressDialog(this);
        mCityLocalDatabase= FirebaseDatabase.getInstance().getReference().child("City").child(mCurrentuid).child("Local");
        mNameUid=new HashMap<>();
        Intent intent=getIntent();
        mCurrentCity=intent.getStringExtra("currentCity");
        mCurrentuid=intent.getStringExtra("currentCityUid");

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

                    String currentUiD=citySnapshot.getKey().toString();
                    String nameLocal= citySnapshot.child("Name").getValue().toString();
                    mNameUid.put(nameLocal,currentUiD);
                    String aboutMe=citySnapshot.child("About").getValue().toString();
                    String ImageUrl=citySnapshot.child("ImageUrl").getValue().toString();
                    City mTemporaryCity=new City(nameLocal,aboutMe,ImageUrl);


                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
