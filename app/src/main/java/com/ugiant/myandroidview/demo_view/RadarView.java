package com.ugiant.myandroidview.demo_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chijiaduo on 2017/1/13.
 */

public class RadarView extends View {

    private int mCount = 6;

    //利用弧度来表示角度
    private double angle = Math.PI * 2 / mCount;
    private int mCenterX;
    private int mCenterY;
    private float radius;
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mCorverPaint;
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(25);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);

        mCorverPaint = new Paint();
        mCorverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCorverPaint.setColor(Color.parseColor("#dd514c"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) / 2 * 0.9f;
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        float r = radius / (mCount - 1);
        for (int i = 1; i < mCount; i++) {

            float currentR = r * i;
            path.reset();
            for (int j = 0; j < mCount; j++) {
                if (j == 0) {
                    path.moveTo(mCenterX + currentR, mCenterY);//即以中心点的右边开始算起
                } else {
                    float x = (float) (mCenterX + currentR * Math.cos(angle * j));
                    float y = (float) (mCenterY + currentR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mLinePaint);
        }

        for (int i = 0; i < mCount; i++) {
            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + radius * Math.cos(angle * i));
            float y = (float) (mCenterY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mLinePaint);
        }

        //写文字
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < mCount; i++) {
            float x = (float) (mCenterX + radius * Math.cos(angle * i));
            float y = (float) (mCenterY + radius * Math.sin(angle * i));
            //第四象限
            if (angle * i == 0) {
                canvas.drawText("text1", x, y, mTextPaint);
            } else if (angle * i > 0 && angle * i <= Math.PI / 2) {
                canvas.drawText("text1", x, y + fontHeight, mTextPaint);
            }
            //第三象限
            else if (angle * i > Math.PI / 2 && angle * i < Math.PI) {
                canvas.drawText("text1", x, y + fontHeight, mTextPaint);
            } else if (angle * i == Math.PI) {
                canvas.drawText("text1", x - mTextPaint.measureText("text1"), y, mTextPaint);
            }
            //第二象限
            else if (angle * i > Math.PI && angle * i <= Math.PI * 3 / 2) {
                canvas.drawText("text1", x, y, mTextPaint);
            }
            //第一象限
            else if (angle * i > Math.PI * 3 / 2 && angle * i <= 2 * Math.PI) {
                canvas.drawText("text1", x, y, mTextPaint);
            }
        }

        double percent;


        float x;
        float y;
        Path corverPath = new Path();
        for (int i = 0; i < mCount; i++) {
            percent = data[i] / maxValue;
            x = (float) (mCenterX + radius * Math.cos(angle * i) * percent);
            y = (float) (mCenterY + radius * Math.sin(angle * i) * percent);

            if (i == 0) {
                corverPath.moveTo(x, mCenterY);
            } else {
                corverPath.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, mCorverPaint);

        }

        mCorverPaint.setColor(Color.parseColor("#60dd514c"));
        canvas.drawPath(corverPath, mCorverPaint);


    }
}
