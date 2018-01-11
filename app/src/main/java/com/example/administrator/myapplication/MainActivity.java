package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TimeView timeView;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat hour = new SimpleDateFormat("HH");
    SimpleDateFormat min = new SimpleDateFormat("mm");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView = findViewById(R.id.time);
        String a = hour.format(date.getTime());
        String b = min.format(date.getTime());
        timeView.setTime(00, Integer.valueOf(b), Integer.valueOf(a));
        timeView.start();
    }
}
