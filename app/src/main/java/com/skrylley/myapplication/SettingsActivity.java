package com.skrylley.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;




public class SettingsActivity extends AppCompatActivity {

    @Override
    // CREARE ACTIVITATE SI PROPRIETATI
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // ANIMATIE TRANZITIE
        overridePendingTransition(R.transition.animation_rightleft_in, R.transition.animation_rightleft_out);

        // BAGAM GLOBALUL
        Global global = (Global) getApplication();



        // BUTON BACK
        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Definește intentul pentru a deschide o altă activitate
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

        // BUTON SWITCH COLOR MODE
        final Switch switchButton = findViewById(R.id.switchColor);
        switchButton.setChecked(global.getGlobalVariableColorCheck());

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Global global = (Global) getApplication();
                Resources resources = getResources();
                if (isChecked) {
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

                    buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
                    buttonBack.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                    //switchButton.getTrackDrawable().setColorFilter(getResources().getColor(R.color.colorSwitchOn), PorterDuff.Mode.OVERLAY);
                    switchButton.setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));
                    switchButton.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                }
                else {
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

                    buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
                    buttonBack.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                    //switchButton.getTrackDrawable().setColorFilter(getResources().getColor(R.color.colorSwitchOff), PorterDuff.Mode.OVERLAY);
                    switchButton.setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));
                    switchButton.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                }
                // SALVEZ POZITIA BUTONULUI INTRE SESIUNI
                global.setGlobalVariableColorCheck(isChecked);
            }
        });



        // -------------------------------------------------------------------------------------------------------------------------------------------
        // CULORI
        if(global.getGlobalVariableColorCheck())
        {

            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

            buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
            buttonBack.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

            switchButton.setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));
            switchButton.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

        }
        else {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

            buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
            buttonBack.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

            switchButton.setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));
            switchButton.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
        }
        switchButton.getThumbDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        // -------------------------------------------------------------------------------------------------------------------------------------------
    }

    @Override
    // BUTON BACK FIZIC
    public void onBackPressed() {
        //super.onBackPressed(); // Apelarea metodei din clasa de bază pentru a gestiona comportamentul Back
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent); // Deschide activitatea specificată
        finish();
    }

}