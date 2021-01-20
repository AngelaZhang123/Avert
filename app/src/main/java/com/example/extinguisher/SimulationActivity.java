package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimulationActivity extends AppCompatActivity {
    private Button mFireButton, mQuakeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        mFireButton = (Button) findViewById(R.id.fire_button);
        mFireButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulationActivity.this, WildfireActivity.class);
                startActivity(intent);
            }
        });

        mQuakeButton = (Button) findViewById(R.id.quake_button);
        mQuakeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulationActivity.this, EarthquakeActivity.class);
                startActivity(intent);
            }
        });
    }
}