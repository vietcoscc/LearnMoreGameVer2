package com.example.nguynqucvit.learnmoregamever2.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by viet on 24/07/2017.
 */

public class JsoupParserAsyncTask extends AsyncTask<String, ArrayList<ItemGame>, ArrayList<ItemGame>> {
    public static final String TAG = "JsoupParserAsyncTask";

    private OnCompleteParsingListener onCompleteParsingListener;

    @Override
    protected ArrayList<ItemGame> doInBackground(String... strings) {
        String link = strings[0];
        try {
            ArrayList<ItemGame> arrItemGame = new ArrayList<>();
            Document document = Jsoup.connect(link).get();
//            Log.i(TAG,document.outerHtml());
            Elements elementsImage = document.select("div.portfolio-image");
            Elements elementsDes = document.select("div.portfolio-desc");
            for (int i = 0; i < elementsImage.size(); i++) {
                Element elementImage = elementsImage.get(i);
                String imageUrl = "https://linkneverdie.com" + elementImage.select("img").attr("src");
                Element elementDes = elementsDes.get(i);
                String name = elementDes.select("h3").select("a").text().trim();
                String type = elementDes.select("h5").text().trim();
                String views = elementDes.text().replace(type,"").replace(name,"").trim();
                views = views.substring(views.lastIndexOf(" ")+1,views.length());
                String date = elementDes.text().replace(type,"").replace(name,"").trim().replace(views,"").trim();
                String detailsUrl = "https://linkneverdie.com" + elementImage.select("a").attr("href").trim();

                ItemGame itemGame = new ItemGame(0,imageUrl,name,type,date,views,detailsUrl);
                arrItemGame.add(itemGame);
            }
            return arrItemGame;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemGame> itemGames) {
        if(onCompleteParsingListener!=null){
            onCompleteParsingListener.onComplete(itemGames);
        }
        super.onPostExecute(itemGames);
    }

    public void setOnCompleteParsingListener(OnCompleteParsingListener onCompleteParsingListener) {
        this.onCompleteParsingListener = onCompleteParsingListener;
    }

    public interface OnCompleteParsingListener{
        void onComplete(ArrayList<ItemGame> itemGames );
    }

}
