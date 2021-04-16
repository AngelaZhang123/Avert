package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SimulationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        ImageButton fireButton = (ImageButton) findViewById(R.id.wildfire_button);
        fireButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulationActivity.this, WildfireActivity.class);
                startActivity(intent);
            }
        });

        ImageButton quakeButton = (ImageButton) findViewById(R.id.earthquake_button);
        quakeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulationActivity.this, EarthquakeActivity.class);
                startActivity(intent);
            }
        });

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_simulations);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulationActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}