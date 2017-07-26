package com.example.nguynqucvit.learnmoregamever2.activity;

import android.graphics.Bitmap;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nguynqucvit.learnmoregamever2.R;

import static com.example.nguynqucvit.learnmoregamever2.fragment.BaseFragment.DETAILS_URL;
import static com.example.nguynqucvit.learnmoregamever2.fragment.BaseFragment.NAME;

public class GameContentActivity extends AppCompatActivity {
    private String detailsUrl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        initData();
        initViews();
    }

    private void initData() {
        detailsUrl = getIntent().getExtras().getString(DETAILS_URL);
        name = getIntent().getExtras().getString(NAME);
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
        final ContentLoadingProgressBar contentLoadingProgressBar =
                (ContentLoadingProgressBar) findViewById(R.id.contentLoadingProgressBar);

        final WebView wvGameContent = (WebView) findViewById(R.id.wvGameContent);
        WebSettings webSettings = wvGameContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvGameContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!contentLoadingProgressBar.isShown()) {
                    contentLoadingProgressBar.show();
                }
                //TODO:
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (contentLoadingProgressBar.isShown()) {
                    contentLoadingProgressBar.hide();
                }
                //TODO:
            }
        });
        wvGameContent.loadUrl(detailsUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
