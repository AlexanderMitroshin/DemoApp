package com.gggroup.demoapplication.api;

import android.util.Log;

import com.gggroup.demoapplication.model.News;
import com.gggroup.demoapplication.model.NewsData;

import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Http2;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

import static com.gggroup.demoapplication.api.ApiConstants.BASE_URL;


public class ApiProvider {

    private static final String TAG = ApiProvider.class.getSimpleName();

    private static ApiProvider instance;

    private ApiInterface apiInterface;

    private ApiProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiProvider getInstance() {
        if (instance == null) {
            instance = new ApiProvider();
        }
        return instance;
    }

    public String firstRequest() {
        try {
//            Call<ResponseBody> call = apiInterface.postRequest();
            Call<ResponseBody> call = apiInterface.getRequest(ApiConstants.SECOND_URL);
            Response<ResponseBody> response = call.execute();
            Log.i(TAG, "postRequest code " + response.code() + " body " + response.body() + " headers " + response.headers());
            if (response.code() == 301 || response.code() == 302) {
                return response.headers().get("location");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<News> getNews(String category) throws Exception {
        Call<NewsData> call = apiInterface.getNews(ApiConstants.NEWS_URL + (category != null ? "/" + category : ""));
        Response<NewsData> response = call.execute();
        Log.i(TAG, "getNews code " + response.code() + " body " + response.body() + " headers " + response.headers());
        return response.body().getData();
    }

}
