package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Simulation3Activity extends AppCompatActivity {
    private Button backButtonL3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation3);
        backButtonL3 = (Button) findViewById(R.id.backButton);
        backButtonL3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Simulation3Activity.this, LevelActivity.class);
                    startActivity(intent);
                }
            });
        }
    }