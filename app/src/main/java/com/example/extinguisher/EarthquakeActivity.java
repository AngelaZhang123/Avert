package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
public class EarthquakeActivity extends AppCompatActivity {
    private Button L1Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        L1Button = (Button) findViewById(R.id.level1Button);
        L1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarthquakeActivity.this, EQLevel1Activity.class);
                startActivity(intent);
            }
        });
    }
}