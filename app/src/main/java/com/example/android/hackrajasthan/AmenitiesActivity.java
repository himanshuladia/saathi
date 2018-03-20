package com.example.android.hackrajasthan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.hackrajasthan.UserCity.LocalUser;

import java.util.ArrayList;
import java.util.List;

public class AmenitiesActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private Spinner mSpinnerDisable;
    private Spinner mSpinnerLanguage;
    private EditText editText;
    private Button sumbitBtn;
    private String mCurrentCity;
    private String mCurrentuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities);
        mToolBar=(Toolbar)findViewById(R.id.tool_bar_amenities);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Amenities");
        editText=(EditText)findViewById(R.id.budget);
        mSpinnerDisable=(Spinner)findViewById(R.id.spinner_disable);
        mSpinnerLanguage=(Spinner)findViewById(R.id.spinner_language);
        Intent intent=getIntent();
        mCurrentCity=intent.getStringExtra("currentCity");
        mCurrentuid=intent.getStringExtra("currentUserid");
        // Spinner click listener
        mSpinnerDisable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int poistion, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner Drop down elements
        List<String> disable = new ArrayList<String>();
        disable.add("Deaf");
        disable.add("Dump");
        disable.add("Physcial Disabled");
        disable.add("None");

        // Creating adapter for spinner
        ArrayAdapter<String> dataDisableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, disable);

        // Drop down layout style - list view with radio button
        dataDisableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinnerDisable.setAdapter(dataDisableAdapter);

        // Spinner Drop down elements
        List<String> language = new ArrayList<String>();
        language.add("English");
        language.add("Hindi");


        // Creating adapter for spinner
        ArrayAdapter<String> dataLanguageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, language);

        // Drop down layout style - list view with radio button
        dataLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinnerLanguage.setAdapter(dataLanguageAdapter);

        sumbitBtn=(Button)findViewById(R.id.submit_btn);
        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AmenitiesActivity.this, LocalUser.class);
                intent.putExtra("currentCity",mCurrentCity);
                intent.putExtra("currentUserid",mCurrentuid);
                startActivity(intent);
                finish();


            }
        });

    }
}
