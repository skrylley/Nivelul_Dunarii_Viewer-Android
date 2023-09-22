package com.skrylley.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ecran2 extends AppCompatActivity {
// ceva comentariu care terbuie sa dispara
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
        overridePendingTransition(R.transition.animation_rightleft_in, R.transition.animation_rightleft_out);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Definește intentul pentru a deschide o altă activitate
                Intent intent = new Intent(ecran2.this, MainActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed(); // Apelarea metodei din clasa de bază pentru a gestiona comportamentul Back
        Intent intent = new Intent(ecran2.this, MainActivity.class);
        startActivity(intent); // Deschide activitatea specificată
        finish();
    }

}
