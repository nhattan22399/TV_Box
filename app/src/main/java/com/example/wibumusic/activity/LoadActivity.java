package com.example.wibumusic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.wibumusic.R;

public class LoadActivity extends AppCompatActivity {

    //THOI GIAN CHO 3 GIAY
    int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();


            }
        },SPLASH_TIME_OUT);
    }

}