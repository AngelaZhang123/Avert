package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class CheckListActivity extends AppCompatActivity {

    private CheckBox item1, item2, item3, item4, item5, item6, item7, item8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

    }
}