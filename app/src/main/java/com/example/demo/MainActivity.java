package com.example.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView  mWebview;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebAction();
            }
        });

        WebAction();

    }

     public void WebAction() {

            mWebview = findViewById(R.id.webview);

            mWebview.setWebViewClient(new MyClient());

            String url = "http://mscitclass.com/doctor-app/doctor/login.php";

            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.getSettings().setAllowContentAccess(true);
            //Webview.getSettings().setAppCacheEnabled(true);
            swipe.setRefreshing(true);
            mWebview.loadUrl(url);


            mWebview.setWebViewClient(new WebViewClient(){

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                    mWebview.loadUrl("file:///android_asset/error.html");
                    if(mWebview.canGoBack()) {
                        finish();
                    }

                }

                public void onPageFinished(WebView view, String url) {
                    // do your stuff here
                    swipe.setRefreshing(false);
                }

            });
        }

    private class MyClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // or finish();
    }
}
