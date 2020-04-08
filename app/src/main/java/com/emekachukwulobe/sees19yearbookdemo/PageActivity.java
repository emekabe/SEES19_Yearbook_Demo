package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PageActivity extends AppCompatActivity {

    ProgressBar progressBarCircular;
    ProgressBar progressBarHorizontal;

    WebView page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        progressBarCircular = findViewById(R.id.progress_circular);
        progressBarHorizontal = findViewById(R.id.progress_horizontal);

        page = findViewById(R.id.webview);

        renderWebPage(Alumni.PAGE_LINK, page);

    }

    protected void renderWebPage(String urlToRender, WebView webViewToDoStuff){
        webViewToDoStuff.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                progressBarHorizontal.setVisibility(View.VISIBLE);
                progressBarCircular.setVisibility(View.VISIBLE);
                page.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                page.setVisibility(View.VISIBLE);
                progressBarCircular.setVisibility(View.GONE);
                progressBarHorizontal.setVisibility(View.GONE);
//                Toast.makeText(PageActivity.this, "Page loaded.", Toast.LENGTH_LONG).show();
            }
        });

        webViewToDoStuff.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress){
                progressBarHorizontal.setProgress(newProgress);

                if (newProgress == 100){
                    progressBarHorizontal.setVisibility(View.GONE);
                    progressBarCircular.setVisibility(View.GONE);
                    page.setVisibility(View.VISIBLE);
                }
            }
        });

        webViewToDoStuff.getSettings().setJavaScriptEnabled(true);
        webViewToDoStuff.loadUrl(urlToRender);
    }

    //Incase I show a page with an hyperlink
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && page.canGoBack()){
            page.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
