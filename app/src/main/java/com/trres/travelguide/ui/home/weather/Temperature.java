package com.trres.travelguide.ui.home.weather;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("temp")
    private double med;
    @SerializedName("feels_like")
    private double feelsLike;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;

    public static final double KELVIN =  273.15;

    public Temperature(double med, double feelsLike, double tempMin, double tempMax) {
        this.med = med;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public double getMed() {
        return med;
    }

    public void setMed(double med) {
        this.med = med;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
