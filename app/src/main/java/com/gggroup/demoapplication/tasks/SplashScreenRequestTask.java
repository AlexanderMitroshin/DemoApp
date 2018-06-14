package com.gggroup.demoapplication.tasks;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.gggroup.demoapplication.api.ApiProvider;


public class SplashScreenRequestTask extends AsyncTask<Void, Void, String> {

    public interface Callback {
        void onSuccess(String url);

        void onError();
    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return ApiProvider.getInstance().firstRequest();
    }

    @Override
    protected void onPostExecute(String url) {
        super.onPostExecute(url);
        if (mCallback != null) {
            if (!TextUtils.isEmpty(url)) {
                mCallback.onSuccess(url);
            } else {
                mCallback.onError();
            }
        }
    }
}
