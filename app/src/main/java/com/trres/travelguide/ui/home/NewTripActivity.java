package com.trres.travelguide.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.trres.travelguide.ui.home.room.Trip;
import com.trres.travelguide.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class NewTripActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.triplistsql.REPLY";
    private static final int PICK_IMAGE_REQUEST = 1 ;
    private EditText tripNameEditText;
    private EditText tripLocationEditText;
    private EditText tripCostEditText;

    private String tripName;
    private String tripLocation;
    private int tripCost;
    private byte[] imagine;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        tripCostEditText = findViewById(R.id.trip_cost);
        tripLocationEditText = findViewById(R.id.trip_location);
        tripNameEditText = findViewById(R.id.trip_name);

        final Button button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean validation = true;
                tripName = tripNameEditText.getText().toString();
                tripLocation = tripLocationEditText.getText().toString();
                try{
                    tripCost = Integer.parseInt(tripCostEditText.getText().toString());
                }catch (Exception exception){
                    tripCostEditText.setError("Please enter a number");
                    validation = false;
                }
                if(imagine == null){
                }
                Intent reply = new Intent();
                if(!tripName.isEmpty() && !tripLocation.isEmpty() && validation && imagine != null){
                    Log.e("New Trip","result Ok");
                    reply.putExtra(HomeFragment.NAME,tripName);
                    reply.putExtra(HomeFragment.LOCATION,tripLocation);
                    reply.putExtra(HomeFragment.PRICE,tripCost);
                    reply.putExtra(HomeFragment.IMAGE,imagine);
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


}
