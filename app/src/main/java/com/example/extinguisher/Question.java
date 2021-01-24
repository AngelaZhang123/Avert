package com.example.extinguisher;

public class Question {

    private int mQuestionTextID, mCorrectAnswer;

    public Question(int id, int correct) {
        mQuestionTextID = id;
        mCorrectAnswer = correct;
    }

    public int getQuestionTextID() {
        return mQuestionTextID;
    }

    public void setQuestionTextID(int questionTextID) {
        mQuestionTextID = questionTextID;
    }

    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }
}
