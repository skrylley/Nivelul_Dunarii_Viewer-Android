package com.skrylley.myapplication;

import android.os.AsyncTask;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


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
            return document.title();
        }

        catch (IOException e) {
            e.printStackTrace();
            // MESAJ IN CAZ DE EROARE
            return "ERROR";
        }

    }

    @Override
    protected void onPostExecute(String title) {

        // ACTUALIZARE CASETA TEXT DIN ALLACTIVITY
        mActivity.updateTextView(title);
    }
}
