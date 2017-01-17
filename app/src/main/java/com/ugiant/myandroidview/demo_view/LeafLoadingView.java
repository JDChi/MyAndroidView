package com.ugiant.myandroidview.demo_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.model.Leaf;
import com.ugiant.myandroidview.model.LeafFactory;
import com.ugiant.myandroidview.util.UiUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by chijiaduo on 2017/1/9.
 */

public class LeafLoadingView extends View {
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private int outerWidth;
    private int outerHeight;
    private Bitmap outerBitmap;
    private Paint bitmapPaint;
    private Rect outerSrcRect;
    private Rect outerDesRect;

    private Bitmap leafBitmap;
    private int leafWidth;
    private int leafHeight;
    /**
     * 当前进度
     */
    private int mCurrentProgress;
    /**
     * 总进度
     */
    private final int TOTAL_PROGRESS = 100;
    /**
     * 当前进度条在水平方向上的位置
     */
    private int mCurrentProgressHorizontalPosition;
    private int mProgressWidth;
    //进度条距离左，上，底部的距离，即外框背景图的边框
    private final int LEFT_TOP_BOTTOM_MARGIN = 9;
    //进度条距离右边的距离，即外框背景图右边部分，因为要放风扇，所以距离增加
    private final int RIGHT_MARGIN = 25;
    //以下两个是由上面转换而来的，这个才是我们真正用到的
    private int mLeftTopBottomMargin;
    private int mRightMargin;
    private int mArcRadius;
    private RectF mArcRectF;
    /**
     * 弧线右上角的点的位置，也就是矩形的左上角起始的点
     */
    private int mArcRightLocation;
    private Paint mBackgroundWhitePaint;
    /**
     * 右边的白色矩形
     */
    private RectF mBackgroundWhiteRectF;

    private Paint mProgressOrangePaint;
    /**
     * 右边的进度条的矩形
     */
    private RectF mProgressOrangeRectF;

    // 淡白色
    private static final int COLOR_WHITE = 0xfffde399;
    // 橙色
    private static final int COLOR_ORANGE = 0xffffa800;
    /**
     * 叶子旋转一周的时间
     */
    private long mLeafRotateTime = 2000;

    private List<Leaf> mLeafList;
    private LeafFactory leafFactory;




    public LeafLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        //根据屏幕把dp转成px
        mLeftTopBottomMargin = UiUtils.dipToPx(context, LEFT_TOP_BOTTOM_MARGIN);
        mRightMargin = UiUtils.dipToPx(context, RIGHT_MARGIN);

        initOuter();
        initLeaf();
        initBackgroundWhitePaint();
        initProgressOrangePaint();


