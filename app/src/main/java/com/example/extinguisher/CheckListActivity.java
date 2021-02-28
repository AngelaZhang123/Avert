package com.example.extinguisher;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CheckListActivity extends AppCompatActivity {

    private CheckBox item1, item2, item3, item4, item5, item6, item7, item8;
    private int points;

    SharedPreferences userData;
    private PreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        TextView point = (TextView)findViewById(R.id.points);

        manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());

        userData = getSharedPreferences("data", 0);
        points = manager.getListPoints();
        point.setText("Points: "+points);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.kit_1);
        checkBox1.setChecked(userData.getBoolean("checked1",false));

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.kit_2);
        checkBox2.setChecked(userData.getBoolean("checked2",false));

        CheckBox checkBox3 = (CheckBox) findViewById(R.id.kit_3);
        checkBox3.setChecked(userData.getBoolean("checked3",false));

        CheckBox checkBox4 = (CheckBox) findViewById(R.id.kit_4);
        checkBox4.setChecked(userData.getBoolean("checked4",false));

        CheckBox checkBox5 = (CheckBox) findViewById(R.id.kit_5);
        checkBox5.setChecked(userData.getBoolean("checked5",false));

        CheckBox checkBox6 = (CheckBox) findViewById(R.id.kit_6);
        checkBox6.setChecked(userData.getBoolean("checked6",false));

        CheckBox checkBox7 = (CheckBox) findViewById(R.id.kit_7);
        checkBox7.setChecked(userData.getBoolean("checked7",false));

        CheckBox checkBox8 = (CheckBox) findViewById(R.id.kit_8);
        checkBox8.setChecked(userData.getBoolean("checked8",false));
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        SharedPreferences.Editor editor = userData.edit();

        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.kit_1:
                if (checked) {
                    points++;
                    editor.putBoolean("checked1", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked1",false)) {
                    points--;
                    editor.putBoolean("checked1", false);
                }
                break;
            case R.id.kit_2:
                if (checked) {
                    points++;
                    editor.putBoolean("checked2", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked2",false)) {
                    points--;
                    editor.putBoolean("checked2", false);
                }
                break;
            case R.id.kit_3:
                if (checked) {
                    points++;
                    editor.putBoolean("checked3", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked3",false)) {
                    points--;
                    editor.putBoolean("checked3", false);
                }
                break;
            case R.id.kit_4:
                if (checked) {
                    points++;
                    editor.putBoolean("checked4", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked4",false)) {
                    points--;
                    editor.putBoolean("checked4", false);
                }
                break;
            case R.id.kit_5:
                if (checked) {
                    points++;
                    editor.putBoolean("checked5", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked5",false)) {
                    points--;
                    editor.putBoolean("checked5", false);
                }
                break;
            case R.id.kit_6:
                if (checked) {
                    points++;
                    editor.putBoolean("checked6", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked6",false)) {
                    points--;
                    editor.putBoolean("checked6", false);
                }
                break;
            case R.id.kit_7:
                if (checked) {
                    points++;
                    editor.putBoolean("checked7", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked7",false)) {
                    points--;
                    editor.putBoolean("checked7", false);
                }
                break;
            case R.id.kit_8:
                if (checked) {
                    points++;
                    editor.putBoolean("checked8", true);
                    Toast.makeText(getApplicationContext(), "Good work!", Toast.LENGTH_SHORT).show();
                } else if (userData.getBoolean("checked8",false)) {
                    points--;
                    editor.putBoolean("checked8", false);
                }
                break;
        }
        TextView point = (TextView)findViewById(R.id.points);
        point.setText("Points:"+points);
        editor.commit();
        manager.setListPoints(points);
    }
}