package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());

        ImageButton l1Button = (ImageButton) findViewById(R.id.level1Button);
        l1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel1Activity.class);
                startActivity(intent);
            }
        });

        ImageButton l2Button = (ImageButton) findViewById(R.id.level2Button);
        TextView l2Text = (TextView) findViewById(R.id.outside_title);
        if(manager.getComplete(PreferenceManager.EARTHQUAKE1)) {
            l2Button.setEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                l2Button.setElevation(10);
            l2Text.setText("Outside");
            l2Text.setTextSize(14);
        }
        else {
            l2Button.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                l2Button.setElevation(0);
            l2Text.setText(R.string.unlock);
            l2Text.setTextSize(12);
        }

        l2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel2Activity.class);
                startActivity(intent);
            }
        });

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, SimulationActivity.class);
                startActivity(intent);
            }
        });

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_earthquake);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}