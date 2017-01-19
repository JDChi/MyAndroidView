package com.ugiant.myandroidview.activity.activity_practice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chijiaduo on 2017/1/18.
 */

public class SwitchLocationView extends LinearLayout {

    private TextView tv_start;
    private TextView tv_end;
    private ImageView iv_switch;


    public SwitchLocationView(Context context) {
        super(context);
    }

    public SwitchLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
