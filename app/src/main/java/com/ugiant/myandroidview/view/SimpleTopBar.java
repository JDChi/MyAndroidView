package com.ugiant.myandroidview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ugiant.myandroidview.R;

/**
 * Created by JDNew on 12/5/2016.
 */

public class SimpleTopBar extends RelativeLayout {
    private int mTitleTextColor;
    private int mLeftTextColor;
    private int mRightTextColor;
    private Drawable mLeftBackground;
    private Drawable mRightBackground;
    private String mTitleText;
    private String mLeftText;
    private String mRightText;
    private float mTitleTextSize;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;




    public SimpleTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs , R.styleable.SimpleTopBar);
        mTitleTextColor = typedArray.getColor(R.styleable.SimpleTopBar_titleTextColor , 0);
        mLeftTextColor = typedArray.getColor(R.styleable.SimpleTopBar_leftTextColor , 0);
        mRightTextColor = typedArray.getColor(R.styleable.SimpleTopBar_rightTextColor , 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.SimpleTopBar_leftBackground);
        mRightBackground = typedArray.getDrawable(R.styleable.SimpleTopBar_rightBackground);
        mTitleText = typedArray.getString(R.styleable.SimpleTopBar_title);
        mLeftText = typedArray.getString(R.styleable.SimpleTopBar_leftText);
        mRightText = typedArray.getString(R.styleable.SimpleTopBar_rightText);
        mTitleTextSize = typedArray.getDimension(R.styleable.SimpleTopBar_titleTextSize , 10);

        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT , TRUE);
        addView(mLeftButton , mLeftParams);

        mRightParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT , TRUE);
        addView(mRightButton , mRightParams);

        mTitleParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT , TRUE);
        addView(mTitleView , mTitleParams);


        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }


    public interface topbarClickListener{
        void leftClick();

        void rightClick();
    }

    public topbarClickListener mListener;
    public void setOnTopBarClickListener(topbarClickListener listener){
        this.mListener = listener;
    }


}
