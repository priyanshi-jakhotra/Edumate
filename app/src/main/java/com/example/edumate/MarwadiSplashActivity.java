package com.example.edumate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class MarwadiSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marwadi_splash);

        // Delay for 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the OLD SplashActivity
                Intent intent = new Intent(MarwadiSplashActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
