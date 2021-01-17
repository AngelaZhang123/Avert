package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Simulation1 extends AppCompatActivity {
    private Button backButtonL1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation1);

        backButtonL1 = (Button) findViewById(R.id.backButton);
        backButtonL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulation1.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }
}