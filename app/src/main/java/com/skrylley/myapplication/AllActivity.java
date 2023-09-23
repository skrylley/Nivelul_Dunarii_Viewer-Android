package com.skrylley.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AllActivity extends AppCompatActivity {

    // CREARE ACTIVITATE SI PROPRIETATI
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        Parse parse = (Parse) getApplication();
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(parse.getTitle());

        // ANIMATIE TRANZITIE
        overridePendingTransition(R.transition.animation_rightleft_in, R.transition.animation_rightleft_out);

        // BAGAM GLOBALUL
        Global global = (Global) getApplication();
        Resources resources = getResources();

        // BUTON BACK
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Definește intentul pentru a deschide o altă activitate
                Intent intent = new Intent(AllActivity.this, MainActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

        // Caseta Text Hello World

        TextView textView = findViewById(R.id.textView);
        // -------------------------------------------------------------------------------------------------------------------------------------------
        // CULORI
        if(global.getGlobalVariableColorCheck())
        {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

            buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
            buttonBack.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

            textView.setTextColor(getResources().getColor(R.color.colorTextDarkMode));
        }
        else {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

            buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
            buttonBack.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

            textView.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
        }
        // -------------------------------------------------------------------------------------------------------------------------------------------
    }

    @Override
    // BUTON BACK FIZIC
    public void onBackPressed() {
        //super.onBackPressed(); // Apelarea metodei din clasa de bază pentru a gestiona comportamentul Back
        Intent intent = new Intent(AllActivity.this, MainActivity.class);
        startActivity(intent); // Deschide activitatea specificată
        finish();
    }
}