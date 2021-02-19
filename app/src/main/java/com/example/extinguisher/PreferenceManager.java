package com.example.extinguisher;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager {

    private static PreferenceManager self;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean isInitialised;
    private static final String COMPLETE = "Complete";
    private boolean [] completeList;
    private static final int NUM_LEVELS = 2;

    private PreferenceManager() {
    }

    public void initialize(Context context) {
        if (!isInitialised) {
            completeList = new boolean [NUM_LEVELS];
            preferences = context.getSharedPreferences("Preferences", 0);
            editor = preferences.edit();
            loadPreferences();
            isInitialised = true;
        }
    }

    public static PreferenceManager getInstance() {
        if (self == null) {
            self = new PreferenceManager();
        }
        return self;
    }

    public void setComplete(boolean b, int i) {
        completeList[i] = b;
        savePreferences();
    }

    public boolean getComplete(int i) {
        return completeList[i];
    }

    private void savePreferences() {
        for(int i = 0; i < completeList.length; i++) {
            editor.putBoolean(COMPLETE + i, completeList[i]);
        }
        editor.commit();
    }

    private void loadPreferences() {
        for(int i = 0; i < completeList.length; i++) {
            completeList[i] = preferences.getBoolean(COMPLETE + i, false);
        }
    }
}
