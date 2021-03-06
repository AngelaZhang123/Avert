package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HouseActivity extends AppCompatActivity {

    private Button mAButton, mBButton, mCButton, mBackButton;
    private ImageButton mNextButton;
    private int mCurrIndex = 0;
    private int choice = -1;
    private TextView mQuestionTextView, mLivesText;
    private int lives;

    private int [][] mAnswerArr = new int [][] {
            {R.string.house1a, R.string.house1b, R.string.house1c},
            {R.string.house2a, R.string.house2b, R.string.house2c},
            {R.string.house3a, R.string.house3b, R.string.house3c},
            {R.string.house4a, R.string.house4b, R.string.house4c},
            {R.string.house5a, R.string.house5b, R.string.house5c}
    };
    private int [][] mToastArr = new int [][] {
            {R.string.house_toast1a, R.string.house_toast1b, R.string.house_toast1c},
            {R.string.house_toast2a, R.string.house_toast2b, R.string.house_toast2c},
            {R.string.house_toast3a, R.string.house_toast3b, R.string.house_toast3c},
            {R.string.house_toast4a, R.string.house_toast4b, R.string.house_toast4c},
            {R.string.house_toast5a, R.string.house_toast5b, R.string.house_toast5c}
    };
    private Question [] mQuestionArr = new Question [] {
            new Question(R.string.h1_text, 1),
            new Question(R.string.h2_text, 0),
            new Question(R.string.h3_text, 2),
            new Question(R.string.h4_text, 0),
            new Question(R.string.h5_text, 1)
    };

    private int[] initialImages = new int[] {
            R.drawable.firehouse, R.drawable.man, R.drawable.man, R.drawable.clothes_fire, R.drawable.man
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        lives = 2;
        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        mAButton = (Button) findViewById(R.id.housea_button);
        mBButton = (Button) findViewById(R.id.houseb_button);
        mCButton = (Button) findViewById(R.id.housec_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mLivesText = (TextView) findViewById(R.id.h_lives_text);
        updateQuestion();
        updatePicture();

        mAButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 0;
                setToastText();
                mAButton.setEnabled(false);
            }
        });

        mBButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 1;
                setToastText();
                mBButton.setEnabled(false);
            }
        });

        mCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 2;
                setToastText();
                mCButton.setEnabled(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrIndex++;
                if(mCurrIndex == mAnswerArr.length) gameOver();
                else updateQuestion();
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseActivity.this, WildfireActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setToastText() {
        if(choice == mQuestionArr[mCurrIndex].getCorrectAnswer()) {
            mAButton.setEnabled(false);
            mBButton.setEnabled(false);
            mCButton.setEnabled(false);
            mNextButton.setVisibility(View.VISIBLE);
        }
        else lives--;
        updateLives();
        int text = mToastArr[mCurrIndex][choice];
        Toast.makeText(HouseActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        mNextButton.setVisibility(View.INVISIBLE);
        //updatePicture();
        mAButton.setEnabled(true);
        mBButton.setEnabled(true);
        mCButton.setEnabled(true);
        int text = mQuestionArr[mCurrIndex].getQuestionTextID();
        mQuestionTextView.setText(text);
        mAButton.setText(mAnswerArr[mCurrIndex][0]);
        mBButton.setText(mAnswerArr[mCurrIndex][1]);
        mCButton.setText(mAnswerArr[mCurrIndex][2]);
        updateLives();
        updatePicture();
    }

    private void updateLives() {
        mLivesText.setText("Lives left: " + lives);
        if(lives == 0) gameOver();
    }

    private void updatePicture() {
        ImageView img= (ImageView) findViewById(R.id.house_image);
        img.setImageResource(initialImages[mCurrIndex]);
    }

    private void gameOver() {
        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        if(lives > 0) manager.setComplete(true, 1, lives);
        Intent intent = new Intent(HouseActivity.this, GameOverActivity.class);
        startActivity(intent);
    }
}