package com.trres.travelguide.ui.home.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Query("SELECT * from trip_table ORDER BY name ASC")
    LiveData<List<Trip>> getAlphabetizedTrips();
}
