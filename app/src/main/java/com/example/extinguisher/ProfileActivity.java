package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private final int [] images = {R.drawable.avatar_one, R.drawable.avatar_two, R.drawable.avatar_three};
    private final int [] pointsNeeded = {0, 15, 30};
    private Button selectButton;
    private ImageView avatar, lock, square;
    private TextView avatarText;
    private int currIndex, points, selected;
    private PreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        points = manager.getTotalPoints();
        int levels = manager.getNumComplete();
        avatar = (ImageView) findViewById(R.id.avatar);
        selected = currIndex = manager.getAvatarIndex();
        lock = (ImageView) findViewById(R.id.lock_image);
        square = (ImageView) findViewById(R.id.border);
        selectButton = (Button) findViewById(R.id.select_avatar);
        avatarText = (TextView) findViewById(R.id.avatar_text);
        setImage();

        TextView pointsText = (TextView) findViewById(R.id.profile_points);
        pointsText.setText(pointsText.getText() + " " + points);
        TextView levelsText = (TextView) findViewById(R.id.profile_levels);
        levelsText.setText("" + levelsText.getText() + " " + levels);

        ImageButton rightButton = (ImageButton) findViewById(R.id.right_arrow);
        rightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currIndex++;
                currIndex %= images.length;
                setImage();
            }
        });

        ImageButton leftButton = (ImageButton) findViewById(R.id.left_arrow);
        leftButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currIndex--;
                if(currIndex == -1) currIndex = images.length-1;
                setImage();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selected = currIndex;
                manager.setAvatarIndex(selected);
                square.setVisibility(View.VISIBLE);
                avatarText.setText("Selected");
                avatarText.setVisibility(View.VISIBLE);
                selectButton.setVisibility(View.INVISIBLE);
            }
        });

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_profile);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setImage() {
        avatar.setImageResource(images[currIndex]);
        if(points >= pointsNeeded [currIndex]) {
            lock.setVisibility(View.INVISIBLE);
            if(currIndex == selected) {
                square.setVisibility(View.VISIBLE);
                selectButton.setVisibility(View.INVISIBLE);
                avatarText.setVisibility(View.VISIBLE);
                avatarText.setText("Selected");
            }
            else {
                square.setVisibility(View.INVISIBLE);
                selectButton.setVisibility(View.VISIBLE);
                avatarText.setVisibility(View.INVISIBLE);
            }
        }
        else {
            square.setVisibility(View.INVISIBLE);
            lock.setVisibility(View.VISIBLE);
            selectButton.setVisibility(View.INVISIBLE);
            avatarText.setVisibility(View.VISIBLE);
            avatarText.setText("Unlock at " + pointsNeeded[currIndex] + " points");
        }
    }
}