package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JDNew on 12/11/2016.
 */

public class SimpleAudioBarChatView extends View {

    private int mWidth;
    private int mHeight;
    private int mBarCount = 20;
    private float mBarHeight;
    private Paint mBarFillPaint;
    private Paint mBarStrokePaint;
    private LinearGradient mLinearGradient;

    public SimpleAudioBarChatView(Context context){
        super(context , null);
    }

    public SimpleAudioBarChatView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBarStrokePaint = new Paint();
        mBarStrokePaint.setStyle(Paint.Style.STROKE);
        mBarStrokePaint.setStrokeWidth(6);
        mBarStrokePaint.setColor(Color.BLACK);

        mBarFillPaint = new Paint();
        mBarFillPaint.setStyle(Paint.Style.FILL);
        mBarFillPaint.setColor(Color.YELLOW);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(mWidth , mHeight);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLinearGradient = new LinearGradient(
                0 ,
                0 ,
                mWidth/mBarCount,
                mHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP

        );
        mBarFillPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0 ; i < mBarCount ; i ++){
            mBarHeight = (float) (Math.random() * mHeight);

            canvas.drawRect(mWidth/mBarCount*i , mBarHeight , mWidth/mBarCount*(i+1), mHeight , mBarFillPaint);
            canvas.drawRect(mWidth/mBarCount*i , mBarHeight , mWidth/mBarCount*(i+1), mHeight , mBarStrokePaint);



        }
        postInvalidateDelayed(300);
    }

    public void setmBarCount(int mBarCount) {
        this.mBarCount = mBarCount;
    }
}
