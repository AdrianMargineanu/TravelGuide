package com.trres.travelguide.ui.home.weather;

import android.util.Log;

import com.trres.travelguide.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherRepository {
    private static WeatherRepository weatherRepository;

    private GetWeather getWeather;

    public WeatherRepository(GetWeather getWeather) {
        this.getWeather = getWeather;
    }

    public static WeatherRepository getInstance(){
        if (weatherRepository == null){
            Retrofit retrofit = RetrofitWeather.getRetrofitWeather();
            weatherRepository = new WeatherRepository(retrofit.create(GetWeather.class));
        }
        return weatherRepository;
    }

    public void getWeather(final OnGetWeatherCallBack callBack,String location){
        getWeather.getWeather(location, BuildConfig.API_KEY)
                .enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        if(response.isSuccessful()){
                            Weather weather  = response.body();
                            if(weather != null){
                                callBack.onSuccess(weather);
                            }else{
                                callBack.onError();
                                Log.e("Repository","Weather null");
                            }
                        }else {

                            callBack.onError();
                            Log.e("Repository",response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        callBack.onError();
                        Log.e("Respository",t.getMessage());
                    }
                });
    }
}
