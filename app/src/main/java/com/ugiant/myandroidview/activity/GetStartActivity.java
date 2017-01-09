package com.ugiant.myandroidview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.activity.activity_get_start.AudioBarChatViewActivity;
import com.ugiant.myandroidview.activity.activity_get_start.TextViewActivity;
import com.ugiant.myandroidview.activity.activity_get_start.TopBarActivity;
import com.ugiant.myandroidview.activity.activity_get_start.ViewActivity;

/**
 * Created by chijiaduo on 2017/1/6.
 */

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_text_view;
    private Button bt_view;
    private Button bt_top_bar;
    private Button bt_audio_bar_chat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        initView();
    }

    private void initView() {
        bt_text_view = (Button) findViewById(R.id.bt_text_view);

        bt_text_view.setOnClickListener(this);
        bt_view = (Button) findViewById(R.id.bt_view);
        bt_view.setOnClickListener(this);
        bt_top_bar = (Button) findViewById(R.id.bt_top_bar);
        bt_top_bar.setOnClickListener(this);
        bt_audio_bar_chat = (Button) findViewById(R.id.bt_audio_bar_chat);
        bt_audio_bar_chat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_text_view:
                startActivity(new Intent(GetStartActivity.this, TextViewActivity.class));
                break;
            case R.id.bt_view:
                startActivity(new Intent(GetStartActivity.this, ViewActivity.class));
                break;
            case R.id.bt_top_bar:
                startActivity(new Intent(GetStartActivity.this, TopBarActivity.class));
                break;
            case R.id.bt_audio_bar_chat:
                startActivity(new Intent(GetStartActivity.this, AudioBarChatViewActivity.class));
                break;
        }
    }
}
