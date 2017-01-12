package com.ugiant.myandroidview.activity.activity_practice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.base.BaseActivity;
import com.ugiant.myandroidview.demo_view.LeafLoadingView;

import java.util.Random;

/**
 * Created by chijiaduo on 2017/1/9.
 */

public class LeafLoadingActivity extends BaseActivity {


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
                   mProgress++;
                   // 随机800ms以内刷新一次
                   mHandler.sendEmptyMessageDelayed(1,
                           new Random().nextInt(800));
                   leafLoadingView.setmCurrentProgress(mProgress);
                   break;
           }

        }
    };

    private LeafLoadingView leafLoadingView;
    private int mProgress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_loading);

        leafLoadingView = (LeafLoadingView) findViewById(R.id.leafLoadingView);

        mHandler.sendEmptyMessageDelayed(1 , 3000);



    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }
}
