package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CheckListActivity extends AppCompatActivity {

    private CheckBox item1, item2, item3, item4, item5, item6, item7, item8;
    private boolean wasChecked1, wasChecked2, wasChecked3, wasChecked4, wasChecked5, wasChecked6, wasChecked7, wasChecked8;
    private int points; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        TextView point = (TextView)findViewById(R.id.points);
        point.setText("Points: "+points);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.kit_1:
                if (checked) {
                    points++;
                    wasChecked1 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked1) {
                    points--;
                    wasChecked1 = false;
                }
                break;
            case R.id.kit_2:
                if (checked) {
                    points++;
                    wasChecked2 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked2) {
                    points--;
                    wasChecked2 = false;
                }
                break;
            case R.id.kit_3:
                if (checked) {
                    points++;
                    wasChecked3 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked3) {
                    points--;
                    wasChecked3 = false;
                }
                break;
            case R.id.kit_4:
                if (checked) {
                    points++;
                    wasChecked4 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked4) {
                    points--;
                    wasChecked4 = false;
                }
                break;
            case R.id.kit_5:
                if (checked) {
                    points++;
                    wasChecked5 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked5) {
                    points--;
                    wasChecked5 = false;
                }
                break;
            case R.id.kit_6:
                if (checked) {
                    points++;
                    wasChecked6 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked6) {
                    points--;
                    wasChecked6 = false;
                }
                break;
            case R.id.kit_7:
                if (checked) {
                    points++;
                    wasChecked7 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked7) {
                    points--;
                    wasChecked7 = false;
                }
                break;
            case R.id.kit_8:
                if (checked) {
                    points++;
                    wasChecked8 = true;
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (wasChecked8) {
                    points--;
                    wasChecked8 = false;
                }
                break;
        }
        TextView point = (TextView)findViewById(R.id.points);
        point.setText("Points:"+points);
    }
}