package com.trres.travelguide.ui.home.weather;

import com.google.gson.annotations.SerializedName;

public class General {
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;


    public General(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
