package com.example.mybook.myview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyRecyclerView extends RecyclerView {
    private String TAG = "MyRecyclerView";

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float startX = 0;
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
                if (Math.abs(dx) > Math.abs(dy)) {
                    Log.d(TAG, "onInterceptTouchEvent: 水平滑动");
                    //水平滑动
                    return false;
                } else {
                    Log.d(TAG, "onInterceptTouchEvent: 竖直滑动");
                    //竖直滑动
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }*/
}
