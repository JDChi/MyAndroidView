package com.ugiant.myandroidview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chijiaduo on 2017/2/6.
 */

public class SimplePathOPView extends View {

    private Paint paint;
    private int mWidth;
    private int mHeight;
    public SimplePathOPView(Context context) {
        super(context);
        init();
    }



    public SimplePathOPView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
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
        int x = 80;
        int r = 100;
        canvas.translate(250 , 0);

        Path path1 = new Path();
        Path path2 = new Path();
        Path pathResult = new Path();

        path1.addCircle(-x , 0 , r , Path.Direction.CW);
        path2.addCircle(x , 0 , r, Path.Direction.CW);
        pathResult.op(path1 , path2 , Path.Op.DIFFERENCE);
        canvas.translate(0 , 200);
        canvas.drawText("DIFFERENCE" , 240 , 0 , paint);
        canvas.drawPath(pathResult , paint);

        pathResult.op(path1 , path2 , Path.Op.REVERSE_DIFFERENCE);
        canvas.translate(0 , 300);
        canvas.drawText("REVERSE_DIFFERENCE" , 240 , 0 , paint);
        canvas.drawPath(pathResult , paint);

        pathResult.op(path1 , path2 , Path.Op.INTERSECT);
        canvas.translate(0 , 300);
        canvas.drawText("INTERSECT" , 240 , 0 , paint);
        canvas.drawPath(pathResult , paint);

        pathResult.op(path1 , path2 , Path.Op.UNION);
        canvas.translate(0 , 300);
        canvas.drawText("UNION" , 240 , 0 , paint);
        canvas.drawPath(pathResult , paint);

        pathResult.op(path1 , path2 , Path.Op.XOR);
        canvas.translate(0 , 300);
        canvas.drawText("XOR" , 240 , 0 , paint);
        canvas.drawPath(pathResult , paint);

        //这里连续的translate是指在上一个的基础上偏移，所以它的y坐标就是0 ， 200 ， 500这样



    }
}
