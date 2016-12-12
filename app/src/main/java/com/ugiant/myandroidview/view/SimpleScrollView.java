package com.ugiant.myandroidview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by JDNew on 12/11/2016.
 * 该scrollview的父布局应为LinearLayout，否则不会发生滑动
 */

public class SimpleScrollView extends ViewGroup {

    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private int mEnd;
    private Scroller mScroller;

    public SimpleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView , widthMeasureSpec , heightMeasureSpec);
        }

    }

    //确定子view的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        marginLayoutParams.height = mScreenHeight * childCount;
        setLayoutParams(marginLayoutParams);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                //onLayout一般是循环取出子View，
                // 然后经过计算得出各个子View位置的坐标值，
                // 然后用以下函数设置子View位置。
                child.layout(l , i * mScreenHeight , r , (i+1)*mScreenHeight);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if(getScrollY() < 0){
                    dy = 0;
                }
                if(getScrollY() > getHeight() - mScreenHeight){
                    dy = 0;
                }
                scrollBy(0 , dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:

//                int dScrollY = checkAlignment();
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY > 0){
                    if(dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(
                                0,
                                getScrollY(),
                                0,
                                -dScrollY
                        );

                    }else {
                        mScroller.startScroll(
                                0,
                                getScrollY(),
                                0,
                                mScreenHeight - dScrollY
                        );
                    }
                }else {
                    if(-dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(
                                0,
                                getScrollY(),
                                0,
                                -dScrollY
                        );
                    }else {
                        mScroller.startScroll(
                                0,
                                getScrollY(),
                                0,
                                -mScreenHeight - dScrollY
                        );
                    }

                }
                break;
        }
        postInvalidate();
        return true;
    }

    private int checkAlignment() {
        int mEnd = getScrollY();
        boolean isUp = (mEnd - mStart > 0)?true:false;
        int lastPrev = mEnd % mScreenHeight;
        int lastNext = mScreenHeight - lastPrev;
        if(isUp){
            return lastPrev;
        }else {
            return -lastNext;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0 , mScroller.getCurrY());
            postInvalidate();
        }
    }
}
