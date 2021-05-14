package com.example.drugology;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Pair;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        SystemClock.sleep(500);
        Intent intent = new Intent(splash_activity.this,MainActivity.class);

        startActivity(intent);
        finish();

    }
}