        leafFactory = new LeafFactory();
        mLeafList = leafFactory.generateDefaultLeafs();

    }


    private void initBackgroundWhitePaint() {
        mBackgroundWhitePaint = new Paint();
        mBackgroundWhitePaint.setAntiAlias(true);
        mBackgroundWhitePaint.setColor(COLOR_WHITE);
    }

    private void initProgressOrangePaint() {
        mProgressOrangePaint = new Paint();
        mProgressOrangePaint.setAntiAlias(true);
        mProgressOrangePaint.setColor(COLOR_ORANGE);
    }


    /**
     * 初始化外框图片和画笔
     */
    private void initOuter() {
        outerBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.leaf_kuang);
        bitmapPaint = new Paint();
        //使用抗锯齿功能
        bitmapPaint.setAntiAlias(true);
        //过滤掉图像的优化操作
        bitmapPaint.setFilterBitmap(true);
        //使用图像抖动处理
        bitmapPaint.setDither(true);
    }

    private void initLeaf() {
        leafBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.leaf);
        leafWidth = leafBitmap.getWidth();
        leafHeight = leafBitmap.getHeight();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        setOuterSrcAndDesRect();
        //进度条的宽度就是总的宽度减去左边界和右边界
        mProgressWidth = mWidth - mLeftTopBottomMargin - mRightMargin;
        //左边弧度的半径就是总的高度，减去上下的边界，中间部分的空白除以2
        mArcRadius = (mHeight - 2 * mLeftTopBottomMargin) / 2;
        //左边的半圆外接矩形
        mArcRectF = new RectF(mLeftTopBottomMargin, mLeftTopBottomMargin, mLeftTopBottomMargin + 2 * mArcRadius,
                mHeight - mLeftTopBottomMargin);

        mBackgroundWhiteRectF = new RectF(mLeftTopBottomMargin + mCurrentProgressHorizontalPosition, mLeftTopBottomMargin,
                mWidth - mRightMargin,
                mHeight - mLeftTopBottomMargin);
        mArcRightLocation = mLeftTopBottomMargin + mArcRadius;

        mProgressOrangeRectF = new RectF(mLeftTopBottomMargin + mArcRadius, mLeftTopBottomMargin,
                mCurrentProgressHorizontalPosition, mHeight - mLeftTopBottomMargin);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /**
     * 确定外框的绘制区域和在屏幕上的显示区域
     */
    private void setOuterSrcAndDesRect() {
        //初始化外框的绘制区域
        outerWidth = outerBitmap.getWidth();
        outerHeight = outerBitmap.getHeight();
        outerSrcRect = new Rect(0, 0, outerWidth, outerHeight);
        //初始化外框在屏幕上的显示区域
        outerDesRect = new Rect(0, 0, mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawProgressAndLeaf(canvas);

        //将外框绘制出来
        canvas.drawBitmap(outerBitmap, outerSrcRect, outerDesRect, bitmapPaint);

        postInvalidate();

    }

    /**
     * 绘制进度条和叶子
     *
     * @param canvas
     */
    private void drawProgressAndLeaf(Canvas canvas) {
        if (mCurrentProgress >= TOTAL_PROGRESS) {
            mCurrentProgress = 0;
        }
        //根据当前进度算出进度条的位置
        mCurrentProgressHorizontalPosition = mProgressWidth * mCurrentProgress / TOTAL_PROGRESS;

        //如果当前进度位置小于弧形半径，那么首先它背景的填充要包括弧形和后面的矩形，然后进度就只要画弧形里的区域就行了
        if (mCurrentProgressHorizontalPosition < mArcRadius) {

            //绘制弧形背景
            canvas.drawArc(mArcRectF, 90, 180, false, mBackgroundWhitePaint);
            //绘制矩形背景
            mBackgroundWhiteRectF.left = mArcRightLocation;
            canvas.drawRect(mBackgroundWhiteRectF, mBackgroundWhitePaint);
            drawLeafs(canvas);

            //绘制进度在弧形里的变化
            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressHorizontalPosition) / mArcRadius));
            int startAngle = 180 - angle;
            int sweepAngle = 2 * angle;
            canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, mProgressOrangePaint);

        }
        //如果大于弧度半径了，那么背景填充就只要画矩形就行了，且会随着当前的进度而变化，那么背景的这个矩形的左上角起始的点就是进度的当前位置
        //而进度条则是要先把左半部分的填充，然后右半部分的矩形则以弧形的最右定点为起点，结束点为当前进度
        else {

            //绘制背景
            mBackgroundWhiteRectF.left = mCurrentProgressHorizontalPosition;
            canvas.drawRect(mBackgroundWhiteRectF, mBackgroundWhitePaint);
            drawLeafs(canvas);
            //绘制进度的弧形
            canvas.drawArc(mArcRectF, 90, 180, false, mProgressOrangePaint);
            //绘制进度的矩形
            mProgressOrangeRectF.left = mArcRightLocation;
            mProgressOrangeRectF.right = mCurrentProgressHorizontalPosition;
            canvas.drawRect(mProgressOrangeRectF, mProgressOrangePaint);

        }
    }

    /**
     * 默认中等振幅
     */
    private final int MIDDLE_VIBRATION = 15;
    /**
     * 振幅差（即大振幅比中振幅大3，小振幅比中振幅小3）
     */
    private final int VIBRATION_DISPARITY = 3;

    /**
     * 绘制树叶
     *
     * @param canvas
     */
    private void drawLeafs(Canvas canvas) {

        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafList.size(); i++) {
            Leaf leaf = mLeafList.get(i);

            if(currentTime > leaf.getStartTime() && leaf.getStartTime()!=0){
                long intervalTime = currentTime - leaf.getStartTime();
                if (intervalTime < 0) {
                    return;
                } else if (intervalTime > LeafFactory.LEAF_DEFAULT_CYCLE_TIME) {
                        leaf.setStartTime(currentTime + new Random().nextInt((int) LeafFactory.LEAF_DEFAULT_CYCLE_TIME));
                }
                //要注意这里的意思
                float fraction = (float) intervalTime / LeafFactory.LEAF_DEFAULT_CYCLE_TIME;
                leaf.setX((int)(mProgressWidth - mProgressWidth * fraction));

                //y = a(wx+q)h
                float w = (float) ((float)2 * Math.PI / mProgressWidth);
                float a = MIDDLE_VIBRATION;
                switch (leaf.getStartType()) {
                    case LITTLE:
                        a = MIDDLE_VIBRATION - VIBRATION_DISPARITY;
                        break;
                    case MIDDLE:
                        a = MIDDLE_VIBRATION;
                        break;
                    case BIG:
                        a = MIDDLE_VIBRATION + VIBRATION_DISPARITY;
                        break;
                }
                leaf.setY((int) (a * Math.sin(w * leaf.getX()) + mArcRadius * 2 / 3));

                canvas.save();

                Matrix matrix = new Matrix();
                float transX = mLeftTopBottomMargin + leaf.getX();
                float transY = mLeftTopBottomMargin + leaf.getY();
                matrix.postTranslate(transX, transY);

                float rotateFraction = ((currentTime - leaf.getStartTime()) % LeafFactory.LEAF_DEFAULT_CYCLE_TIME)
                        / (float)LeafFactory.LEAF_DEFAULT_CYCLE_TIME;
                Log.i("Leaf", "leaf.getStartTime: " + leaf.getStartTime() + "");
                int angle = (int) (rotateFraction * 360);
                int rotate;
                if (leaf.getRotateDirection() == 0) {
                    rotate = angle + leaf.getRotateAngle();
                } else {
                    rotate = -angle + leaf.getRotateAngle();
                }
                matrix.postRotate(rotate, transX + leafWidth / 2 , transY + leafHeight / 2);
                canvas.drawBitmap(leafBitmap , matrix , bitmapPaint);
                canvas.restore();
            }
        }


    }

    public void setmCurrentProgress(int progress){
        this.mCurrentProgress = progress;
        postInvalidate();
    }
}
