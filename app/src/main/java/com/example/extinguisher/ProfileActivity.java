package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private Button contactsB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        contactsB = (Button) findViewById(R.id.contactsButton);

        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        int points = manager.getTotalPoints();
        int levels = manager.getNumComplete();

        TextView pointsText = (TextView) findViewById(R.id.profile_points);
        pointsText.setText(pointsText.getText() + " " + points);
        TextView levelsText = (TextView) findViewById(R.id.profile_levels);
        levelsText.setText("" + levelsText.getText() + " " + levels);

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}