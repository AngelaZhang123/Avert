package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EarthquakeActivity extends AppCompatActivity {
    private ImageButton L1Button, L2Button;
    private PreferenceManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        mManager = PreferenceManager.getInstance();
        mManager.initialize(getApplicationContext());

        L1Button = (ImageButton) findViewById(R.id.level1Button);
        L1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel1Activity.class);
                startActivity(intent);
            }
        });

        L2Button = (ImageButton) findViewById(R.id.level2Button);
        L2Button.setEnabled(mManager.getComplete(2));
        L2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel2Activity.class);
                startActivity(intent);
            }
        });
    }
}