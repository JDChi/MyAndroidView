package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JDNew on 11/28/2016.
 */

public class SimpleView extends View {
    public SimpleView(Context context) {
        super(context);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 首先这两个参数都是来自于MeasureSpec类，
     * 它是一个32位的int值，高2位为测量模式，低30位为测量的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 如果不重写该方法，默认使用EXACTLY模式，即它会响应具体值和match_parent，
     * 并且当我们对该控件使用warp_content的时候，它是会默认填充整个布局的
     *
     * 而要真正实现我们意义上的warp_content的话就必须重写该方法，相对应的就是AT_MOST模式
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //当对onMeasure进行重写的时候，最后要调用的是setMeasureDimension方法，所以把super注释掉
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMeasureSpec) , measureHeight(heightMeasureSpec));


    }

    private int measureHeight(int heightMeasureSpec){

        int result = 0 ;
        //如上所说，widthMeasureSpec是由两部分构成，所以它的大小和模式都可以通过
        //已经封装好的getMode和getSize获取
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            //返回测量得到的精确大小
            result = specSize;
        }else {
            result = 200;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result , specSize);
            }
        }

        return result;

    }

    private int measureWidth(int widthMeasureSpec){

        int result = 0 ;
        //如上所说，widthMeasureSpec是由两部分构成，所以它的大小和模式都可以通过
        //已经封装好的getMode和getSize获取
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            //返回测量得到的精确大小
            result = specSize;
        }else {
            result = 200;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result , specSize);
            }
        }

        return result;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
