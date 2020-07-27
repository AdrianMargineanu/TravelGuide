package com.trres.travelguide.ui.home.weather;

import com.trres.travelguide.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetWeather {
    @GET ("weather")
    Call<Weather> getWeather(@Query("q") String city,@Query("appid") String key);
}
