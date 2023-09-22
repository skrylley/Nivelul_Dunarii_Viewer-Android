package com.skrylley.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class Global extends Application {
    private static final String PREFS_NAME = "my_preferences"; // Numele fișierului SharedPreferences
    private static final String GLOBAL_COLOR_KEY = "global_culoare"; // Cheia pentru culoarea globală
    private static final String GLOBAL_COLOR_CHECK_KEY = "global_checker_culoare"; // Cheia pentru verificarea culorii globală

    private String globalColor;
    private boolean globalColorCheck;

    public String getGlobalVariableColor() {
        return globalColor;
    }

    public void setGlobalVariableColor(String value) {
        globalColor = value;
        saveToSharedPreferences();
    }

    public boolean getGlobalVariableColorCheck() {
        return globalColorCheck;
    }

    public void setGlobalVariableColorCheck(boolean value) {
        globalColorCheck = value;
        saveToSharedPreferences();
    }

    private void saveToSharedPreferences() {
        // Salvează valorile în SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GLOBAL_COLOR_KEY, globalColor);
        editor.putBoolean(GLOBAL_COLOR_CHECK_KEY, globalColorCheck);
        editor.apply();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Resources resources = getResources();
        // Aici poți prelua valorile din SharedPreferences la pornirea aplicației
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        globalColor = preferences.getString(GLOBAL_COLOR_KEY, resources.getString(R.string.colorBackgroundWhiteMode)); // Valoarea implicită poate fi setată aici
        globalColorCheck = preferences.getBoolean(GLOBAL_COLOR_CHECK_KEY, false); // Valoarea implicită poate fi setată aici
    }
}