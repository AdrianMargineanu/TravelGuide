package com.trres.travelguide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trres.travelguide.R;
import com.trres.travelguide.ui.home.room.Trip;
import com.trres.travelguide.ui.home.room.TripListAdapter;
import com.trres.travelguide.ui.home.room.TripViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private TripViewModel tripViewModel;
    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 2;
    public static final String NAME = "name";
    public static final String LOCATION = "location";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.my_recycler_view);
        /*recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new TripListAdapter(root.getContext()));*/
        final TripListAdapter adapter = new TripListAdapter(root.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                adapter.setTrips(trips);
            }
        });

        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(root.getContext(),NewTripActivity.class);
                startActivityForResult(intent,NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }

        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("HomeFragment",requestCode+ " " + (requestCode));
        if(requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Trip trip = new Trip(bundle.getString(NAME),bundle.getString(LOCATION),false,bundle.getInt(PRICE),bundle.getByteArray(IMAGE));
            Log.e("HomeFragment",trip.getName());

            tripViewModel.insert(trip);
        }else{
            Log.e("HomeFragment","in onActivityResault NOT");
        }
    }
}
