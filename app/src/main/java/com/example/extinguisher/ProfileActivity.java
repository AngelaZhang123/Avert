package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private int [] images = {R.drawable.smiley, R.drawable.blue_smiley, R.drawable.yellow_smiley};
    private int [] pointsNeeded = {0, 15, 30};
    private ImageButton rightButton, leftButton;
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
        currIndex = manager.getAvatarIndex();
        lock = (ImageView) findViewById(R.id.lock_image);
        square = (ImageView) findViewById(R.id.border);
        selectButton = (Button) findViewById(R.id.select_avatar);
        avatarText = (TextView) findViewById(R.id.avatar_text);
        setImage();

        TextView pointsText = (TextView) findViewById(R.id.profile_points);
        pointsText.setText(pointsText.getText() + " " + points);
        TextView levelsText = (TextView) findViewById(R.id.profile_levels);
        levelsText.setText("" + levelsText.getText() + " " + levels);

        rightButton = (ImageButton) findViewById(R.id.right_arrow);
        rightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currIndex++;
                currIndex %= images.length;
                setImage();
            }
        });

        leftButton = (ImageButton) findViewById(R.id.left_arrow);
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
                avatarText.setVisibility(View.VISIBLE);
                avatarText.setText("Selected");
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