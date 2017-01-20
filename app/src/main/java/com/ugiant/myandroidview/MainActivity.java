package com.ugiant.myandroidview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ugiant.myandroidview.activity.GetStartActivity;
import com.ugiant.myandroidview.activity.PracticeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_get_start;
    private Button bt_practice;
    private RelativeLayout iv;
    private LinearLayout activity_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }


    private void setListener() {

    }


    private void initView() {
        bt_get_start = (Button) findViewById(R.id.bt_get_start);
        bt_practice = (Button) findViewById(R.id.bt_practice);

        bt_get_start.setOnClickListener(this);
        bt_practice.setOnClickListener(this);

        iv = (RelativeLayout) findViewById(R.id.iv);
        iv.setOnClickListener(this);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        activity_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_get_start:
                startActivity(new Intent(MainActivity.this, GetStartActivity.class));
                break;
            case R.id.bt_practice:
                startActivity(new Intent(MainActivity.this, PracticeActivity.class));

                break;
        }
    }
}
