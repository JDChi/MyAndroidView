package com.ugiant.myandroidview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ugiant.myandroidview.R;

/**
 * Created by JDNew on 12/10/2016.
 */

public class SimpleArchView extends View {
    private float mCircleX;
    private float mCircleY;
    private float mRadius;
    private int measureWidth;
    private int measureHeight;
    private Paint mPaintCircle;
    private Paint mPaintText;
    private String text;
    private Paint mPaintArch;
    private RectF mRectF;



    public SimpleArchView(Context context, AttributeSet attrs) {
        super(context, attrs);





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth , measureHeight);
        initView();

    }

    private void initView() {
        mCircleX = measureWidth / 2;
        mCircleY = measureHeight / 2;
        mRadius = measureWidth / 4;

        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(Color.GRAY);

        mPaintText = new Paint();
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(60);
        mPaintText.setTextAlign(Paint.Align.CENTER);

        text = "hello world";

        mPaintArch = new Paint();
        mPaintArch.setStyle(Paint.Style.STROKE);
        mPaintArch.setStrokeWidth(20);
        mPaintArch.setColor(Color.GRAY);

        float topx = (float)(measureWidth / 2 - (mRadius + 30));
        float topy =  (float)(measureHeight / 2 - (mRadius + 30));
        float bottomx = (float)(measureWidth / 2 + (mRadius + 30));
        float bottomy = (float)(measureHeight / 2 + (mRadius + 30));
        mRectF = new RectF(
                topx ,
                topy ,
                bottomx ,
                bottomy
        );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCircleX , mCircleY , mRadius , mPaintCircle);

        canvas.drawText(text , mCircleX , mCircleY , mPaintText);

        canvas.drawArc(mRectF , 0 , 180 , false , mPaintArch);

    }


}
