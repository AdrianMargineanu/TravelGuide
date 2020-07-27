package com.trres.travelguide.ui.home.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
        @SerializedName("weather")
        private List<General> general;
        @SerializedName("main")
        private Temperature temperature;

    public Weather(List<General> general, Temperature temperature) {
        this.general = general;
        this.temperature = temperature;
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
}
