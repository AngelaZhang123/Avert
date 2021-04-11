package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ChecklistSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_settings);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.35));

        final Button doneButton = (Button) findViewById(R.id.done_settings);
        final SwitchCompat notifSwitch = findViewById(R.id.notif_switch);
        final AppCompatSpinner spinner = findViewById(R.id.often_menu);
        final EditText editText = findViewById(R.id.enter_num);

        notifSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notifSwitch.isChecked()) {
                    doneButton.setEnabled(true);
                    spinner.setEnabled(true);
                    editText.setEnabled(true);
                }
                else {
                    doneButton.setEnabled(false);
                    spinner.setEnabled(false);
                    editText.setEnabled(false);
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChecklistSettingsActivity.this, CheckListActivity.class);
                intent.putExtra("notifs on", notifSwitch.isChecked());
                intent.putExtra("number times", editText.getText());
                intent.putExtra("frequency", spinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}