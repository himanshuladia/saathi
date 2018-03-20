package com.example.android.hackrajasthan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.hackrajasthan.CityPart.City;
import com.example.android.hackrajasthan.CityPart.CityAdapter;
import com.example.android.hackrajasthan.Intro.OnBoardingScreenActivity;
import com.example.android.hackrajasthan.Login.SignInActivity;
import com.example.android.hackrajasthan.Login.SignUpActivity;
import com.example.android.hackrajasthan.UserCity.Local;
import com.example.android.hackrajasthan.UserCity.LocalUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText searchCity;
    private ImageButton searchCityButton;
    private FirebaseUser mCurrentUser;
    private ListView cityDisplayView;
    private ArrayList<City> informationCity;
    private DatabaseReference mCityDatabase;
    private ProgressDialog progressDialog;
    private CityAdapter cityAdapter;
    private ArrayList<City> searchingResult;
    private  String mCurrentCityUser;
    private City mSearchCity;
    private android.support.v7.widget.Toolbar mToolBar;
    public HashMap<String,String> mcityFireBaseUiD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        //Check whether user is already login in or not
        if(mAuth.getCurrentUser()==null) {
            startActivity(new Intent(this, OnBoardingScreenActivity.class));
            finish();
        }
            mCityDatabase= FirebaseDatabase.getInstance().getReference().child("City");
        mcityFireBaseUiD=new HashMap<>();
        searchCity=(EditText)findViewById(R.id.city_user_search_field);
        searchCityButton=(ImageButton)findViewById(R.id.city_user_search_btn);
        cityDisplayView=(ListView) findViewById(R.id.city_user_result_list);
        searchingResult=new ArrayList<>();
        informationCity=new ArrayList<>();
        mToolBar=(android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar_city);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Saathi");
        progressDialog=(ProgressDialog)new ProgressDialog(this);



       /** searchCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchCity.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        String searchText=searchCity.getText().toString();

                        for(int i=0;i<informationCity.size();i++)
                        {
                            String cityName=informationCity.get(i).getCityName().toString();
                            searchingResult.clear();

                            if(cityName.contains(searchText))
                            {
                                searchingResult.add(informationCity.get(i));
                                //mSearchCity=new City(informationCity.get(i).cityName,informationCity.get(i).cityAboutMe,informationCity.get(i).imageUrl);

                            }
                            cityAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                        cityAdapter=new CityAdapter(MainActivity.this,searchingResult);
                        cityDisplayView.setAdapter(cityAdapter);
                    }
                });
            }
        });**/

        cityDisplayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                City mCurrentCity=(City)adapterView.getItemAtPosition(position);



                mCurrentCityUser=mCurrentCity.getCityName();
                Intent intent=new Intent(MainActivity.this,AmenitiesActivity.class);
                String user_id=mcityFireBaseUiD.get(mCurrentCityUser);
                        intent.putExtra("currentUserid", user_id);
                        intent.putExtra("currentCity",mCurrentCityUser);
                        startActivity(intent);


                }

        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.village_progress_dialog));
        progressDialog.show();
        mCityDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                informationCity.clear();
                for(DataSnapshot citySnapshot: dataSnapshot.getChildren())
                {

                    String currentUiD=citySnapshot.getKey().toString();
                    String nameCity= citySnapshot.child("Name").getValue().toString();
                    mcityFireBaseUiD.put(nameCity,currentUiD);
                    String aboutMe=citySnapshot.child("About").getValue().toString();
                    String ImageUrl=citySnapshot.child("ImageUrl").getValue().toString();
                    City mTemporaryCity=new City(nameCity,aboutMe,ImageUrl);
                    informationCity.add(mTemporaryCity);

                }
                progressDialog.dismiss();
                cityAdapter =new CityAdapter(MainActivity.this,informationCity);
                cityDisplayView.setAdapter(cityAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.citysetting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.log_out_menu)
        {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,SignInActivity.class));
            finish();
        }
        if(item.getItemId()==R.id.settings_menu)
        {
           // startActivity(new Intent(VillageActivity.this, UserInfoActivity.class));

        }
        return true;

    }
   public String getCurrentCity()
   {
       return mCurrentCityUser;
   }

}
