package com.gggroup.demoapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.tasks.SplashScreenRequestTask;
import com.gggroup.demoapplication.ui.news.MainActivity;


public class SplashActivity extends Activity {

    public static final String TAG = "SplashActivity";
    private WebView mWebView;
    private View mProgress;
    private SplashScreenRequestTask mSplashScreenRequestTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mWebView = findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mProgress = findViewById(R.id.progress);
        mSplashScreenRequestTask = new SplashScreenRequestTask();
        mSplashScreenRequestTask.setCallback(new SplashScreenRequestTask.Callback() {
            @Override
            public void onSuccess(String url) {
                Log.i(TAG,"onSuccess "+url);
                mProgress.setVisibility(View.GONE);
                mWebView.loadUrl(url);
            }

            @Override
            public void onError() {
                Log.i(TAG,"onError");
                mProgress.setVisibility(View.GONE);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mSplashScreenRequestTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashScreenRequestTask.setCallback(null);
    }
}

