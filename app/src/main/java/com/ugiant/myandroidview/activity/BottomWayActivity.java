package com.ugiant.myandroidview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ugiant.myandroidview.R;

public class BottomWayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_tablayout;
    private LinearLayout activity_bottom_way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_way);
        initView();
        setListener();
    }

    private void setListener() {
        tv_tablayout.setOnClickListener(this);
    }

    private void initView() {
        tv_tablayout = (TextView) findViewById(R.id.tv_tablayout);
        activity_bottom_way = (LinearLayout) findViewById(R.id.activity_bottom_way);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tablayout:
                startActivity(new Intent(this , BottomTabTabLayoutActivity.class));
                break;
        }
    }
}
