package com.trres.travelguide.ui.home.weather;

public interface OnGetWeatherCallBack {

    void onSuccess (Weather weather);

    void onError();
}
