package com.skrylley.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ParseAll extends AsyncTask<Void, Void, Void> {
    private AllActivity mActivity;
    private String[] valLocalitate;
    private String[] valKm;
    private String[] valNivel;
    private String[] valVariatie;
    private int rowCount;
    public ParseAll(AllActivity activity) {
        mActivity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            // CERERE CONECTIUNE JSON
            Document document = Jsoup.connect("https://www.afdj.ro/ro/cotele-dunarii").get();
            //Elements rows = document.select("tbody tr"); // Selectează toate rândurile din toate tabelele
            Element firstTable = document.select("table").first();
            Elements rows = firstTable.select("tbody tr");

            rowCount = rows.size();

            valLocalitate = new String[rowCount];
            valKm = new String[rowCount];
            valNivel = new String[rowCount];
            valVariatie = new String[rowCount];

            for (int i = 0; i < rowCount; i++) {
                Element row = rows.get(i);
                Elements cells = row.select("td"); // Selectează toate celulele din rând

                // Verificați dacă există cel puțin 4 celule în rând înainte de a încerca să le accesați
                if (cells.size() >= 4) {
                    // Ia valorile din celule pentru fiecare coloană și le salvezi în variabilele de instanță
                    valLocalitate[i] = cells.get(0).text(); // Localitate
                    valKm[i] = cells.get(1).text(); // Km
                    valNivel[i] = cells.get(2).text(); // Nivel
                    valVariatie[i] = cells.get(3).text(); // Variatie
                } else {
                    // Setează valoarea la un șir gol sau la alt text corespunzător în cazul lipsei datelor
                    valLocalitate[i] = "";
                    valKm[i] = "";
                    valNivel[i] = "";
                    valVariatie[i] = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // MESAJ IN CAZ DE EROARE
        }
        return null;
    }
    public String[] getLocalitateParse() {
        return valLocalitate;
    }
    public int getSize() {
        return rowCount;
    }


    protected void onPostExecute(Void result) {
        String[] errorArray = new String[getSize()];
        Arrays.fill(errorArray, "error");

        if (valLocalitate != null && valKm != null && valNivel != null && valVariatie != null) {
            mActivity.updateLocalitate(valLocalitate);
            mActivity.updateKm(valKm);
            mActivity.updateNivel(valNivel);
            mActivity.updateVariatie(valVariatie);
        }
        else
        {
            mActivity.updateLocalitate(errorArray);
            mActivity.updateKm(errorArray);
            mActivity.updateNivel(errorArray);
            mActivity.updateVariatie(errorArray);
        }
    }

}


