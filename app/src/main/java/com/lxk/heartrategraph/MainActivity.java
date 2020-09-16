package com.lxk.heartrategraph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lxk.heartrate.HeartRateGraphWidget;
import com.lxk.heartrate.bean.HeartRateBean;

public class MainActivity extends AppCompatActivity {

    HeartRateGraphWidget heartRateGraphWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartRateGraphWidget = findViewById(R.id.heart_rate);
        heartRateGraphWidget.setOnItemSelectCallback(
                new HeartRateGraphWidget.onItemSelectCallback() {
                    @Override
                    public void onItemSelected(HeartRateBean heartRateBean) {
                        System.out.println(heartRateBean.toString());
                    }
                });

        findViewById(R.id.day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartRateGraphWidget.setCurShowType(HeartRateGraphWidget.WEEK);
                heartRateGraphWidget.reset();
                heartRateGraphWidget.postInvalidate();
            }
        });

        findViewById(R.id.minute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartRateGraphWidget.setCurShowType(HeartRateGraphWidget.DAY);
                heartRateGraphWidget.reset();
                heartRateGraphWidget.postInvalidate();
            }
        });
    }


}