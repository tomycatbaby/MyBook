package com.example.mybook.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MyCoordinatorLayout extends ViewGroup {
    private String TAG = "MyCoordinatorLayout";

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
//            final LayoutParams lp =  child.getLayoutParams();
//            if (lp.width == LayoutParams.WRAP_CONTENT){
//                Log.d(TAG, "measure width: "+child.getMeasuredWidth());
//            }
            //Log.d(TAG, "width: " + lp.width + "height:" + lp.height);
        }
        Log.d(TAG, "onMeasure end: ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
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
        //return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(ev);
    }

    /**
     * Call this view's OnClickListener, if it is defined.  Performs all normal
     * actions associated with clicking: reporting accessibility event, playing
     * a sound, etc.
     *
     * @return True there was an assigned OnClickListener that was called, false
     * otherwise is returned.
     */
    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
