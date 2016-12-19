package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JDNew on 12/13/2016.
 * 切记要关闭硬件加速
 */

public class SimplePathTestView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public SimplePathTestView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将原点移动正中间，起点会默认为从原点开始
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
//这一部分为简单的画线（开始）
//        path.lineTo(200 , 200);
        //移动下一次操作的起点位置
//        path.moveTo(200 , 100);
        //设置之前操作的最后一个点的位置
//        path.setLastPoint(200 , 100);

//        path.lineTo(200 , 0);
        //将当前的点跟最初的点连起来，形成闭合
//        path.close();
//这一部分为简单的画线（结束）


        //这一部分为基本形状，跟之前的用canvas直接draw一样（开始）
        //顺时针
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        //逆时针
//        path.addRect(-200 , -200 , 200 , 200 , Path.Direction.CCW);
//        path.setLastPoint(-300, 300);
        //这一部分为基本形状，跟之前的用canvas直接draw一样（结束）

        //将两个Path合并成为一个（开始）
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        Path src = new Path();
//        src.addCircle(0 , 0 , 100 , Path.Direction.CW);
//        path.addPath(src , 0 , -200);
        //将两个Path合并成为一个（结束）


        //addArc与arcTo
        canvas.scale(1 , -1); //翻转y坐标轴
        path.lineTo(100 , 100);
        RectF oval = new RectF(0 , 0 , 300 , 300);
        //addArc 从零度开始，逆时针转270度，参考文章5
//        path.addArc(oval , 0 , 270 );
        // 与上一句相同
//        path.arcTo(oval , 0 , 270 , true);
        //arcTo 连接圆弧的起点和上次的最后一个点
//        path.arcTo(oval , 0 , 270);

//        path.arcTo(oval , 0 , 270 , false);


        canvas.drawPath(path, mPaint);
    }
}
