package com.trres.travelguide.ui.home.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "trip_table")
public class Trip {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "destination")
    private String destination;
    @ColumnInfo(name = "favorite")
    private boolean isFavorite;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "image")
    private byte[] img;
    @ColumnInfo(name = "type")// 0 city brake; 1 sea Side; 2 Mountains
    private int type;
    @ColumnInfo(name = "year_start_date")
    private int yearStartDate;
    @ColumnInfo(name = "month_start_date")
    private int monthStartDate;
    @ColumnInfo(name = "day_start_date")
    private int dayStartDate;
    @ColumnInfo(name = "year_end_date")
    private int yearEndDate;
    @ColumnInfo(name = "month_end_date")
    private int monthEndDate;
    @ColumnInfo(name = "day_end_date")
    private int dayEndDate;
    @ColumnInfo(name = "rating")
    private double rating;
    public Trip(@NonNull String name, String destination, boolean isFavorite, int price, byte[] img,
                int type, int yearStartDate, int monthStartDate, int dayStartDate, int yearEndDate,
                int monthEndDate, int dayEndDate, double rating, int id) {
        this.name = name;
        this.destination = destination;
        this.isFavorite = isFavorite;
        this.price = price;
        this.img = img;
        this.type = type;
        this.yearStartDate = yearStartDate;
        this.monthStartDate = monthStartDate;
        this.dayStartDate = dayStartDate;
        this.yearEndDate = yearEndDate;
        this.monthEndDate = monthEndDate;
        this.dayEndDate = dayEndDate;
        this.rating = rating;
        this.id = id;
    }

    @NonNull
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

    public int getType() {
        return type;
    }

    public int getYearStartDate() {
        return yearStartDate;
    }

    public int getMonthStartDate() {
        return monthStartDate;
    }

    public int getDayStartDate() {
        return dayStartDate;
    }

    public int getYearEndDate() {
        return yearEndDate;
    }

    public int getMonthEndDate() {
        return monthEndDate;
    }

    public int getDayEndDate() {
        return dayEndDate;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setYearStartDate(int yearStartDate) {
        this.yearStartDate = yearStartDate;
    }

    public void setMonthStartDate(int monthStartDate) {
        this.monthStartDate = monthStartDate;
    }

    public void setDayStartDate(int dayStartDate) {
        this.dayStartDate = dayStartDate;
    }

    public void setYearEndDate(int yearEndDate) {
        this.yearEndDate = yearEndDate;
    }

    public void setMonthEndDate(int monthEndDate) {
        this.monthEndDate = monthEndDate;
    }

    public void setDayEndDate(int dayEndDate) {
        this.dayEndDate = dayEndDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", isFavorite=" + isFavorite +
                ", price=" + price +
                ", type=" + type +
                ", yearStartDate=" + yearStartDate +
                ", monthStartDate=" + monthStartDate +
                ", dayStartDate=" + dayStartDate +
                ", yearEndDate=" + yearEndDate +
                ", monthEndDate=" + monthEndDate +
                ", dayEndDate=" + dayEndDate +
                ", rating=" + rating +
                '}';
    }
}
