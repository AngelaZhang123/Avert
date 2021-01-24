package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    private Button mPlayAgain, mMenuButton, mSimulation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        mPlayAgain = (Button) findViewById(R.id.play_again_button);
        mPlayAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, CampfireActivity.class);
                startActivity(intent);
            }
        });

        mMenuButton = (Button) findViewById(R.id.menu_button);
        mMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        mSimulation = (Button) findViewById(R.id.simulation_button);
        mSimulation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, SimulationActivity.class);
                startActivity(intent);
            }
        });
    }
}