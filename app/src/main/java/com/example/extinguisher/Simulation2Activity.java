package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
public class Simulation2Activity extends AppCompatActivity {
    private Button backButtonL2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation2);

        backButtonL2 = (Button) findViewById(R.id.backButton);
        backButtonL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulation2Activity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }
}