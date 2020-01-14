package com.hrvikas.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class webViewActivity extends AppCompatActivity {
    WebView webView;
    ProgressDialog progressDialog;
    String surl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=findViewById(R.id.webview);
        Bundle b=getIntent().getExtras();
         surl=b.getString("URL");
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading..");
        progressDialog.show();
        webView.setWebViewClient(new Mybrowser(progressDialog));
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(surl);

    }
    public  class Mybrowser extends WebViewClient
    {
        ProgressDialog pd;
        public Mybrowser(ProgressDialog pd)
        {
            this.pd=pd;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(surl);
            return super.shouldOverrideUrlLoading(view, url);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (pd.isShowing())
            {
                pd.dismiss();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Toast.makeText(webViewActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(webViewActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
