package com.skrylley.myapplication;

import android.os.AsyncTask;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parse extends AsyncTask<Void, Void, String> {
    private AllActivity mActivity;

    public Parse(AllActivity activity) {
        mActivity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            // CERERE CONECTIUNE JSON
            Document document = Jsoup.connect("https://www.afdj.ro/ro/cotele-dunarii").get();

            // IA TITLUL
            Elements elements = document.select("h2.title");
            return elements.text();
        }

        catch (IOException e) {
            e.printStackTrace();
            // MESAJ IN CAZ DE EROARE
            return "ERROR";
        }

    }

    @Override
    protected void onPostExecute(String valLocalitate) {

        // ACTUALIZARE CASETA TEXT DIN ALLACTIVITY
        mActivity.updateTextView(valLocalitate);
    }
}
