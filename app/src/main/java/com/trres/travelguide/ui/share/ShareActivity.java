package com.trres.travelguide.ui.share;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.trres.travelguide.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ShareFragment.newInstance())
                    .commitNow();
        }
    }
}
