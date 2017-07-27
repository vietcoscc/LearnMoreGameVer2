package com.example.nguynqucvit.learnmoregamever2.asynctask;

import android.os.AsyncTask;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by viet on 27/07/2017.
 */

public class HtmlDownloadingAsyncTask extends AsyncTask<String, String, String> {
    private OnCompleteDownloadListener onCompleteDownloadListener;

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        if (link != null) {
            try {
                String src = Jsoup.connect(link).get().outerHtml();
                return src;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (onCompleteDownloadListener != null) {
            onCompleteDownloadListener.OnComplete(s);
        }
    }

    public void setOnCompleteDownloadListener(OnCompleteDownloadListener onCompleteDownloadingListener) {
        this.onCompleteDownloadListener = onCompleteDownloadingListener;
    }

    public interface OnCompleteDownloadListener {
        void OnComplete(String src);
    }
}
