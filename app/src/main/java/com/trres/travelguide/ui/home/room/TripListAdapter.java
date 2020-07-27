package com.trres.travelguide.ui.home.room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.trres.travelguide.R;
import com.trres.travelguide.ui.home.HomeFragment;
import com.trres.travelguide.ui.home.NewTripActivity;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;


public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder>{

    public static final int EDIT_TRIP_ACTIVITY_REQUEST_CODE = 3;

    public class TripViewHolder extends RecyclerView.ViewHolder  /*implements RecyclerView.OnLongClickListener*/{
        private ImageView imageView;
        private TextView tripNameTextView;
        private TextView tripLocationTextView;
        private TextView tripCostTextView;
        private CheckBox favoriteCheckBox;
        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView4);
            tripNameTextView = itemView.findViewById(R.id.trip_name);
            tripLocationTextView = itemView.findViewById(R.id.destination);
            tripCostTextView = itemView.findViewById(R.id.price);
            favoriteCheckBox = itemView.findViewById(R.id.favorite);

        }

//        @Override
//        public boolean onLongClick(View v) {
//            int i =getAdapterPosition();
//            Trip current = trips.get(i);
//            Intent intent =  new Intent(v.getContext(),NewTripActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(HomeFragment.NAME,current.getName());
//            bundle.putString(HomeFragment.LOCATION,current.getDestination());
//            bundle.putInt(HomeFragment.PRICE,current.getPrice());
//            bundle.putByteArray(HomeFragment.IMAGE,current.getImg());
//            bundle.putInt(HomeFragment.YEAR_START_DATE,current.getYearStartDate());
//            bundle.putInt(HomeFragment.MONTH_START_DATE,current.getMonthStartDate());
//            bundle.putInt(HomeFragment.DAY_START_DATE,current.getDayStartDate());
//            bundle.putInt(HomeFragment.YEAR_END_DATE,current.getYearEndDate());
//            bundle.putInt(HomeFragment.MONTH_END_DATE,current.getMonthEndDate());
//            bundle.putInt(HomeFragment.DAY_END_DATE,current.getDayEndDate());
//            bundle.putDouble(HomeFragment.RATING,current.getRating());
//            bundle.putInt(HomeFragment.TYPE,current.getType());
//            intent.putExtras(bundle);
//            ((Activity) v.getContext()).startActivityForResult(intent,
//                    HomeFragment.NEW_TRIP_ACTIVITY_REQUEST_CODE,bundle);
//            return false;
//        }
    }

    ViewGroup parent;
    private Context context;
    private List<Trip> trips;

    public TripListAdapter(Context context){this.context  = context;}

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,
                false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        if(trips != null){
            Trip current = trips.get(position);
            holder.tripNameTextView.setText(current.getName());
            holder.tripLocationTextView.setText(current.getDestination());
            holder.tripCostTextView.setText(current.getPrice()+"$");
            holder.favoriteCheckBox.setSelected(current.isFavorite());
            Bitmap bmp = BitmapFactory.decodeByteArray(current.getImg(),0,
                    current.getImg().length);
            holder.imageView.post(new Runnable() {
                @Override
                public void run() {
                    holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                            holder.imageView.getWidth(), holder.imageView.getHeight(),false));
                }
            });
            /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent =  new Intent(v.getContext(),NewTripActivity.class);
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
                    ((Activity) parent.getContext()).startActivityForResult(intent,
                            HomeFragment.NEW_TRIP_ACTIVITY_REQUEST_CODE,bundle);
                    return true;
                }
            });*/
        }else{
            Toast.makeText(context,"NO TRIPS",Toast.LENGTH_LONG);
        }
    }

    public Trip getTrip(int position){
        return trips.get(position);
    }

    public void setTrips(List<Trip> trips){
        this.trips = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(trips != null){
            return trips.size();
        }
        return 0;
    }
}
