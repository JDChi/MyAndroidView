package com.ugiant.myandroidview.activity.activity_practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.demo_view.MagicCircle;

/**
 * Created by chijiaduo on 2017/1/20.
 */

public class MagicCircleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_start;
    private MagicCircle magic_circle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_circle);
        initView();
    }

    private void initView() {
        bt_start = (Button) findViewById(R.id.bt_start);
        magic_circle = (MagicCircle) findViewById(R.id.magic_circle);

        bt_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
magic_circle.startAnimation();
                break;
        }
    }
}
