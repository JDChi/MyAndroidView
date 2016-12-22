package com.ugiant.myandroidview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ugiant.myandroidview.activity.BottomWayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_bottom_way;
    private LinearLayout activity_main;

//    private SimpleTopBar tb_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
//        tb_title = (SimpleTopBar) findViewById(R.id.tb_title);
//        tb_title.setOnTopBarClickListener(new SimpleTopBar.topbarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this , "左边" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(MainActivity.this , "右边" , Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void setListener() {
        tv_bottom_way.setOnClickListener(this);
    }

    private void initView() {
        tv_bottom_way = (TextView) findViewById(R.id.tv_bottom_way);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bottom_way:
startActivity(new Intent(this , BottomWayActivity.class));
                break;
        }
    }
}
