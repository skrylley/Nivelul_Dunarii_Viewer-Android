package com.skrylley.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class Global extends Application {



    private static final String PREFS_NAME = "my_preferences"; // Numele fișierului SharedPreferences
    private static final String GLOBAL_COLOR_CHECK_KEY = "global_checker_culoare"; // Cheia pentru verificarea culorii globală
    private static final String GLOBAL_NUMBER_OF_ROWS = "global_no_of_rows"; // Cheia pentru verificarea culorii globală
    private static final String GLOBAL_FAVORITE= "global_checker_fav"; // Cheia pentru verificarea culorii globală
    private boolean globalColorCheck;
    private boolean vectorFav[];
    private boolean vectorFavinit[]={
            false, false, false, false, false,
            false, false, false, false, false,
            false, false, false, false, false,
            false, false, false, false, false,
            false, false, false  };

    private boolean ifRefresh = false;
    private int globalNoOfRows;


    public boolean getIfRefresh() {
        return ifRefresh;
    }

    public void setIfRefresh(boolean value) {
        ifRefresh = value;
    }


    public boolean[] getVectorFavAll() {
        return vectorFav;
    }
    public boolean getVectorFav(int i) {
        return vectorFav[i];
    }

    public void setVectorFav(boolean value, int i) {
        vectorFav[i] = value;
    }
    public void setVectorFavAll(boolean value[]) {
        vectorFav = value;
    }


    public boolean getGlobalVariableColorCheck() {
        return globalColorCheck;
    }

    public void setGlobalVariableColorCheck(boolean value) {
        globalColorCheck = value;
        saveToSharedPreferences();
    }

    public int getGlobalNumberOfRows() {
        return globalNoOfRows;
    }

    public void setGlobalNumberOfRows(int value) {
        globalNoOfRows = value;
        saveToSharedPreferences();
    }

    void saveToSharedPreferences() {
        // Salvează valorile în SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(GLOBAL_COLOR_CHECK_KEY, globalColorCheck);
        for (int k = 0; k < 100; k++) {
            // Folosiți o cheie unică pentru fiecare element din vectorFav
            String favKey = GLOBAL_FAVORITE + "_" + k;
            editor.putBoolean(favKey, vectorFav[k]);
        }
        editor.apply(); // Aplică modificările pentru a le salva în SharedPreferences
    }

    public void onCreate() {
        super.onCreate();

        // Aici poți prelua valorile din SharedPreferences la pornirea aplicației
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        globalColorCheck = preferences.getBoolean(GLOBAL_COLOR_CHECK_KEY, false);

        if (vectorFav == null) {
            vectorFav = new boolean[100];
            Arrays.fill(vectorFav, false);
        }

        for (int k = 0; k < 100; k++) {
            // Folosiți o cheie unică pentru fiecare element din vectorFav
            String favKey = GLOBAL_FAVORITE + "_" + k;
            vectorFav[k] = preferences.getBoolean(favKey, false);
            Log.d("Parse", "k: " + k + " Val:" + vectorFav[k]);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://www.afdj.ro/ro/cotele-dunarii").get();
                    Element firstTable = document.select("table").first();
                    Elements rows = firstTable.select("tbody tr");

                    globalNoOfRows = rows.size();
                    Log.d("Parse", "val1: " + globalNoOfRows);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
