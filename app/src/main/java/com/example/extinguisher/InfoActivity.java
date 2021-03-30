package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private String[] text = new String[]{
            "Keep the wound clean by washing with soap and cover with a bandage.",
            "Clean the skin around the wound with warm, soapy water. If you don’t have access to clean water, use alcohol wipes. Seek medical care immediately.",
            "Put ice on it and don’t move around. Compress it with a bandage and elevate your ankle to reduce swelling. Seek medical care if possible.",
            "Lie down with your head slightly elevated. Don’t move if unnecessary. Seek medical care.",
            "Immediately call an adult over and tell them to perform CPR. Seek medical care.",
            "Attract attention to yourself- shout, blow a whistle, or bang on a wall or pipe. Protect your mouth, nose, and eyes from dust.",
            "Wear a N95 respirator to filter out the ash.",
            "Call 9-11 and PG & E. Then leave the area immediately.",
            "Use flashlights and try to avoid lighting candles. If you have a power generator, be sure to use it outside.",
            "If food smells or looks weird, don’t eat it. When in doubt, throw it out. Listen to local reports to decide if tap water is safe to use."
    };

    private Button wound_button1, wound_button2, wound_button3, wound_button4, wound_button5;
    private Button whatif_button1, whatif_button2, whatif_button3, whatif_button4;
    private boolean[] on = new boolean[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        wound_button1 = (Button) findViewById(R.id.wound1);
        wound_button2 = (Button) findViewById(R.id.wound2);
        wound_button3 = (Button) findViewById(R.id.wound3);
        wound_button4 = (Button) findViewById(R.id.wound4);
        wound_button5 = (Button) findViewById(R.id.wound5);

        whatif_button1 = (Button) findViewById(R.id.whatif1);
        whatif_button2 = (Button) findViewById(R.id.whatif2);
        whatif_button3 = (Button) findViewById(R.id.whatif3);
        whatif_button4 = (Button) findViewById(R.id.whatif4);

        wound_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.wound_text1);
                if(!on[0])
                    textView.setText(text[0]);
                else
                    textView.setText("");
                on[0] = !on[0];
            }
        });

        wound_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.wound_text2);
                if(!on[1])
                    textView.setText(text[1]);
                else
                    textView.setText("");
                on[1] = !on[1];
            }
        });

        wound_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.wound_text3);
                if(!on[2])
                    textView.setText(text[2]);
                else
                    textView.setText("");
                on[2] = !on[2];
            }
        });

        wound_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.wound_text4);
                if(!on[3])
                    textView.setText(text[3]);
                else
                    textView.setText("");
                on[3] = !on[3];
            }
        });

        wound_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.wound_text5);
                if(!on[4])
                    textView.setText(text[4]);
                else
                    textView.setText("");
                on[4] = !on[4];
            }
        });

        whatif_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.whatif_text1);
                if(!on[5])
                    textView.setText(text[5]);
                else
                    textView.setText("");
                on[5] = !on[5];
            }
        });

        whatif_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.whatif_text2);
                if(!on[6])
                    textView.setText(text[6]);
                else
                    textView.setText("");
                on[6] = !on[6];
            }
        });

        whatif_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.whatif_text3);
                if(!on[7])
                    textView.setText(text[7]);
                else
                    textView.setText("");
                on[7] = !on[7];
            }
        });

        whatif_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.whatif_text4);
                if(!on[8])
                    textView.setText(text[8]);
                else
                    textView.setText("");
                on[8] = !on[8];
            }
        });
    }
}