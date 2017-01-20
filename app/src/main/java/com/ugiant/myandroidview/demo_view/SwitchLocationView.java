package com.ugiant.myandroidview.demo_view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ugiant.myandroidview.R;

/**
 * Created by chijiaduo on 2017/1/18.
 */

public class SwitchLocationView extends RelativeLayout {

    private TextView tv_text1;
    private TextView tv_text2;
    private ImageView iv_switch;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;
    private int mWidth;
    private int mHeight;
    public static String start , end;


    public SwitchLocationView(Context context) {
        super(context);
        init(context);
    }



    public SwitchLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    private void init(Context context) {

        tv_text1 = new TextView(context);
        tv_text2 = new TextView(context);
        iv_switch = new ImageView(context);

        tv_text1.setGravity(Gravity.CENTER_VERTICAL);
        tv_text2.setGravity(Gravity.CENTER_VERTICAL);

        tv_text1.setText("北京");
        tv_text2.setText("广州");
        iv_switch.setImageResource(R.mipmap.ic_launcher);

        mLeftParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        addView(tv_text1, mLeftParams);

        mRightParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT , TRUE);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL , TRUE);
        addView(tv_text2, mRightParams);

        mTitleParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT , TRUE);
        addView(iv_switch , mTitleParams);

        final String start = tv_text1.getText().toString();
        String end = tv_text2.getText().toString();

        iv_switch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_text1, "translationX" , 0 , 100);
                objectAnimator.setDuration(200);
                objectAnimator.start();
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(tv_text2, "translationX" , 200 , 0);
                objectAnimator1.setDuration(500);
                objectAnimator1.start();

//                mLeftParams.removeRule();
            }
        });

    }

//    public interface switchClickListener{
//        void switchLocation();
//    }
//    public switchClickListener mSwitchClickListener;
//    public void setOnSwitchClickListener(switchClickListener switchClickListener){
//        this.mSwitchClickListener = switchClickListener;
//    }

}
