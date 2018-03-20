package com.example.android.hackrajasthan.UserCity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.hackrajasthan.LocalInformation.LocalDetailInformationActivity;
import com.example.android.hackrajasthan.MainActivity;
import com.example.android.hackrajasthan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LocalUser extends AppCompatActivity {
        private ListView mListView;
        private String mCurrentCity;
        private String mCurrentuid;
    private DatabaseReference mCityUserDatabase;
    private LocalAdapter localAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<Local> mCityUserList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_user);

        mListView=(ListView)findViewById(R.id.local_user_list);

        Intent intent=getIntent();
        mCurrentCity=intent.getStringExtra("currentCity");
        mCurrentuid=intent.getStringExtra("currentUserid");

        progressDialog=(ProgressDialog)new ProgressDialog(this);
        mCityUserDatabase= FirebaseDatabase.getInstance().getReference().child("City").child(mCurrentuid).child("Local");
        mCityUserList=new ArrayList<>();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(LocalUser.this, LocalDetailInformationActivity.class);
                String user_id=mCurrentuid;
                intent.putExtra("currentCityUid", user_id);
                intent.putExtra("currentCity",mCurrentCity);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.village_progress_dialog));
        progressDialog.show();
        mCityUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCityUserList.clear();
                for(DataSnapshot villageSnapshot: dataSnapshot.getChildren())
                {

                    String name=villageSnapshot.child("Name").getValue().toString();
                    String aboutMe=villageSnapshot.child("About").getValue().toString();
                    String ImageUrl=villageSnapshot.child("ImageUrl").getValue().toString();
                    Local mTemporaryUser=new Local(name,aboutMe,ImageUrl);
                    mCityUserList.add(mTemporaryUser);

                }
                progressDialog.dismiss();
                localAdapter =new LocalAdapter(LocalUser.this,mCityUserList);
                mListView.setAdapter(localAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
