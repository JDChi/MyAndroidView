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

public class SimpleBezierThirdOrderView extends View {

    private Paint mPaint;
    private int mCenterX , mCenterY;
    private Point start , end , control1 , control2;
    private boolean mode = true;


    public SimpleBezierThirdOrderView(Context context, AttributeSet attrs) {
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
        control1 = new Point(0, 0);
        control2 = new Point(0 , 0);

    }

    public SimpleBezierThirdOrderView(Context context) {
        super(context);

       init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = w / 2;

        start.x = mCenterX - 200;
        start.y = mCenterY;
        end.x = mCenterX + 200;
        end.y = mCenterY;
        control1.x = mCenterX;
        control1.y = mCenterY - 100;
        control2.x = mCenterX;
        control2.y = mCenterY + 100;



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mode){
            control1.x = (int) event.getX();
            control1.y = (int) event.getY();

        }else {
            control2.x = (int) event.getX();
            control2.y = (int) event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        canvas.drawPoint(start.x , start.y , mPaint);
        canvas.drawPoint(end.x , end.y , mPaint);
        canvas.drawPoint(control1.x , control1.y , mPaint);
        canvas.drawPoint(control2.x , control2.y , mPaint);

        canvas.drawLine(start.x , start.y , control1.x , control1.y , mPaint);
        canvas.drawLine(control1.x , control1.y , control2.x , control2.y , mPaint);
        canvas.drawLine(end.x , end.y , control2.x , control2.y , mPaint);

        mPaint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(start.x , start.y);
        path.cubicTo(control1.x , control1.y , control2.x , control2.y , end.x , end.y);

        canvas.drawPath(path , mPaint);


    }
}
