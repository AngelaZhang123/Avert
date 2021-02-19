package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WildfireActivity extends AppCompatActivity {

    private Button mCampButton, mHouseButton;
    private PreferenceManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wildfire);
        mManager = PreferenceManager.getInstance();
        mManager.initialize(getApplicationContext());

        mCampButton = (Button) findViewById(R.id.campfire_button);
        mCampButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, CampfireActivity.class);
                startActivity(intent);
            }
        });

        mHouseButton = (Button) findViewById(R.id.house_button);
        mHouseButton.setEnabled(mManager.getComplete(0));
        mHouseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, HouseActivity.class);
                startActivity(intent);

            }
        });
    }
}