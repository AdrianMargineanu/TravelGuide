package com.trres.travelguide.ui.home.weather;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather implements WeatherData {
        @SerializedName("weather")
        private List<General> general;
        @SerializedName("main")
        private Temperature temperature;
        private PrecipType precipType;

    public Weather(List<General> general, Temperature temperature) {
        this.general = general;
        this.temperature = temperature;
    }

    public void setWeather() {
        if(general.get(0).getMain().compareTo("Rain") == 0 || general.get(0).getMain().compareTo("Drizzle") == 0){
            precipType = PrecipType.RAIN;
        }else if(general.get(0).getMain().compareTo("Snow") == 0){
            precipType = PrecipType.SNOW;

        }else if(general.get(0).getMain().compareTo("Clear") == 0){
            precipType = PrecipType.CLEAR;
        }else{
            precipType = PrecipType.SNOW;
        }
    }

    public List<General> getGeneral() {
        return general;
    }

    public void setGeneral(List<General> general) {
        this.general = general;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public float getEmissionRate() {
        return precipType.getEmissionRate();
    }

    @Override
    public PrecipType getPrecipType() {
        return precipType;
    }

    @Override
    public int getSpeed() {
        return precipType.getSpeed();
    }
}
