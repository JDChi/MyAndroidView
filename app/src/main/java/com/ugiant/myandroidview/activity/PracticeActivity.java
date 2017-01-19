package com.ugiant.myandroidview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.activity.activity_get_start.TextViewActivity;
import com.ugiant.myandroidview.activity.activity_practice.LeafLoadingActivity;
import com.ugiant.myandroidview.activity.activity_practice.RadarViewActivity;

/**
 * Created by chijiaduo on 2017/1/6.
 */

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_leaf_loading;
    private Button bt_radar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        initView();
    }

    private void initView() {
        bt_leaf_loading = (Button) findViewById(R.id.bt_leaf_loading);
        bt_radar = (Button) findViewById(R.id.bt_radar);

        bt_leaf_loading.setOnClickListener(this);
        bt_radar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_leaf_loading:
                startActivity(new Intent(PracticeActivity.this, LeafLoadingActivity.class));
                break;
            case R.id.bt_radar:
                startActivity(new Intent(PracticeActivity.this, RadarViewActivity.class));
                break;
        }
    }
}
