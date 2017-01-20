package com.ugiant.myandroidview.activity.activity_practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ugiant.myandroidview.R;

/**
 * Created by chijiaduo on 2017/1/19.
 */

public class SwitchLocationViewActivity extends AppCompatActivity {

    private TextView tv1;
    private ImageView iv;
    private TextView tv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_location);
        initView();
        setListener();
    }



    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        iv = (ImageView) findViewById(R.id.iv);
        tv2 = (TextView) findViewById(R.id.tv2);
    }

    private void setListener() {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
