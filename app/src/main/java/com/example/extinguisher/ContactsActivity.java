package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactsActivity extends AppCompatActivity {
    private Button editButton, addButton;
    private EditText phone1, phone2, phone3, phone4;
    private boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_contacts);
        addButton = (Button) findViewById(R.id.addButton);
        editButton = (Button) findViewById(R.id.editButton);
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        phone4 = (EditText) findViewById(R.id.phone4);


        /*editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked==false) {
                    editButton.setText("Done");
                    changeEdit(0);
                }
                else {
                    editButton.setText("Edit");
                    changeEdit(1);
                }
                clicked=!clicked;
            }
        });*/

    }

    /*public void changeEdit(int num){
        if(num==1){
            phone1.setEnabled(false);
            phone2.setEnabled(false);
            phone3.setEnabled(false);
            phone4.setEnabled(false);
            phone1.setVisibility(View.INVISIBLE);
            phone2.setVisibility(View.INVISIBLE);
            phone3.setVisibility(View.INVISIBLE);
            phone4.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.INVISIBLE);
        }
        else{
            phone1.setVisibility(View.VISIBLE);
            phone2.setVisibility(View.VISIBLE);
            phone3.setVisibility(View.VISIBLE);
            phone4.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
            phone1.setEnabled(true);
            phone2.setEnabled(true);
            phone3.setEnabled(true);
            phone4.setEnabled(true);
        }
    }*/
}