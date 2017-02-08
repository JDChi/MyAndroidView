package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chijiaduo on 2017/2/8.
 */

public class CanvasRotateTestView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public CanvasRotateTestView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
    }

    public CanvasRotateTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
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
//        Rect rect = new Rect(0 , -400 , 400 , 0);
//        canvas.drawRect(rect , mPaint);

//        canvas.rotate(180);
//        canvas.rotate(180 , 200 , 0);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rect , mPaint);

        canvas.drawCircle(0 , 0 , 100 , mPaint);
        canvas.drawCircle(0 , 0 , 80 , mPaint);

        for (int i = 0 ; i <= 360 ; i = i + 10){
            canvas.drawLine(0 , 100 , 0 , 80 , mPaint);
            canvas.rotate(10);
        }
    }
}
