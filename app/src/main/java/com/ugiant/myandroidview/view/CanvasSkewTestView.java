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

public class CanvasSkewTestView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public CanvasSkewTestView(Context context) {
        super(context);
        init();
    }

    public CanvasSkewTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
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
        Rect rect = new Rect(0 , 0 , 200 , 200);
        canvas.drawRect(rect , mPaint);
        //x轴和y轴倾斜角度的tan值
        canvas.skew(1 , 0);
        canvas.drawRect(rect , mPaint);

    }


}
