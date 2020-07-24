package com.trres.travelguide.ui.home.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository repository;

    private LiveData<List<Trip>> allTrips;

    public TripViewModel (Application application) {
        super(application);
        repository = new TripRepository(application);
        allTrips = repository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }

    public void insert (Trip trip) { repository.insert(trip);}
}
