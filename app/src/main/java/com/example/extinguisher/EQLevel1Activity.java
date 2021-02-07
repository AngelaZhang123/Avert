package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EQLevel1Activity extends AppCompatActivity {
    private Button backButton, choice1Button, choice2Button, choice3Button;
    private TextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_q_level1);
        question = (TextView) findViewById(R.id.question);
        choice1Button = (Button) findViewById(R.id.choice1);
        choice2Button = (Button) findViewById(R.id.campfirec_button);
        choice3Button = (Button) findViewById(R.id.next_button);
        backButton = (Button) findViewById(R.id.backButton);
        buttonChoices1();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EQLevel1Activity.this, EarthquakeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void buttonChoices1(){
        choice3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("What position should you take?");
                choice1Button.setText("Squat Down");
                choice2Button.setText("Cover your neck");
                choice3Button.setText("Hold onto desk with one hand, cover neck with other");
                buttonChoices2();
            }
        });
    }

    public void buttonChoices2(){
        choice3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("The earthquake is still going on. What should you do next?");
                choice1Button.setText("Hold your position");
                choice2Button.setText("Check to see for possible damage");
                choice3Button.setText("Run away before the house collapses");
                buttonChoices3();
            }
        });
    }

    public void buttonChoices3(){
        choice3Button.setOnClickListener(null);
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("The shaking has stopped. What now?");
                choice1Button.setText("Stay where you are");
                choice2Button.setText("Carefully exit the house");
                choice3Button.setText("Run around, happy to be alive");
                endOfSimulation();
            }
        });
    }

    public void endOfSimulation(){
        choice3Button.setOnClickListener(null);
        choice1Button.setOnClickListener(null);
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("Congratulations! You survived the earthquake!");
                choice1Button.setVisibility(View.INVISIBLE);
                choice2Button.setVisibility(View.INVISIBLE);
                choice3Button.setVisibility(View.INVISIBLE);
            }
        });
    }
}