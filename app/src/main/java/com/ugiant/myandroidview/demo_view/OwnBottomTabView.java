package com.ugiant.myandroidview.demo_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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


    public OwnBottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(Color.WHITE);

        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setColor(Color.YELLOW);


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
        Rect rect = new Rect(0 , 0 , mWidth , mHeight);
        canvas.drawRect(rect , mRectPaint);
        RectF rectf = new RectF(50 , 0 , mWidth-50 , mHeight);
        canvas.drawArc(rectf , 0 , 180 , true , mArcPaint);
    }
}
