package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by JDNew on 12/4/2016.
 */

public class SimpleLinearGradientTextView extends TextView {

    private int mViewWidth;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private Paint mPaint;
    private float translate;
    public SimpleLinearGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0){
                mPaint = getPaint();
                linearGradient = new LinearGradient(0 , 0 , mViewWidth , 0 ,
                        new int[]{Color.BLUE , 0xffffffff , Color.BLUE} , null , Shader.TileMode.CLAMP);
                mPaint.setShader(linearGradient);
                matrix = new Matrix();

            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix != null){
            translate += mViewWidth / 5;
            if(translate > 2 * mViewWidth){
                translate = -mViewWidth;
            }
            matrix.setTranslate(translate , 0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }
}
