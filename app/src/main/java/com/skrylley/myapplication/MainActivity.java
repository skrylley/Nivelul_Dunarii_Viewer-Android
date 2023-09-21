package com.skrylley.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.transition.animation_leftright_in, R.transition.animation_leftright_out);

        // Găsește butonul după ID
        Button button1 = findViewById(R.id.buttonFavorite);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

        Button buttonToate = findViewById(R.id.buttonToate);
        buttonToate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent); // Deschide activitatea specificată
                finish();
            }
        });

    }
}


