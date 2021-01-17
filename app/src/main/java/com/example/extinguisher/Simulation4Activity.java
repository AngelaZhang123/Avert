package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Simulation4Activity extends AppCompatActivity {
    private Button backButtonL4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation4);

        backButtonL4 = (Button) findViewById(R.id.backButton);
        backButtonL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulation4Activity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }
}