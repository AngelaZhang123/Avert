package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class WildfireActivity extends AppCompatActivity {

    private ImageButton mCampButton, mHouseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wildfire);
        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());

        mCampButton = (ImageButton) findViewById(R.id.campfire_button);
        mCampButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, CampfireActivity.class);
                startActivity(intent);
            }
        });

        mHouseButton = (ImageButton) findViewById(R.id.house_button);
        mHouseButton.setEnabled(manager.getComplete(0));
        mHouseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, HouseActivity.class);
                startActivity(intent);

            }
        });
    }
}