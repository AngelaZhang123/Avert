package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class WildfireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wildfire);
        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());

        ImageButton campButton = (ImageButton) findViewById(R.id.campfire_button);
        campButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, CampfireActivity.class);
                startActivity(intent);
            }
        });

        ImageButton houseButton = (ImageButton) findViewById(R.id.house_button);
        TextView houseText = (TextView) findViewById(R.id.house_title);
        if(manager.getComplete(PreferenceManager.WILDFIRE1)) {
            houseButton.setEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                houseButton.setElevation(10);
            houseText.setText("House Fire Safety");
            houseText.setTextSize(14);
        }
        else {
            houseButton.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                houseButton.setElevation(0);
            houseText.setText(R.string.unlock);
            houseText.setTextSize(12);
        }

        houseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, HouseActivity.class);
                startActivity(intent);

            }
        });

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, SimulationActivity.class);
                startActivity(intent);
            }
        });

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_wildfire);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WildfireActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}