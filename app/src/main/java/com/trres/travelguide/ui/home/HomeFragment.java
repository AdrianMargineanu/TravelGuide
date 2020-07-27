package com.trres.travelguide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trres.travelguide.MainActivity;
import com.trres.travelguide.R;
import com.trres.travelguide.ui.home.room.RecyclerTouchListener;
import com.trres.travelguide.ui.home.room.Trip;
import com.trres.travelguide.ui.home.room.TripListAdapter;
import com.trres.travelguide.ui.home.room.TripViewModel;
import com.trres.travelguide.ui.home.room.TripsClickListener;
import com.trres.travelguide.ui.home.weather.ReadOnlyActivity;

import java.util.EventListener;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private int position;
    TripListAdapter adapter;
    private RecyclerView recyclerView;
    private TripViewModel tripViewModel;
    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 2;
    public static final int EDIT_TRIP_ACTIVITY_REQUEST_CODE = 3;
    public static final String NAME = "name";
    public static final String LOCATION = "location";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";
    public static final String TYPE = "type;";
    public static final String YEAR_START_DATE = "year_start_date";
    public static final String MONTH_START_DATE = "month_start_date";
    public static final String DAY_START_DATE = "day_start_date";
    public static final String YEAR_END_DATE = "year_end_date";
    public static final String MONTH_END_DATE = "month_end_date";
    public static final String DAY_END_DATE = "day_end_date";
    public static final String RATING = "rating";
    public static final String ID = "id";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.my_recycler_view);
        final TripListAdapter adapter = new TripListAdapter(root.getContext());
        this.adapter = adapter;
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
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(root.getContext(), recyclerView, new TripsClickListener() {
            @Override
            public void onClick(View view, int position) {
                Trip current =  adapter.getTrip(position);
                Intent intent =  new Intent(root.getContext(),ReadOnlyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(HomeFragment.NAME,current.getName());
                bundle.putString(HomeFragment.LOCATION,current.getDestination());
                bundle.putInt(HomeFragment.PRICE,current.getPrice());
                bundle.putByteArray(HomeFragment.IMAGE,current.getImg());
                bundle.putInt(HomeFragment.YEAR_START_DATE,current.getYearStartDate());
                bundle.putInt(HomeFragment.MONTH_START_DATE,current.getMonthStartDate());
                bundle.putInt(HomeFragment.DAY_START_DATE,current.getDayStartDate());
                bundle.putInt(HomeFragment.YEAR_END_DATE,current.getYearEndDate());
                bundle.putInt(HomeFragment.MONTH_END_DATE,current.getMonthEndDate());
                bundle.putInt(HomeFragment.DAY_END_DATE,current.getDayEndDate());
                bundle.putDouble(HomeFragment.RATING,current.getRating());
                bundle.putInt(HomeFragment.TYPE,current.getType());
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Trip current =  adapter.getTrip(position);
                Intent intent =  new Intent(root.getContext(),NewTripActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(HomeFragment.NAME,current.getName());
                bundle.putString(HomeFragment.LOCATION,current.getDestination());
                bundle.putInt(HomeFragment.PRICE,current.getPrice());
                bundle.putByteArray(HomeFragment.IMAGE,current.getImg());
                bundle.putInt(HomeFragment.YEAR_START_DATE,current.getYearStartDate());
                bundle.putInt(HomeFragment.MONTH_START_DATE,current.getMonthStartDate());
                bundle.putInt(HomeFragment.DAY_START_DATE,current.getDayStartDate());
                bundle.putInt(HomeFragment.YEAR_END_DATE,current.getYearEndDate());
                bundle.putInt(HomeFragment.MONTH_END_DATE,current.getMonthEndDate());
                bundle.putInt(HomeFragment.DAY_END_DATE,current.getDayEndDate());
                bundle.putDouble(HomeFragment.RATING,current.getRating());
                bundle.putInt(HomeFragment.TYPE,current.getType());
                bundle.putInt(HomeFragment.ID,current.getId());
                intent.putExtras(bundle);
                startActivityForResult(intent,EDIT_TRIP_ACTIVITY_REQUEST_CODE);
            }
        }));
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE) {
                Bundle bundle = data.getExtras();
                Trip trip = new Trip(bundle.getString(NAME),
                        bundle.getString(LOCATION), false,
                        bundle.getInt(PRICE),
                        bundle.getByteArray(IMAGE),
                        bundle.getInt(TYPE),
                        bundle.getInt(YEAR_START_DATE),
                        bundle.getInt(MONTH_START_DATE),
                        bundle.getInt(DAY_START_DATE),
                        bundle.getInt(YEAR_END_DATE),
                        bundle.getInt(MONTH_END_DATE),
                        bundle.getInt(DAY_END_DATE),
                        bundle.getDouble(RATING),
                        adapter.getItemCount()+1
                );
                Log.e("HomeFragment", trip.toString());
                tripViewModel.insert(trip);
             }
            if(requestCode == EDIT_TRIP_ACTIVITY_REQUEST_CODE){
                Bundle bundle = data.getExtras();
                Trip trip = adapter.getTrip(position);
                trip.setName(bundle.getString(NAME));
                trip.setDestination(bundle.getString(LOCATION));
                trip.setImg(bundle.getByteArray(IMAGE));
                trip.setType(bundle.getInt(TYPE));
                trip.setYearStartDate(bundle.getInt(YEAR_START_DATE));
                trip.setMonthStartDate(bundle.getInt(MONTH_START_DATE));
                trip.setDayStartDate(bundle.getInt(DAY_START_DATE));
                trip.setYearEndDate(bundle.getInt(YEAR_END_DATE));
                trip.setMonthEndDate(bundle.getInt(MONTH_END_DATE));
                trip.setDayEndDate(bundle.getInt(DAY_END_DATE));
                trip.setRating(bundle.getDouble(RATING));
                trip.setPrice(bundle.getInt(PRICE));
                trip.setId(bundle.getInt(ID));
                Log.e("HOMEFRAGMENT","EDIT PART");
                tripViewModel.insert(trip);
            }
        }else{
            Log.e("HomeFragment","in onActivityResault NOT");
        }
    }

}
