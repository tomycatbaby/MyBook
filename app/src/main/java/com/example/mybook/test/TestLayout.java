package com.example.mybook.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class TestLayout extends FrameLayout {
    private String TAG = "TestLayout";
    int columns = 2;//列数
    int rows = 0;//行数
    int margin = 10;//间距

    public TestLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        rows = childCount % columns == 0 ? childCount / columns : childCount / columns + 1;
        if (childCount < 1) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            Log.d(TAG, "onMeasure: " + lp.width + " :" + lp.height);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            child.measure(MeasureSpec.makeMeasureSpec(widthSize / 2, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.AT_MOST));
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        int childCount = getChildCount();
        if (childCount < 1) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            Log.d(TAG, "onLayout: " + child.getMeasuredHeight());
        }
    }
}
