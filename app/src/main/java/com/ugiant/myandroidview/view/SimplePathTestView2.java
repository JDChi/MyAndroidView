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
 * Created by chijiaduo on 2017/2/5.
 */

public class SimplePathTestView2 extends View {


    private Paint paint;
    private int mWidth;
    private int mHeight;

    public SimplePathTestView2(Context context) {
        super(context);
    }

    public SimplePathTestView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(3);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
canvas.translate(mWidth /2 , mHeight / 2);

        Path path = new Path();
//        path.moveTo(100 , 100);
//        path.lineTo(200 , 200);
//        相对当前位置偏移200的位置
//        path.rLineTo(200 , 200);

//        奇偶规则
//        path.setFillType(Path.FillType.EVEN_ODD);
//        反奇偶规则
//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
//        非零环绕数规则
//        path.setFillType(Path.FillType.WINDING);
//
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        // path.addRect(-400, -400, 400, 400, Path.Direction.CCW);


//        Path path1 = new Path();
//        Path path2 = new Path();
//        Path path3 = new Path();
//
//        path.addCircle(0 , 0 , 200 , Path.Direction.CW);
//        path1.addRect(0 , -200 , 200 , 200 , Path.Direction.CW);
//        path2.addCircle(0 , -100 , 100 , Path.Direction.CW);
//        path3.addCircle(0 , 100 , 100 , Path.Direction.CCW);
//
//        //需要在19后才有
//        path.op(path1 , Path.Op.DIFFERENCE);
//        path.op(path2 , Path.Op.UNION);
//        path.op(path3 , Path.Op.DIFFERENCE);

        RectF rectF = new RectF();
        path.lineTo(100 , -50);
        path.lineTo(100 , 50);
        path.close();
        path.addCircle(-100 , 0 , 100 , Path.Direction.CW);
        //计算边界并存入矩形内，通过获取矩形的数据来知道该图形的位置和边界大小
        path.computeBounds(rectF , true);

        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        canvas.drawRect(rectF , paint);

        String left = String.valueOf(rectF.left);
        paint.setTextSize(50);
        canvas.drawText(left , 0 , -400 , paint);


    }
}
