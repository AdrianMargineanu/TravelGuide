package com.trres.travelguide.ui.home.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "destination")
    private String destination;
    @ColumnInfo(name = "favorite")
    private boolean isFavorite;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "image")
    private byte[]  img;

    public Trip(String name, String destination, boolean isFavorite, int price, byte[] img) {
        this.name = name;
        this.destination = destination;
        this.isFavorite = isFavorite;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getPrice() {
        return price;
    }

    public byte[] getImg() {
        return img;
    }
}
