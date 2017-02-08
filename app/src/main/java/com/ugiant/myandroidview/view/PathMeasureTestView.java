package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chijiaduo on 2017/2/8.
 */

public class PathMeasureTestView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    public PathMeasureTestView(Context context) {
        super(context);
        init();
    }

    public PathMeasureTestView(Context context, AttributeSet attrs) {
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
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2 , mHeight / 2);
        Path path = new Path();
        path.lineTo(0 , 200);
        path.lineTo(200 , 200);
        path.lineTo(200 , 0);

        PathMeasure pathMeasure1 = new PathMeasure(path , false);
        PathMeasure pathMeasure2 = new PathMeasure(path , true);

        Log.e("TAG", "forceClosed=false---->"+pathMeasure1.getLength());
        Log.e("TAG", "forceClosed=true----->"+pathMeasure2.getLength());

        canvas.drawPath(path , mPaint);


    }
}
