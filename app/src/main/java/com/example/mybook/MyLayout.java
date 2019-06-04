package com.example.mybook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class MyLayout extends LinearLayout {
    private String TAG = "MyLayout";
    private final Paint mPaint;
    private final Matrix matrix;

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint();
        this.matrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        /*mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic);
        RectF mRectF  = new RectF(20,20,200,200);*/
        // canvas.drawArc(mRectF,-45,360,false,mPaint);
        //canvas.drawCircle(100,100,80,mPaint);
      /*  matrix.postTranslate(100,0);
        matrix.postRotate(45);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),matrix,mPaint);*/

        super.onDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        //如果是垂直方向上的滑动，就拦截掉，如果是水平方向上的滑动，就不拦截
        /*float startX = 0;
        float startY = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                float dx = endX - startX;
                float dy = endY - startY;
                if (Math.abs(dx)>Math.abs(dy)){
                    Log.d(TAG, "onInterceptTouchEvent: 水平滑动");
                    //水平滑动
                    return false;
                }else {
                    Log.d(TAG, "onInterceptTouchEvent: 竖直滑动");
                    //竖直滑动
                    return true;
                }
        }*/
        return super.onInterceptTouchEvent(ev);
    }

}
