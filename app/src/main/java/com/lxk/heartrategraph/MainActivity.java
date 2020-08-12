package com.lxk.heartrategraph;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lxk.heartrate.HeartRateGraphWidget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HeartRateGraphWidget heartRateGraphWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartRateGraphWidget = findViewById(R.id.heart_rate);
    }


}