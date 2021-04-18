package com.example.extinguisher;           

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CampfireActivity extends AppCompatActivity {

    private Button mAButton, mBButton, mCButton, mBackButton;
    private ImageButton mNextButton;
    private int mCurrIndex = 0;
    private int choice = -1;
    private TextView mQuestionTextView, mLivesText;
    private int lives;

    private int [][] mAnswerArr = new int [][] {
            {R.string.campfire1a, R.string.campfire1b, R.string.campfire1c},
            {R.string.campfire2a, R.string.campfire2b, R.string.campfire2c},
            {R.string.campfire3a, R.string.campfire3b, R.string.campfire3c},
            {R.string.campfire4a, R.string.campfire4b, R.string.campfire4c},
            {R.string.campfire5a, R.string.campfire5b, R.string.campfire5c}
    };
    private int [][] mToastArr = new int [][] {
            {R.string.cf_toast1a, R.string.cf_toast1b, R.string.cf_toast1c},
            {R.string.cf_toast2a, R.string.cf_toast2b, R.string.cf_toast2c},
            {R.string.cf_toast3a, R.string.cf_toast3b, R.string.cf_toast3c},
            {R.string.cf_toast4a, R.string.cf_toast4b, R.string.cf_toast4c},
            {R.string.cf_toast5a, R.string.cf_toast5b, R.string.cf_toast5c}
    };
    private Question [] mQuestions = new Question [] {
            new Question(R.string.campfire1_text, 1),
            new Question(R.string.campfire2_text, 0),
            new Question(R.string.campfire3_text, 0),
            new Question(R.string.campfire4_text, 2),
            new Question(R.string.campfire5_text, 1)
    };

    private int[] initialImages = new int[] {
            R.drawable.campfiretwo, R.drawable.restroom, R.drawable.marshmallow_fire, R.drawable.campfiretwo, R.drawable.embers
    };

    private int[] finalImages = new int[] {
            R.drawable.openarea, R.drawable.sister, R.drawable.marshmallow, R.drawable.bucket, R.drawable.outfire
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campfire);

        lives = 2;
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mAButton = (Button) findViewById(R.id.campfirea_button);
        mBButton = (Button) findViewById(R.id.campfireb_button);
        mCButton = (Button) findViewById(R.id.campfirec_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mLivesText = (TextView)findViewById(R.id.cf_lives_text);
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
                Intent intent = new Intent(CampfireActivity.this, WildfireActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setToastText() {
        if(choice == mQuestions[mCurrIndex].getCorrectAnswer()) {
            updatePicture2();
            mAButton.setEnabled(false);
            mBButton.setEnabled(false);
            mCButton.setEnabled(false);
            mNextButton.setVisibility(View.VISIBLE);
        }
        else lives--;
        int text = mToastArr[mCurrIndex][choice];
        Toast.makeText(CampfireActivity.this, text, Toast.LENGTH_SHORT).show();
        updateLives();
    }

    private void updateQuestion() {
        mNextButton.setVisibility(View.INVISIBLE);
        mAButton.setEnabled(true);
        mBButton.setEnabled(true);
        mCButton.setEnabled(true);
        int text = mQuestions[mCurrIndex].getQuestionTextID();
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
        ImageView img= (ImageView) findViewById(R.id.campfire_image);
        img.setImageResource(initialImages[mCurrIndex]);
    }

    private void updatePicture2() {
        ImageView img= (ImageView) findViewById(R.id.campfire_image);
        img.setImageResource(finalImages[mCurrIndex]);
    }

    private void gameOver() {
        PreferenceManager manager = PreferenceManager.getInstance();
        manager.initialize(getApplicationContext());
        if(lives > 0) manager.setComplete(true, 0, lives);
        Intent intent = new Intent(CampfireActivity.this, GameOverActivity.class);
        startActivity(intent);
    }
}
