package com.trres.travelguide.ui.home.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.trres.travelguide.R;
import com.trres.travelguide.ui.home.HomeFragment;

public class ReadOnlyActivity extends AppCompatActivity {
    private WeatherRepository weatherRepository;
    private String location;
    private WeatherView weatherView;

    private ImageView icon;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView costTextView;
    private TextView dateTextView;
    private TextView typeTextView;
    private TextView generalTextView;
    private TextView descriptionTextView;
    private TextView tempMedTextView;
    private TextView tempMaxTextView;
    private TextView tempMinTextView;
    private TextView tempFeelsTextView;
    private RatingBar rating;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_only);
        setView();
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        if(location != null) {
            weatherRepository = WeatherRepository.getInstance();
            weatherRepository.getWeather(new OnGetWeatherCallBack() {
                @Override
                public void onSuccess(Weather weather) {
                    weather.setWeather();
                    setIcon(weather);
                    Log.e("ReadOnly",weather.getGeneral().get(0).getMain());
                    generalTextView.setText(weather.getGeneral().get(0).getMain());
                    descriptionTextView.setText(weather.getGeneral().get(0).getDescription());
                    tempMedTextView.setText("Current temperature " + convector(weather.getTemperature().getMed()) + " C");
                    tempMinTextView.setText("Minimum temperature " + convector(weather.getTemperature().getTempMin()) + " C");
                    tempMaxTextView.setText("Maximum temperature " + convector(weather.getTemperature().getTempMax()) + " C");
                    tempFeelsTextView.setText("Temperature feels like " + convector(weather.getTemperature().getFeelsLike()) + " C");
                    if(weather.getPrecipType() != null) {
                        weatherView.setWeatherData(weather);
                    }
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                }

                @Override
                public void onError() {
                    Toast.makeText(ReadOnlyActivity.this, "NOT OK", Toast.LENGTH_LONG).show();
                }
            }, location);
        }

    }

    private void setIcon(Weather weather) {
        if(weather.getPrecipType() != null){
            if(weather.getPrecipType().compareTo(PrecipType.CLEAR) == 0){
                icon.setImageResource(R.drawable.sun);
            }else if(weather.getPrecipType().compareTo(PrecipType.RAIN) == 0){
                icon.setImageResource(R.drawable.rain);
            }else if(weather.getPrecipType().compareTo(PrecipType.SNOW) == 0){
                icon.setImageResource(R.drawable.snow);
            }else{
                icon.setImageResource(R.drawable.cloud);
            }
        }else{
            icon.setImageResource(R.drawable.cloud);
        }

    }

    private double convector (Double temp){
        return Math.round(temp - Temperature.KELVIN);
    }

    private String getDate(Bundle bundle){
        return new String(bundle.getInt(HomeFragment.DAY_START_DATE) + "/"
                + bundle.getInt(HomeFragment.MONTH_START_DATE) + "/"
                + bundle.getInt(HomeFragment.YEAR_START_DATE) + "-"
                + bundle.getInt(HomeFragment.DAY_END_DATE) + "/"
                + bundle.getInt(HomeFragment.MONTH_END_DATE) + "/"
                + bundle.getInt(HomeFragment.YEAR_END_DATE));
    }

    private String getType(int type){
        switch (type){
            case 0:
                return "CITY BREAK";
            case 1:
                return "SEA SIDE";
            case 2:
                return "MOUNTAIN";
        }
        return null;
    }

    private void setView(){
        Bundle bundle = getIntent().getExtras();
        weatherView = findViewById(R.id.weather_view);
        nameTextView = findViewById(R.id.name);
        location = bundle.getString(HomeFragment.LOCATION);
        nameTextView.setText(bundle.getString(HomeFragment.NAME));
        locationTextView = findViewById(R.id.location);
        locationTextView.setText(bundle.getString(HomeFragment.LOCATION));
        costTextView = findViewById(R.id.cost);
        costTextView.setText(bundle.getInt(HomeFragment.PRICE)+ "$");
        dateTextView = findViewById(R.id.date);
        dateTextView.setText(getDate(bundle));
        typeTextView = findViewById(R.id.type);
        typeTextView.setText(getType(bundle.getInt(HomeFragment.TYPE)));
        generalTextView = findViewById(R.id.general);
        descriptionTextView = findViewById(R.id.description);
        tempMedTextView = findViewById(R.id.temp);
        tempMaxTextView = findViewById(R.id.temp_max);
        tempMinTextView = findViewById(R.id.temp_min);
        tempFeelsTextView = findViewById(R.id.temp_feels);
        rating = findViewById(R.id.rating_bar);
        rating.setRating((float) bundle.getDouble(HomeFragment.RATING));
        imageView = findViewById(R.id.display_img);
        byte[] imagine = bundle.getByteArray(HomeFragment.IMAGE);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagine,0,
                        imagine.length);
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                        imageView.getWidth(),imageView.getHeight(),false));
            }
        });
        icon = findViewById(R.id.display_icon);
    }
}
