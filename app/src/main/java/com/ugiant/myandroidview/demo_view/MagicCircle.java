package com.ugiant.myandroidview.demo_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.ugiant.myandroidview.model.HPoint;
import com.ugiant.myandroidview.model.VPoint;

/**
 * Created by chijiaduo on 2017/1/19.
 */

public class MagicCircle extends View {
    private Path mPath;
    private Paint mFillCirclePaint;
    private float mRadius;

    private int mWidth, mHeight;
    private int mCenterX, mCenterY;
    private float blackMagic = 0.551915024494f;
    private float C;
    private float stretchDistance;
    private float moveDistance;
    private float cDistance;
    private float mMaxLength;
    private float mInterpolatedTime;
    private VPoint p2, p4;
    private HPoint p1, p3;


    public MagicCircle(Context context) {
        super(context);
        init();
    }

    public MagicCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(Color.RED);
        mFillCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFillCirclePaint.setAntiAlias(true);

        p2 = new VPoint();
        p4 = new VPoint();

        p1 = new HPoint();
        p3 = new HPoint();


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mRadius = 50;
        C = mRadius * blackMagic;
        stretchDistance = mRadius;
        moveDistance = mRadius * (3 / 5f);
        cDistance = C * 0.45f;
        mMaxLength = mWidth - mRadius - mRadius;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //画布平移
        canvas.translate(mRadius, mRadius);
        if (mInterpolatedTime >= 0 && mInterpolatedTime <= 0.2) {
            model1(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.2 && mInterpolatedTime <= 0.5) {
            model2(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.5 && mInterpolatedTime <= 0.8) {
            model3(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.8 && mInterpolatedTime <= 0.9) {
            model4(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.9 && mInterpolatedTime <= 1) {
            model5(mInterpolatedTime);
        }

        float offset = mMaxLength * (mInterpolatedTime - 0.2f);
//       if(offset > 0){
//           offset = 0;
//       }

        offset = offset>0?offset:0;
        p1.adjustAllX(offset);
        p2.adjustAllX(offset);
        p3.adjustAllX(offset);
        p4.adjustAllX(offset);

        mPath.moveTo(p1.x , p1.y);
        mPath.cubicTo(p1.right.x , p1.right.y , p2.bottom.x , p2.bottom.y , p2.x , p2.y);
        mPath.cubicTo(p2.top.x , p2.top.y , p3.right.x , p3.right.y , p3.x , p3.y);
        mPath.cubicTo(p3.left.x , p3.left.y , p4.top.x , p4.top.y , p4.x , p4.y);
        mPath.cubicTo(p4.bottom.x , p4.bottom.y , p1.left.x , p1.left.y , p1.x , p1.y);

        canvas.drawPath(mPath , mFillCirclePaint);


    }


    private void model0() {
        p1.setY(mRadius);
        p3.setY(-mRadius);
        p3.x = p1.x = 0;
        p3.left.x = p1.left.x = -C;
        p3.right.x = p1.right.x = C;

        p2.setX(mRadius);
        p4.setX(-mRadius);
        p2.y = p4.y = 0;
        p2.top.y = p4.top.y = -C;
        p2.bottom.y = p4.bottom.y = C;
    }


    private void model1(float mInterpolatedTime) {
        model0();
        p2.setX(mRadius + stretchDistance * mInterpolatedTime * 5);
    }

    private void model2(float mInterpolatedTime) {
        model1(0.2f);
        mInterpolatedTime = (mInterpolatedTime - 0.2f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
        p3.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
        p2.adjustY(cDistance * mInterpolatedTime);
        p4.adjustY(cDistance * mInterpolatedTime);


    }


    private void model3(float mInterpolatedTime) {
        model2(0.5f);
        mInterpolatedTime = (mInterpolatedTime - 0.5f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
        p3.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
        p2.adjustY(-cDistance * mInterpolatedTime);
        p4.adjustY(-cDistance * mInterpolatedTime);

        p4.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
    }


    private void model4(float mInterpolatedTime) {
        model3(0.8f);
        mInterpolatedTime = (mInterpolatedTime - 0.8f) * 10;
        p4.adjustAllX(stretchDistance / 2 * mInterpolatedTime);
    }

    private void model5(float mInterpolatedTime) {
        model4(0.9f);
        mInterpolatedTime = mInterpolatedTime - 0.9f;
        p4.adjustAllX((float) (Math.sin(Math.PI * mInterpolatedTime * 10f) *    (2 / 10f * mRadius)));
    }

    public void startAnimation(){
        mPath.reset();
        mInterpolatedTime = 0;
        MoveAnimation move = new MoveAnimation();
        move.setDuration(1000);
        move.setInterpolator(new AccelerateDecelerateInterpolator());
        startAnimation(move);
    }

    private class MoveAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            invalidate();
        }
    }


}
