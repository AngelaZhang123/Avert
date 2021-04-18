package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyInfoActivity extends AppCompatActivity {

    private Button contactsButton, locationsButton, medsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_info);
        contactsButton = (Button) findViewById(R.id.contactsButton);
        locationsButton = (Button) findViewById(R.id.locationsButton);
        medsButton = (Button) findViewById(R.id.medsButton);

        contactsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyInfoActivity.this, EmergencyContactsActivity.class);
                startActivity(intent);
            }
        });
        locationsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyInfoActivity.this, LocationsActivity.class);
                startActivity(intent);
            }
        });
        medsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyInfoActivity.this, MedsActivity.class);
                startActivity(intent);
            }
        });
    }
}