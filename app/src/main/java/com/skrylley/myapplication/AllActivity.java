    package com.skrylley.myapplication;

    import android.content.Intent;
    import android.graphics.drawable.Drawable;
    import android.os.Build;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;

    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
    import android.widget.ToggleButton;

    import androidx.annotation.RequiresApi;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.cardview.widget.CardView;


    import java.util.ArrayList;
    import java.util.Vector;

    public class AllActivity extends AppCompatActivity {

        // VALORI CU DATELE DE AFISAT
        String valLoc[], valKm[], valNiv[], valVar[];
        int N; // nr de elemente GLOBAL
        ArrayList<CardView> cardViewList = new ArrayList<>();

        ArrayList<ToggleButton> toggleButtonList = new ArrayList<>();

        ArrayList<TextView> cardLocalitateList = new ArrayList<>();
        ArrayList<TextView> cardKmList = new ArrayList<>();
        ArrayList<TextView> cardVariatieList = new ArrayList<>();
        ArrayList<TextView> cardNivelList = new ArrayList<>();

        ArrayList<TextView> cardValLocalitateList = new ArrayList<>();
        ArrayList<TextView> cardValKmList = new ArrayList<>();
        ArrayList<TextView> cardValVariatieList = new ArrayList<>();
        ArrayList<TextView> cardValNivelList = new ArrayList<>();

        // CREARE ACTIVITATE SI PROPRIETATI
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all);
            // ANIMATIE TRANZITIE
            overridePendingTransition(R.transition.animation_rightleft_in, R.transition.animation_rightleft_out);

            // BAGAM GLOBALUL
            Global global = (Global) getApplication();
            N = global.getGlobalNumberOfRows();
            final boolean[] isToggled = new boolean[1];

            // BAGAM PARSUL
            ParseAll parse = new ParseAll(this);
            parse.execute();
            if(global.getIfRefresh()==true)
            {
                try {
                    parse.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                global.setGlobalNumberOfRows(parse.getSize());
                N = global.getGlobalNumberOfRows();
            }

            global.setIfRefresh(false);


            // CARD VIEW RECURSIV
            // -------------------------------------------------------------------------------------------------------------------------------------------
            LinearLayout linearLayout = findViewById(R.id.cardLinearLayout); // Acesta este LinearLayout-ul tău din XML

            for (int i = 0; i < N; i++) {
                CardView cardView = new CardView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                layoutParams.setMargins(
                        getResources().getDimensionPixelSize(R.dimen.card_margin_bottom),
                        getResources().getDimensionPixelSize(R.dimen.card_margin_bottom),
                        getResources().getDimensionPixelSize(R.dimen.card_margin_bottom),
                        getResources().getDimensionPixelSize(R.dimen.card_margin_bottom));

                cardView.setLayoutParams(layoutParams);
                cardView.setCardElevation(getResources().getDimension(R.dimen.card_elevation));
                cardView.setRadius(getResources().getDimension(R.dimen.card_corner_radius));
                cardViewList.add(cardView);

                // -------------------------------------------------------------------------------------------------------------------------------------------
                //BUTTON FAVORITE


                ToggleButton toggleButtonFav = new ToggleButton(this);
                toggleButtonFav.setLayoutParams(new LinearLayout.LayoutParams(170, 150));

                //toggleButtonFav.setPaddingRelative(100,100,0,0);
                toggleButtonFav.setPadding(100,100,0,0);

               // layoutParams.leftMargin = 50;
                toggleButtonFav.setTextOff("");
                toggleButtonFav.setTextOn("");
                toggleButtonFav.setText("");

                toggleButtonFav.setChecked(global.getVectorFav(i)); // Inițializați cu valoarea stocată
                //ImageView steaFavorite = cardView.findViewById(R.id.steaFavorite);
                // ++++++++++++++++++++++++++++++++++++++++++++
                boolean isChecked = global.getVectorFav(i);
                // LA FEL 1
                if(isChecked) {
                    //toggleButtonFav.setText("VERIC");
                    toggleButtonFav.setBackgroundResource(R.drawable.ic_star_on);
                }
                else {
                    //toggleButtonFav.setText("nu :(");
                    toggleButtonFav.setBackgroundResource(R.drawable.ic_star_off);
                }
                // ++++++++++++++++++++++++++++++++++++++++++++

                int finalI2 = i;
                toggleButtonFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ++++++++++++++++++++++++++++++++++++++++++++
                        // LA FEL 2
                        boolean isChecked = ((ToggleButton) v).isChecked();
                        if(isChecked) {
                            //toggleButtonFav.setText("VERIC");
                            toggleButtonFav.setBackgroundResource(R.drawable.ic_star_on);
                        }
                        else {
                            //toggleButtonFav.setText("nu :(");
                            toggleButtonFav.setBackgroundResource(R.drawable.ic_star_off);
                        }
                        // ++++++++++++++++++++++++++++++++++++++++++++
                        global.setVectorFav(isChecked, finalI2);
                        // Salvați starea ToggleButton-urilor în SharedPreferences
                        Global global = (Global) getApplication();
                        global.saveToSharedPreferences();
                    }
                });

                //relativeLayout.addView(toggleButtonFav);
                cardView.addView(toggleButtonFav);
                toggleButtonList.add(toggleButtonFav);

                // LOCALITATE
                TextView cardLocalitate = new TextView(this);
                cardLocalitate.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginTop) *1,
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginBottom)); // LEFT TOP RIGHT BOTTOM

                cardLocalitate.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                cardLocalitate.setText(getResources().getString(R.string.cardLocalitate));
                cardView.addView(cardLocalitate);
                cardLocalitateList.add(cardLocalitate);

                TextView cardValLocalitate  = new TextView(this);
                cardValLocalitate .setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginTop) *1,
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardValLocalitate .setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                cardValLocalitate.setText("Connecting");    // AICI II PUNEM VALOARE DIN PARSE
                cardView.addView(cardValLocalitate );
                cardValLocalitateList.add(cardValLocalitate);

                // -------------------------------------------------------------------------------------------------------------------------------------------

                // KILOMETRU
                TextView cardKm = new TextView(this);
                cardKm.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginTop) *2,
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardKm.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                cardKm.setText(getResources().getString(R.string.cardKm));
                cardView.addView(cardKm);
                cardKmList.add(cardKm);

                TextView cardValKm = new TextView(this);
                cardValKm.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginTop) *2,
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardValKm.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                cardValKm.setText("Connecting");    // AICI II PUNEM VALOARE DIN PARSE
                cardView.addView(cardValKm );
                cardValKmList.add(cardValKm);

                // -------------------------------------------------------------------------------------------------------------------------------------------

                // VARIATIE
                TextView cardVariatie = new TextView(this);
                cardVariatie.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginTop) *3,
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardVariatie.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                cardVariatie.setText(getResources().getString(R.string.cardVariatie));
                cardView.addView(cardVariatie);
                cardVariatieList.add(cardVariatie);

                TextView cardValVariatie = new TextView(this);
                cardValVariatie.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginTop) *3,
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardValVariatie.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                cardValVariatie.setText("Connecting");    // AICI II PUNEM VALOARE DIN PARSE
                cardView.addView(cardValVariatie );
                cardValVariatieList.add(cardValVariatie);

                // -------------------------------------------------------------------------------------------------------------------------------------------

                // NIVEL

                TextView cardNivel = new TextView(this);
                cardNivel.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginTop) *4,
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_leftValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardNivel.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                cardNivel.setText(getResources().getString(R.string.cardNivel));
                cardView.addView(cardNivel);
                cardNivelList.add(cardNivel);

                TextView cardValNivel = new TextView(this);
                cardValNivel.setPadding(
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginLeft),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginTop) *4,
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginRight),
                        getResources().getDimensionPixelSize(R.dimen.card_rightValues_marginBottom)); // LEFT TOP RIGHT BOTTOM
                cardValNivel.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));


                cardValNivel.setText("Connecting");    // AICI II PUNEM VALOARE DIN PARSE
                cardView.addView(cardValNivel );
                cardValNivelList.add(cardValNivel);

                // -------------------------------------------------------------------------------------------------------------------------------------------

                // Poți personaliza CardView-ul aici adăugând elemente, text sau alte widget-uri
                linearLayout.addView(cardView);
            }
            // -------------------------------------------------------------------------------------------------------------------------------------------


            // BUTON BACK
            Button buttonBack = findViewById(R.id.buttonBack);
            TextView textNoInternet = findViewById(R.id.textNoInternet);
            buttonBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Definește intentul pentru a deschide o altă activitate
                    Intent intent = new Intent(AllActivity.this, MainActivity.class);
                    startActivity(intent); // Deschide activitatea specificată
                    finish();
                }
            });
            // BUTON REFRESH
            Button buttonRefresh = findViewById(R.id.buttonRefresh);
            buttonRefresh.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Definește intentul pentru a redeschide aceeași activitate
                    global.setIfRefresh(true);
                    Intent intent = new Intent(AllActivity.this, AllActivity.class);
                    startActivity(intent); // Redeschide activitatea
                    finish(); // Termină activitatea curentă
                }
            });


            // -------------------------------------------------------------------------------------------------------------------------------------------
            // CULORI
            if(global.getGlobalVariableColorCheck())
            {
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));
                linearLayout.setBackgroundColor(getResources().getColor(R.color.colorBackgroundDarkMode));

                buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
                buttonBack.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                buttonRefresh.setBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
                buttonRefresh.setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                textNoInternet.setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                for (int i = 0; i < N; i++) {
                    cardLocalitateList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardKmList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardVariatieList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardNivelList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                    cardValLocalitateList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardValKmList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardValVariatieList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));
                    cardValNivelList.get(i).setTextColor(getResources().getColor(R.color.colorTextDarkMode));

                    cardViewList.get(i).setCardBackgroundColor(getResources().getColor(R.color.colorButtonDarkMode));
                }
            }
            else {
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));
                linearLayout.setBackgroundColor(getResources().getColor(R.color.colorBackgroundWhiteMode));

                buttonBack.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
                buttonBack.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                buttonRefresh.setBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
                buttonRefresh.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                textNoInternet.setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                for (int i = 0; i < N; i++) {
                    cardLocalitateList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardKmList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardVariatieList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardNivelList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                    cardValLocalitateList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardValKmList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardValVariatieList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));
                    cardValNivelList.get(i).setTextColor(getResources().getColor(R.color.colorTextWhiteMode));

                    cardViewList.get(i).setCardBackgroundColor(getResources().getColor(R.color.colorButtonWhiteMode));
                }
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


        // ACTUALIZARE LOCALITATE
        public void updateLocalitate(String[] val) {
            valLoc = val;
            for (int i = 0; i < N; i++) {
                TextView textView = cardValLocalitateList.get(i);
                if (valLoc[i] != null) {
                    textView.setText(valLoc[i]);
                }
                else
                    textView.setText("Error");
            }
        }

        // ACTUALIZARE KM
        public void updateKm(String[] val) {
            valKm = val;
            for (int i = 0; i < N; i++) {
                TextView textView = cardValKmList.get(i);
                if (valKm[i] != null) {
                    textView.setText(valKm[i]);
                }
                else
                    textView.setText("Error");
            }
        }
        // ACTUALIZARE VARIATIE
        public void updateNivel(String[] val) {
            valNiv = val;
            for (int i = 0; i < N; i++) {
                TextView textView = cardValVariatieList.get(i);
                if (valNiv[i] != null) {
                    textView.setText(valNiv[i]);
                }
                else
                    textView.setText("Error");
            }
        }
        // ACTUALIZARE NIVEL
        public void updateVariatie(String[] val) {
            valVar = val;
            for (int i = 0; i < N; i++) {
                TextView textView = cardValNivelList.get(i);
                if (valVar[i] != null) {
                    textView.setText(valVar[i]);
                }
                else
                    textView.setText("Error");
            }
        }
    }