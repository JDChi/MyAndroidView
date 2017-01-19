package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 利用三阶贝塞尔曲线画心形
 */

public class SimpleBezierHeartThirdOrderView extends View {

    private final float C = 0.551915024494f;
    private Paint mPaint;
    private int mCenterX, mCenterY;
    private PointF mCenter = new PointF();
    private float mCircleRadius = 200;
    private float mDifference = mCircleRadius * C;
    private float[] mData = new float[8];  //四个数据点，因为一个点有x和y坐标，所以需要八个
    private float[] mCtrl = new float[16]; //同样的八个控制点，就有16个数据

    private float mDuration = 1000;
    private float mCurrent = 0;
    private float mCount = 100;
    private float mPiece = mDuration / mCount;
    private PointF topPoint , bottomPoint , leftPoint , rightPoint;
    private PointF topLeftCtrlPoint , topRightCtrlPoint , bottomLeftCtrlPoint , bottomRightCtrlPoint,
                   leftTopCtrlPoint , leftBottomCtrlPoint , rightTopCtrlPoint , rightBottomCtrlPoint;

    public SimpleBezierHeartThirdOrderView(Context context) {
        super(context);
        init();
    }

    public SimpleBezierHeartThirdOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);


        mData[0] = 0;
        mData[1] = mCircleRadius;
        mData[2] = mCircleRadius;
        mData[3] = 0;
        mData[4] = 0;
        mData[5] = -mCircleRadius;
        mData[6] = -mCircleRadius;
        mData[7] = 0;





        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];
        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;
        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;
        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];
        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];
        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;
        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;
        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);

        canvas.translate(mCenterX , mCenterY);
        canvas.scale(1 , -1);


        drawAssistLine(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        topPoint = new PointF(mData[0] , mData[1]);
        topLeftCtrlPoint = new PointF(mCtrl[12] , mCtrl[13]);
        topRightCtrlPoint = new PointF(mCtrl[14] , mCtrl[15]);

        rightPoint = new PointF(mData[2] , mData[3]);
        rightTopCtrlPoint = new PointF(mCtrl[0] , mCtrl[1]);
        rightBottomCtrlPoint = new PointF(mCtrl[2] , mCtrl[3]);

        bottomPoint = new PointF(mData[4] , mData[5]);
        bottomLeftCtrlPoint = new PointF(mCtrl[4] , mCtrl[5]);
        bottomRightCtrlPoint = new PointF(mCtrl[6] , mCtrl[7]);


        leftPoint = new PointF(mData[6] , mData[7]);
        leftTopCtrlPoint = new PointF(mCtrl[8] , mCtrl[9]);
        leftBottomCtrlPoint = new PointF(mCtrl[10] , mCtrl[11]);







        path.moveTo(topPoint.x , topPoint.y);

        path.cubicTo(rightTopCtrlPoint.x , rightTopCtrlPoint.y ,rightBottomCtrlPoint.x , rightBottomCtrlPoint.y , rightPoint.x , rightPoint.y);
        path.cubicTo(bottomLeftCtrlPoint.x , bottomLeftCtrlPoint.y , bottomRightCtrlPoint.x , bottomRightCtrlPoint.y , bottomPoint.x , bottomPoint.y);
        path.cubicTo(leftTopCtrlPoint.x , leftTopCtrlPoint.y , leftBottomCtrlPoint.x , leftBottomCtrlPoint.y , leftPoint.x , leftPoint.y);
        path.cubicTo(topLeftCtrlPoint.x , topLeftCtrlPoint.y , topRightCtrlPoint.x , topRightCtrlPoint.y , topPoint.x , topPoint.y);

        //画出圆形
        canvas.drawPath(path , mPaint);

        mCurrent = mCurrent + mPiece;
        if(mCurrent < mDuration){
            mData[1] = mData[1] - 120/mCount;
            mCtrl[7] = mCtrl[7] + 80/mCount;
            mCtrl[9] = mCtrl[9] + 80/mCount;
            mCtrl[4] = mCtrl[4] - 20/mCount;
            mCtrl[10] = mCtrl[10] + 20/mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }

    private void drawAssistLine(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
        //画出四个数据点
        for (int i = 0; i < 8; i = i + 2) {
            canvas.drawPoint(mData[i] , mData[i + 1] , mPaint);
        }

        //画出八个控制点
        for (int i = 0; i < 16; i = i + 2) {
            canvas.drawPoint(mCtrl[i] , mCtrl[i + 1] , mPaint);
        }

        mPaint.setStrokeWidth(4);
        for (int i = 2 , j = 2; i < 8; i = i + 2 , j = j + 4) {
            canvas.drawLine(mData[i] , mData[i + 1] , mCtrl[j] , mCtrl[j + 1] , mPaint);
            canvas.drawLine(mData[i] , mData[i + 1] , mCtrl[j + 2] , mCtrl[j + 3] , mPaint);
        }

        canvas.drawLine(mData[0] , mData[1] , mCtrl[0] , mCtrl[1] , mPaint);
        canvas.drawLine(mData[0] , mData[1] , mCtrl[14] , mCtrl[15] , mPaint);


    }

    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();
        canvas.translate(mCenterX , mCenterY);
        canvas.scale(1 , -1);

        Paint assistPaint = new Paint();
        assistPaint.setColor(Color.RED);
        assistPaint.setStrokeWidth(5);
        assistPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0 , -2000 , 0 , 2000 , assistPaint);
        canvas.drawLine(-2000 , 0 , 2000 , 0 , assistPaint);

        //restore和save要配对使用，restore可以比save少，但不能多。
        //save用于保存上一次的状态，然后就对画布进行操作，操作完后再调用restore返回上一次的那个状态
        canvas.restore();
    }
}
