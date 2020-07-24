package com.trres.travelguide.ui.home.room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trres.travelguide.R;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

    public class TripViewHolder extends RecyclerView.ViewHolder {
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
    }

    private Context context;
    private List<Trip> trips;

    public TripListAdapter(Context context){this.context  = context;}

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
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
        }else{
            Toast.makeText(context,"NO TRIPS",Toast.LENGTH_LONG);
        }
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
