package com.example.mybook.utils;

import com.example.mybook.entity.UserInfo;
import com.example.mybook.entity.Weather;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("wxarticle/list/{id}/{page}/json")
    Call<JsonObject> search(@Path("id") String id, @Path("page") String page);


    @GET("wxarticle/list/{id}/{page}/json")
    Observable<JsonObject> login(@Path("id") String id, @Path("page") String page);

    //可以定义其它请求
    @GET("/something.json")
    Call<JsonObject> dosomething(@Query("params") long params);

    @GET("article/list/{num}/json")
    Observable<JsonObject> getArticleList(@Path("num") int num);
}
