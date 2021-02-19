package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EarthquakeActivity extends AppCompatActivity {
    private Button L1Button, L2Button;
    private PreferenceManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        mManager = PreferenceManager.getInstance();
        mManager.initialize(getApplicationContext());

        L1Button = (Button) findViewById(R.id.level1Button);
        L1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel1Activity.class);
                startActivity(intent);
            }
        });

        L2Button = (Button) findViewById(R.id.level2Button);
        L2Button.setEnabled(mManager.getComplete(1));
        L2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel2Activity.class);
                startActivity(intent);
            }
        });
    }
}