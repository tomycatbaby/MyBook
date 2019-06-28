package com.example.mybook.utils;

import com.example.mybook.entity.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherService {

    @GET("weather_mini")
    Observable<Weather> getMessage(@Query("city") String city);
}
