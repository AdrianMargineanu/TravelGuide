package com.trres.travelguide.ui.home.weather;

import com.trres.travelguide.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWeather {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitWeather(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_API_WEATHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
