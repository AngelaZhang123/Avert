package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CampfireActivity extends AppCompatActivity {

    private Button mAButton, mBButton, mCButton, mNextButton;
    private int mCurrIndex = 0;
    private int choice = -1;
    private TextView mQuestionTextView;

    private Question [] mQuestions = new Question [] {
            new Question(R.string.campfire1_text, 2),
            new Question(R.string.campfire2_text, 1)
    };
    private Answer [][] mAnswers = new Answer [][] {
            {
                new Answer(R.string.campfire1a_button, R.string.campfire1a_toast),
                new Answer(R.string.campfire1b_button, R.string.campfire1b_toast),
                new Answer(R.string.campfire1c_button, R.string.campfire1c_toast)
            }, {
                new Answer(R.string.campfire2a_button, R.string.campfire2a_toast),
                new Answer(R.string.campfire2b_button, R.string.campfire2b_toast),
                new Answer(R.string.campfire2c_button, R.string.campfire2c_toast)
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campfire);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mAButton = (Button) findViewById(R.id.campfirea_button);
        mBButton = (Button) findViewById(R.id.campfireb_button);
        mCButton = (Button) findViewById(R.id.campfirec_button);
        mNextButton = (Button) findViewById(R.id.campfireNext_button);
        updateQuestion();

        mAButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 0;
                setToastText();
            }
        });

        mBButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 1;
                setToastText();
            }
        });

        mCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 2;
                setToastText();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrIndex++;
                if(mCurrIndex == mAnswers.length) {
                    Intent intent = new Intent(CampfireActivity.this, GameOverActivity.class);
                    startActivity(intent);
                }
                else updateQuestion();
            }
        });
    }

    private void setToastText() {
        mAButton.setEnabled(false);
        mBButton.setEnabled(false);
        mCButton.setEnabled(false);
        mNextButton.setEnabled(true);
        int text = mAnswers[mCurrIndex][choice].getToastTextID();
        Toast.makeText(CampfireActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        mNextButton.setEnabled(false);
        mAButton.setEnabled(true);
        mBButton.setEnabled(true);
        mCButton.setEnabled(true);
        int text = mQuestions[mCurrIndex].getQuestionTextID();
        mQuestionTextView.setText(text);
        text = mAnswers[mCurrIndex][0].getAnswerTextID();
        mAButton.setText(text);
        text = mAnswers[mCurrIndex][1].getAnswerTextID();
        mBButton.setText(text);
        text = mAnswers[mCurrIndex][2].getAnswerTextID();
        mCButton.setText(text);
    }
}