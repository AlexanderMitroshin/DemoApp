package com.gggroup.demoapplication.tasks;

import android.os.AsyncTask;

import com.gggroup.demoapplication.api.ApiProvider;
import com.gggroup.demoapplication.model.News;

import java.util.List;


public class GetNewsTask extends AsyncTask<String, Void, List<News>> {

    public interface Callback {
        void onSuccess(List<News> news);

        void onError();
    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    protected List<News> doInBackground(String... strings) {
        try {
            List<News> news = ApiProvider.getInstance().getNews(strings[0]);
            return news;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<News> news) {
        super.onPostExecute(news);
        if (mCallback != null) {
            if (news != null) {
                mCallback.onSuccess(news);
            } else {
                mCallback.onError();
            }
        }
    }
}
