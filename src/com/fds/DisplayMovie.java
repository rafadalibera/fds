package com.fds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ShareActionProvider;

public class DisplayMovie extends Activity {
	
	WebView mWebView;
	String url;
	ShareActionProvider provider;
	String TAG;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        
        Log.d("URL", url);
        
        WebView wv = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        wv.loadUrl(url);   
	}
	
	class MyWebViewClient extends WebViewClient 
	{ 
	    @Override 
	    //show the web page in webview but not in web browser
	    public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	        view.loadUrl (url); 
	        return true;
	    }
	}

}
