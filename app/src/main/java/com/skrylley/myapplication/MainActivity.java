package com.skrylley.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;






public class MainActivity extends AppCompatActivity {

    @Override
    // CREARE ACTIVITATE SI PROPRIETATI
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ANIMATIE TRANZITIE
        overridePendingTransition(R.transition.animation_leftright_in, R.transition.animation_leftright_out);

        // BAGAM GLOBALUL
        Global global = (Global) getApplication();
        Resources resources = getResources();



        // BUTON FAVORITE
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

        // BUTON TOATE
        Button buttonToate = findViewById(R.id.buttonToate);
        buttonToate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

        // BUTON SETARI
        Button buttonSetari = findViewById(R.id.buttonSetari);
        buttonSetari.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        // -------------------------------------------------------------------------------------------------------------------------------------------
        // CULORI
        if(global.getGlobalVariableColorCheck())
        {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

            title.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

            buttonFavorite.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
            buttonFavorite.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

            buttonToate.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
            buttonToate.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

            buttonSetari.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
            buttonSetari.setTextColor(getResources().getColor(R.color.colorTextDarkMode));
        }
        else {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

            title.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

            buttonFavorite.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
            buttonFavorite.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

            buttonToate.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
            buttonToate.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

            buttonSetari.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
            buttonSetari.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
        }
        // -------------------------------------------------------------------------------------------------------------------------------------------
        /*try {
            parseOnlySize.get();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


}



