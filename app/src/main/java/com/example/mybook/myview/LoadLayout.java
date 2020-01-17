package com.example.mybook.myview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class LoadLayout extends FrameLayout {
    /**
     * 上一次的y轴坐标
     */
    private float mPreviousYPos;
    /**
     * 当前y轴坐标
     */
    private float mNowYPos;
    /**
     * y轴移动的距离
     */
    private float mYDistance;

    /**
     * down事件时的y坐标
     */
    private float mDownYPos;
    /**
     * 第一次触发move事件的时候，判断移动的距离（避免误触）
     */
    private boolean mFirstMove;
    /**
     * 第一次触发move事件的时候，y轴移动的距离
     */
    private float mJudgeYDistance;
    /**
     * 误差距离
     */
    private int mTouchSlop;

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreviousYPos = ev.getRawY();
                mDownYPos = ev.getRawY();
                mFirstMove = true;
                break;
            case MotionEvent.ACTION_MOVE:
                mNowYPos = ev.getRawY();
                mYDistance = mNowYPos - mPreviousYPos;
                mPreviousYPos = mNowYPos;
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:

        }
        return super.onInterceptTouchEvent(ev);
    }
}
