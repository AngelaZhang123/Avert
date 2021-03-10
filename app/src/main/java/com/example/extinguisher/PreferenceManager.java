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
    private static final String STARS = "Stars";
    /*
    indexes for completeList:
    0 = wildfire level 1
    1 = wildfire level 2
    2 = earthquake level 1
    3 = earthquake level 2
     */
    private boolean [] completeList;
    private int [] starsArr;
    private static final int NUM_LEVELS = 4;
    private int points, listPoints, avatarIndex;

    private PreferenceManager() {

    }

    public void initialize(Context context) {
        if (!isInitialised) {
            completeList = new boolean [NUM_LEVELS];
            starsArr = new int [NUM_LEVELS];
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

    public void setComplete(boolean c, int i, int s) {
        completeList[i] = c;
        for(int a = starsArr[i]; a < s; a++) {
            starsArr[i]++;
            points += 5;
        }
        savePreferences();
    }

    public void setListPoints(int p) {
        listPoints = p;
        savePreferences();
    }

    public int getListPoints() {
        return listPoints;
    }

    public int getTotalPoints() {
        return points + listPoints;
    }

    public boolean getComplete(int i) {
        return completeList[i];
    }

    public int getNumComplete() {
        int count = 0;
        for(int i = 0; i < completeList.length; i++) {
            if(completeList[i]) count++;
        }
        return count;
    }

    public int getStars(int i) {
        return starsArr[i];
    }

    public void setAvatarIndex(int i) {
        avatarIndex = i;
        savePreferences();
    }

    public int getAvatarIndex() {
        return avatarIndex;
    }

    private void savePreferences() {
        for(int i = 0; i < NUM_LEVELS; i++) {
            editor.putBoolean(COMPLETE + i, completeList[i]);
            editor.putInt(STARS + i, starsArr[i]);
        }
        editor.putInt("points", points);
        editor.putInt("list points", listPoints);
        editor.putInt("index", avatarIndex);
        editor.commit();
    }

    private void loadPreferences() {
        for(int i = 0; i < completeList.length; i++) {
            completeList[i] = preferences.getBoolean(COMPLETE + i, false);
            starsArr[i] = preferences.getInt(STARS + i, starsArr[i]);
        }
        points = preferences.getInt("points", 0);
        listPoints = preferences.getInt("list points", 0);
        avatarIndex = preferences.getInt("index", 0);
    }
}
