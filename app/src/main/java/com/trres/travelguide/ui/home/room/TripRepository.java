package com.trres.travelguide.ui.home.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    TripRepository(Application application){
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        tripDao = db.tripDao();
        allTrips = tripDao.getAlphabetizedTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }

    void insert(Trip trip){
        TripRoomDatabase.databaseWriteExecutor.execute(()->tripDao.insert(trip));
    }
}
