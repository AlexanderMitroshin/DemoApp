package com.gggroup.demoapplication.api;


import com.gggroup.demoapplication.model.NewsData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiInterface {
    @POST("#introduction")
    Call<ResponseBody> postRequest();

    @GET
    Call<ResponseBody> getRequest(@Url String url);

    @GET
    Call<NewsData> getNews(@Url String url);
}
