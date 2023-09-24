    package com.skrylley.myapplication;

    import android.content.Intent;
    import android.content.res.Resources;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import com.skrylley.myapplication.Parse;
    import androidx.appcompat.app.AppCompatActivity;

    import org.w3c.dom.Text;

    public class AllActivity extends AppCompatActivity {

        // CREARE ACTIVITATE SI PROPRIETATI
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all);

            // ANIMATIE TRANZITIE
            overridePendingTransition(R.transition.animation_rightleft_in, R.transition.animation_rightleft_out);

            // BAGAM GLOBALUL
            Global global = (Global) getApplication();

            // BAGAM PARSUL
            Parse parse = new Parse(this);
            parse.execute();

            // Casete text
            //TextView textView = findViewById(R.id.textView);    // HELLO WORLD
            TextView textView2 = findViewById(R.id.textView2);  // HELLO DUNARE - VEZI COD LA FINAL

            //Valori Cardview
            //TextView valLocalitate = findViewById(R.id.valLocalitate);
            TextView valKm = findViewById(R.id.valKm);
            TextView valVariatie = findViewById(R.id.valVariatie);
            TextView valNivel = findViewById(R.id.valNivel);


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



            // -------------------------------------------------------------------------------------------------------------------------------------------
            // CULORI
            if(global.getGlobalVariableColorCheck())
            {
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

                buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
                buttonBack.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                //textView.setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                textView2.setTextColor(getResources().getColor(R.color.colorTextDarkMode));
            }
            else {
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

                buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
                buttonBack.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                //textView.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                textView2.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
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


        // ACTUALIZARE CASETA TEXT 2
        public void updateTextView(String valLocalitate) {

            TextView valLocalitate1 = findViewById(R.id.valLocalitate);
            valLocalitate1.setText(valLocalitate);
        }
    }