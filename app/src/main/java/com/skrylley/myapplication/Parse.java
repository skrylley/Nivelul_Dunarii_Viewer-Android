package com.skrylley.myapplication;

import android.app.Application;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * This class is used for HTML parsing from URL using Jsoup.
 * @author Andrew Schwartz
 */

public class Parse extends Application {
    static Document document;
    static String title = document.title(); //Get title
    public static void onCreate(String args[]){
        super.onCreate();
        print("running...");
        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect("http://www.zillow.com/denver-co/").get();

            print("  Title: " + title); //Print title.

        } catch (IOException e) {
            e.printStackTrace();
        }
        print("done");
    }

    public static void print(String string) {
        print(string);
    }
    public String getTitle() {
        return title;
    }
}