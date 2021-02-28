package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView pointsText, levelsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        int points = manager.getTotalPoints();
        int levels = manager.getNumComplete();

        pointsText = (TextView)findViewById(R.id.profile_points);
        pointsText.setText(pointsText.getText() + " " + points);
        levelsText = (TextView)findViewById(R.id.profile_levels);
        levelsText.setText("" + levelsText.getText() + " " + levels);
    }
}