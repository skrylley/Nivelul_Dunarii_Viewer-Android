package com.skrylley.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ParseOnlySize extends Global{
    private MainActivity mActivity;

     private int rowCount;



    public void onCreate() {

        super.onCreate();
        try {
            Document document = Jsoup.connect("https://www.afdj.ro/ro/cotele-dunarii").get();
            Element firstTable = document.select("table").first();
            Elements rows = firstTable.select("tbody tr");

            rowCount = rows.size();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return rowCount;
    }


    public void execute() {
        try {
            Document document = Jsoup.connect("https://www.afdj.ro/ro/cotele-dunarii").get();
            Element firstTable = document.select("table").first();
            Elements rows = firstTable.select("tbody tr");

            rowCount = rows.size();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
