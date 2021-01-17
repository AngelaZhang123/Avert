package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class LevelActivity extends AppCompatActivity {
    private Button level1Button, level2Button, level3Button, level4Button, backButtonL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        level1Button = (Button) findViewById(R.id.level1);
        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, Simulation1.class);
                startActivity(intent);
            }
        });

        level2Button = (Button) findViewById(R.id.level2);
        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, Simulation2Activity.class);
                startActivity(intent);
            }
        });

        level3Button = (Button) findViewById(R.id.level3);
        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, Simulation3Activity.class);
                startActivity(intent);
            }
        });

        level4Button = (Button) findViewById(R.id.level4);
        level4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, Simulation4Activity.class);
                startActivity(intent);
            }
        });

        backButtonL = (Button) findViewById(R.id.backButton);
        backButtonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}