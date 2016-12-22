package com.ugiant.myandroidview.demo_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JDNew on 12/19/2016.
 */

public class OwnBottomTabView extends View {
    private Paint mRectPaint;
    private int mWidth;
    private int mHeight;
    private Paint mArcPaint;
    private Path mPath;
    private Point mStartPoint , mEndPoint , mControlPoint;



    public OwnBottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(20);
        mRectPaint.setColor(Color.BLACK);

        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setColor(Color.YELLOW);


        mPath = new Path();



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Rect rect = new Rect(0 , 0 , mWidth , mHeight);
//        canvas.drawRect(rect , mRectPaint);
//        RectF rectf = new RectF(50 , 0 - mHeight/2 , mWidth-50 , 0);
//        canvas.drawArc(rectf , 0 , 180 , true , mArcPaint);

        mStartPoint = new Point(50 , 0);
        mEndPoint = new Point(mWidth - 50 , 0);
        mControlPoint = new Point(mWidth * 2 / 3 , mHeight * 2 / 3);
        mPath.lineTo(50 , 0);
        mPath.moveTo(50 , 0);
        mPath.quadTo(mControlPoint.x , mControlPoint.y , mEndPoint.x , mEndPoint.y);
        mPath.moveTo(mWidth - 50 , 0);
        mPath.lineTo(mWidth , 0);

        canvas.drawPath(mPath , mRectPaint);
    }
}
