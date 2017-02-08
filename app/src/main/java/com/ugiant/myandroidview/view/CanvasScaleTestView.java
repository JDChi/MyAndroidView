package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chijiaduo on 2017/2/6.
 */

public class CanvasScaleTestView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    public CanvasScaleTestView(Context context) {
        super(context);
        init();
    }

    public CanvasScaleTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
       mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec , heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2 , mHeight / 2);
        Rect rect = new Rect(0 , -400 , 400 , 0);
        canvas.drawRect(rect , mPaint);

        //缩放中心默认为原点
//        canvas.scale(0.5f , 0.5f);
        //缩放中心右移200个位置
        //canvas.scale(0.5f , 0.5f , 200 , 0);
        //为负数时，根据缩放中心进行翻转
//        canvas.scale(-0.5f , -0.5f);
//        canvas.scale(-0.5f , -0.5f , 200 , 0);

//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rect , mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
        for (int i = 0 ; i < 20 ; i++){
            canvas.scale(0.9f , 0.9f , 200 , -200);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(rect , mPaint);
        }



    }
}
