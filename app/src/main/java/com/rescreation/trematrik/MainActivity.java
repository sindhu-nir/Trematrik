package com.rescreation.trematrik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageButton exitImageButton;
    TextView toolbarTitle;
    WebView webView;
    ProgressBar bar;
    String webURL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        exitImageButton=findViewById(R.id.exitImageButton);
        toolbarTitle=findViewById(R.id.title);
        exitImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView=(WebView) findViewById(R.id.webView);
        bar = (ProgressBar) findViewById(R.id.progress1);

        webURL=getResources().getString(R.string.webUrl);

        LoadWebView();
        //This is a test
    }

    private void LoadWebView() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("http://trematrik.com/");

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public class WebViewClientImpl extends WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stubsuper.onPageStarted(view, url, favicon);


            bar.setVisibility(View.VISIBLE);

            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url)
        {


            bar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            Log.e("urhhllll", url);
            view.loadUrl(url);
            return true;

        }
    }
}
