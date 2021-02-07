package com.example.extinguisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EQLevel1Activity extends AppCompatActivity {
    private Button backButton, choice1Button, choice2Button, choice3Button, nextButton;
    private TextView question, finish;
    private int mCurrIndex = 0;
    private int choice = -1;

    private Question [] mQuestions = new Question [] {
            new Question(R.string.EQ11_text, 3),
            new Question(R.string.EQ12_text, 3),
            new Question(R.string.EQ13_text, 1),
            new Question(R.string.EQ14_text, 2)
    };
    private Answer [][] mAnswers = new Answer [][] {
            {
                    new Answer(R.string.EQ11a_button, R.string.EQ11a_toast),
                    new Answer(R.string.EQ11b_button, R.string.EQ11b_toast),
                    new Answer(R.string.EQ11c_button, R.string.EQ11c_toast)
            }, {
            new Answer(R.string.EQ12a_button, R.string.EQ12a_toast),
            new Answer(R.string.EQ12b_button, R.string.EQ12b_toast),
            new Answer(R.string.EQ12c_button, R.string.EQ12c_toast)
    }, {
            new Answer(R.string.EQ13a_button, R.string.EQ13a_toast),
            new Answer(R.string.EQ13b_button, R.string.EQ13b_toast),
            new Answer(R.string.EQ13c_button, R.string.EQ13c_toast)
    }, {
            new Answer(R.string.EQ14a_button, R.string.EQ14a_toast),
            new Answer(R.string.EQ14b_button, R.string.EQ14b_toast),
            new Answer(R.string.EQ14c_button, R.string.EQ14c_toast)
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
                    Intent intent = new Intent(EQLevel1Activity.this, GameOverActivity.class);
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
        Toast.makeText(EQLevel1Activity.this, text, Toast.LENGTH_SHORT).show();
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