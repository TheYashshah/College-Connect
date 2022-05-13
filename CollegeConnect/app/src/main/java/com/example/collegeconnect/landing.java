package com.example.collegeconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
//        getSupportActionBar().hide();
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(landing.this, login.class));
                finish();
            }
        }, 1000 );
    }
}