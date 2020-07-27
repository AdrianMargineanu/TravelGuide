package com.trres.travelguide.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.trres.travelguide.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class NewTripActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.triplistsql.REPLY";
    private static final int PICK_IMAGE_REQUEST = 1 ;
    private EditText tripNameEditText;
    private EditText tripLocationEditText;
    private SeekBar seekBar;
    private TextView tripCostTextView;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private RatingBar ratingBar;

    private String tripName;
    private String tripLocation;
    private int tripCost;
    private byte[] imagine;
    private int yearStartDate;
    private int monthStartDate;
    private int dayStartDate;
    private int yearEndDate;
    private int monthEndDate;
    private int dayEndDate;
    private double rating;
    private int type;
    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yearStartDate  = -1;
        monthStartDate = -1;
        dayStartDate   = -1;
        yearEndDate    = -1;
        monthEndDate   = -1;
        dayEndDate     = -1;
        id = -1;
        setContentView(R.layout.activity_new_trip);
        tripLocationEditText = findViewById(R.id.trip_location);
        tripNameEditText = findViewById(R.id.trip_name);
        seekBar = findViewById(R.id.seekBar);
        tripCostTextView = findViewById(R.id.cost);
        setCost(seekBar.getMin());
        ratingBar = findViewById(R.id.ratingBar);
        Bundle bundle = getIntent().getExtras();
        startDateTextView = findViewById(R.id.start_date_text_view);
        endDateTextView = findViewById(R.id.end_date_tet_view);
        if(bundle != null && !bundle.isEmpty()){
            setForEdit(bundle);
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setCost(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                setCost(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setCost(seekBar.getProgress());
            }
        });
        final Button startDateButton = findViewById(R.id.start_date_button);
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              timeGetter(true);
            }
        });
        final Button endDateButton = findViewById(R.id.end_date_button);
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               timeGetter(false);
            }
        });

        final Button button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tripName = tripNameEditText.getText().toString();
                tripLocation = tripLocationEditText.getText().toString();
                rating = ratingBar.getRating();
                Intent reply = new Intent();
                if(!tripName.isEmpty() && !tripLocation.isEmpty() && imagine != null &&
                        yearStartDate  != -1 && monthStartDate != -1 && dayStartDate  != -1 &&
                            yearEndDate   != -1 && monthEndDate  != -1 && dayEndDate    != -1){
                    Log.e("New Trip","result Ok");
                    reply.putExtra(HomeFragment.NAME,tripName);
                    reply.putExtra(HomeFragment.LOCATION,tripLocation);
                    reply.putExtra(HomeFragment.PRICE,tripCost);
                    reply.putExtra(HomeFragment.IMAGE,imagine);
                    reply.putExtra(HomeFragment.RATING,rating);
                    reply.putExtra(HomeFragment.TYPE,type);
                    reply.putExtra(HomeFragment.YEAR_START_DATE,yearStartDate);
                    reply.putExtra(HomeFragment.YEAR_END_DATE,yearEndDate);
                    reply.putExtra(HomeFragment.MONTH_END_DATE,monthEndDate);
                    reply.putExtra(HomeFragment.MONTH_START_DATE,monthStartDate);
                    reply.putExtra(HomeFragment.DAY_START_DATE,dayStartDate);
                    reply.putExtra(HomeFragment.DAY_END_DATE,dayEndDate);
                    if(id != -1){
                        reply.putExtra(HomeFragment.ID,id);
                    }
                    setResult(RESULT_OK,reply);
                }else{
                    setResult(RESULT_CANCELED,reply);
                }
                finish();
            }
        });
    }

    public void LoadImagineOnClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imagine = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Log.e("requestcode","faild to load");
        }
    }

    public void setCost(int progress){
        tripCostTextView.setText("Cost: " + progress + "$");
        tripCost = progress;
    }

    public void radioButtonOnClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.city_break:
                if(checked){
                    type = 0;
                }
                break;
            case R.id.sea_side:
                if(checked){
                    type = 1;
                }
                break;
            case R.id.mountains:
                if(checked){
                    type = 2;
                }
                break;
        }

    }

    private void setTime(int year,int month,int day, boolean isStart){
        if(isStart){
            yearStartDate = year;
            monthStartDate = month;
            dayStartDate = day;
            startDateTextView.setText(year + "-" + month + "-" + day);
        }else{
            yearEndDate = year;
            monthEndDate = month;
            dayEndDate = day;
            endDateTextView.setText(year + "-" + month + "-" + day);
        }
    }

    public void timeGetter(boolean isStart){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(NewTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setTime(year,month,dayOfMonth,isStart);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void setForEdit(Bundle bundle){
        //TODO set var and the views
        tripName = bundle.getString(HomeFragment.NAME);
        tripNameEditText.setText(tripName);
        tripLocation = bundle.getString(HomeFragment.LOCATION);
        tripLocationEditText.setText(tripLocation);
        setCost(bundle.getInt(HomeFragment.PRICE));
        seekBar.setProgress(tripCost);
        setTime(bundle.getInt(HomeFragment.YEAR_START_DATE),
                bundle.getInt(HomeFragment.MONTH_START_DATE),
                bundle.getInt(HomeFragment.DAY_START_DATE),true);
        setTime(bundle.getInt(HomeFragment.YEAR_END_DATE),
                bundle.getInt(HomeFragment.MONTH_END_DATE),
                bundle.getInt(HomeFragment.DAY_END_DATE),false);
        imagine = bundle.getByteArray(HomeFragment.IMAGE);

        type = bundle.getInt(HomeFragment.TYPE);
        RadioButton radioButton;
        switch (type){
            case 0:
                radioButton = findViewById(R.id.city_break);
                break;
            case 1:
                radioButton = findViewById(R.id.sea_side);
                break;
            case 2:
                radioButton = findViewById(R.id.mountains);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        radioButton.setChecked(true);
        ratingBar.setRating((float) bundle.getDouble(HomeFragment.RATING));
        id = bundle.getInt(HomeFragment.ID);


    }
}
