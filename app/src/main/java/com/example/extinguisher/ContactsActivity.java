package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactsActivity extends AppCompatActivity {
    private Button addButton;
    private EditText phone1, phone2, phone3, phone4;
    private int contactNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        addButton = (Button) findViewById(R.id.addButton);
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        phone4 = (EditText) findViewById(R.id.phone4);
        contactNum=0;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}