package com.example.extinguisher;

public class Answer {
    private int mAnswerTextID, mToastTextID;

    public Answer(int answer, int toast) {
        mAnswerTextID = answer;
        mToastTextID = toast;
    }

    public int getAnswerTextID() {
        return mAnswerTextID;
    }

    public void setAnswerTextID(int answerTextID) {
        mAnswerTextID = answerTextID;
    }

    public int getToastTextID() {
        return mToastTextID;
    }

    public void setToastTextID(int toastTextID) {
        mToastTextID = toastTextID;
    }
}
