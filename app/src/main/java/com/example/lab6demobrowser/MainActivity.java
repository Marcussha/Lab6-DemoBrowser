package com.example.lab6demobrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private class BrowserDemoWebViewClient extends WebViewClient {
        /*@Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }*/

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            try{
                URL targetURL = new URL(request.getUrl().toString());
                if (targetURL.getHost().equalsIgnoreCase("www.gre.ac.uk")) {
                    view.loadUrl(targetURL.toString());
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry you can not leave www.gre.ac.uk", Toast.LENGTH_LONG).show();
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            return true;
        }
    }

    private WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browser = (WebView) findViewById(R.id.myWebView);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.setWebViewClient(new BrowserDemoWebViewClient());
        browser.loadUrl("https://www.gre.ac.uk");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()){
            browser.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}