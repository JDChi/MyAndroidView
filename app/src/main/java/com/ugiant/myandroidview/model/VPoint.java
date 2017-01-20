package com.ugiant.myandroidview.model;

import android.graphics.PointF;

/**
 * Created by chijiaduo on 2017/1/20.
 */

public class VPoint {


    public float x;
    public float y;
    public PointF top = new PointF();
    public PointF bottom = new PointF();

    public void setX(float x) {
        this.x = x;
        top.x = x;
        bottom.x = x;
    }

    public void adjustAllX(float offset){
        this.x = x + offset;
        top.x = top.x + offset;
        bottom.x = bottom.x + offset;
    }

    public void adjustY(float offset){
        top.y = top.y - offset;
        bottom.y = bottom.y + offset;
    }


}
