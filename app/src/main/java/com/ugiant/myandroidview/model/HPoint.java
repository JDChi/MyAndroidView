package com.ugiant.myandroidview.model;

import android.graphics.PointF;

/**
 * Created by chijiaduo on 2017/1/20.
 */

public class HPoint {



    public  float x;
    public float y;
    public PointF left = new PointF();
    public PointF right = new PointF();

    public void setY(float y) {
        this.y = y;
        left.y = y;
        right.y = y;
    }

    public void adjustAllX(float offset){
        this.x = x + offset;
        left.x = left.x + offset;
        right.x = right.x + offset;
    }





}
