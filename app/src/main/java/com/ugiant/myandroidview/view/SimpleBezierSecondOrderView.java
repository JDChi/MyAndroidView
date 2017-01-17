package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chijiaduo on 2017/1/17.
 */

public class SimpleBezierSecondOrderView extends View {
    private Paint mPaint;
    private int mCenterX , mCenterY;
    private Point start , end , control;


    public SimpleBezierSecondOrderView(Context context) {
        super(context);
        init();
    }



    public SimpleBezierSecondOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        start = new Point(0 , 0);
        end = new Point(0 , 0);
        control = new Point(0 , 0);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        start.x = mCenterX - 200;
        start.y = mCenterY;
        end.x = mCenterX + 200;
        end.y = mCenterY;
        control.x = mCenterX;
        control.y = mCenterY - 100;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = (int) event.getX();
        control.y = (int) event.getY();
        invalidate();

        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPoint(start.x , start.y , mPaint);
        canvas.drawPoint(end.x , end.y , mPaint);
        canvas.drawPoint(control.x , control.y , mPaint);

        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(start.x , start.y , control.x , control.y , mPaint);
        canvas.drawLine(end.x , end.y , control.x , control.y , mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(start.x , start.y);
        path.quadTo(control.x , control.y , end.x , end.y);

        canvas.drawPath(path , mPaint);


    }
}
