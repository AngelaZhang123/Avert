package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EQLevel2Activity extends AppCompatActivity {

    private Button backButton, choice1Button, choice2Button, choice3Button, nextButton;
    private TextView question, finish;
    private int mCurrIndex = 0;
    private int choice = -1;

    private Question [] mQuestions = new Question [] {
            new Question(R.string.EQ11_text, 2),
            new Question(R.string.EQ22_text, 3),
            new Question(R.string.EQ23_text, 3)
    };
    private Answer [][] mAnswers = new Answer [][] {
            {
                    new Answer(R.string.EQ21a_button, R.string.EQ21a_toast),
                    new Answer(R.string.EQ21b_button, R.string.EQ21b_toast),
                    new Answer(R.string.EQ21c_button, R.string.EQ21c_toast)
            }, {
            new Answer(R.string.EQ22a_button, R.string.EQ22a_toast),
            new Answer(R.string.EQ22b_button, R.string.EQ22b_toast),
            new Answer(R.string.EQ22c_button, R.string.EQ22c_toast)
    }, {
            new Answer(R.string.EQ23a_button, R.string.EQ23a_toast),
            new Answer(R.string.EQ23b_button, R.string.EQ23b_toast),
            new Answer(R.string.EQ23c_button, R.string.EQ23c_toast)
    }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_q_level2);

        question = (TextView) findViewById(R.id.question);
        choice1Button = (Button) findViewById(R.id.choice1);
        choice2Button = (Button) findViewById(R.id.choice2);
        choice3Button = (Button) findViewById(R.id.choice3);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);
        updateQuestion();

        choice1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 0;
                setToastText(1);
            }
        });

        choice2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 1;
                setToastText(2);
            }
        });

        choice3Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = 2;
                setToastText(3);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrIndex++;
                if(mCurrIndex == mAnswers.length) {
                    Intent intent = new Intent(EQLevel2Activity.this, GameOverActivity.class);
                    startActivity(intent);
                }
                else updateQuestion();
            }
        });
    }

    private void setToastText(int num) {
        if (num==1)
            choice1Button.setEnabled(false);
        else if (num==2)
            choice2Button.setEnabled(false);
        else
            choice3Button.setEnabled(false);
        if(num ==mQuestions[mCurrIndex].getCorrectAnswer()) {
            nextButton.setEnabled(true);
            nextButton.setVisibility(View.VISIBLE);
            choice1Button.setEnabled(false);
            choice2Button.setEnabled(false);
            choice3Button.setEnabled(false);
        }
        int text = mAnswers[mCurrIndex][choice].getToastTextID();
        Toast.makeText(EQLevel2Activity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        nextButton.setVisibility(View.INVISIBLE);
        nextButton.setEnabled(false);
        choice1Button.setEnabled(true);
        choice2Button.setEnabled(true);
        choice3Button.setEnabled(true);
        int text = mQuestions[mCurrIndex].getQuestionTextID();
        question.setText(text);
        text = mAnswers[mCurrIndex][0].getAnswerTextID();
        choice1Button.setText(text);
        text = mAnswers[mCurrIndex][1].getAnswerTextID();
        choice2Button.setText(text);
        text = mAnswers[mCurrIndex][2].getAnswerTextID();
        choice3Button.setText(text);
        text = mAnswers[mCurrIndex][0].getAnswerTextID();
        choice3Button.setText(text);
        text = mAnswers[mCurrIndex][2].getAnswerTextID();
        choice3Button.setText(text);
    }
}