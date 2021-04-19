package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LocationsActivity extends AppCompatActivity {

    private EditText spot1, spot2, spot3;
    private Button editButton;
    private TextView spotT1, spotT2, spotT3;
    private boolean clicked = false;
    private ImageButton homeB;
    SharedPreferences locations;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        locations = getSharedPreferences("locations", Context.MODE_PRIVATE);
        editor = this.locations.edit();
        editButton = (Button) findViewById(R.id.editB);
        spot1 = (EditText) findViewById(R.id.spot1);
        spot2 = (EditText) findViewById(R.id.spot2);
        spot3 = (EditText) findViewById(R.id.spot3);
        spotT1 = (TextView)findViewById(R.id.spotT1);
        spotT2 = (TextView)findViewById(R.id.spotT2);
        spotT3 = (TextView)findViewById(R.id.spotT3);
        homeB = (ImageButton) findViewById(R.id.logoL);

        setData();
        changeEdit(1);

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked==false) {
                    editButton.setText("Done");
                    changeEdit(0);
                }
                else {
                    editButton.setText("Edit");
                    changeEdit(1);
                    setData();
                }
                clicked=!clicked;
            }
        });
    }

    public void changeEdit(int num)
    {
        if(num==1) {
            spot1.setEnabled(false);
            spot1.setVisibility(View.INVISIBLE);
            spot2.setEnabled(false);
            spot2.setVisibility(View.INVISIBLE);
            spot3.setEnabled(false);
            spot3.setVisibility(View.INVISIBLE);
            spotT1.setVisibility(View.VISIBLE);
            spotT2.setVisibility(View.VISIBLE);
            spotT3.setVisibility(View.VISIBLE);
        }
        else{
            spotT1.setVisibility(View.INVISIBLE);
            spotT2.setVisibility(View.INVISIBLE);
            spotT3.setVisibility(View.INVISIBLE);
            spot1.setEnabled(true);
            spot1.setVisibility(View.VISIBLE);
            spot2.setEnabled(true);
            spot2.setVisibility(View.VISIBLE);
            spot3.setEnabled(true);
            spot3.setVisibility(View.VISIBLE);
        }
    }

    public void setData()
    {
        setSpot(1, spot1, spotT1);
        setSpot(2, spot2, spotT2);
        setSpot(3, spot3, spotT3);
    }

    public void setSpot(int num, EditText spot, TextView spotT){
        String location = locations.getString("spot"+num, "");
        String newLocation = spot.getText().toString();
        boolean notSame = true;
        if(newLocation.equals(location) || location.equals("") || newLocation.equals(""))
            notSame = false;

        if(location.equals("") && newLocation.equals("")) {
            spotT.setText("Add Location");
        }
        else if (location.equals("")|| notSame) {
            editor.putString("spot"+num, newLocation);
            spotT.setText(newLocation);
            editor.apply();
        }
        else{
            editor.putString("spot"+num, location);
            editor.apply();
            spotT.setText(location);
        }
        location = locations.getString("spot"+num, "");
        spot.setText(location);
        changeEdit(1);
    }
}