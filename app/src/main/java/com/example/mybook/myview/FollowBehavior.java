package com.example.mybook.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.lzf.mybook.R;

public class FollowBehavior extends CoordinatorLayout.Behavior {
    private int targetId;

    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Follow);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if (a.getIndex(i) == R.styleable.Follow_target) {
                targetId = a.getResourceId(attr, -1);
            }
        }
        a.recycle();
    }

    @Override
   // 当依赖的那个view改变时，回调通知这个behavior对应的view应该怎么做.  child 参数就是那个bevior对应的view，也就是你把这个behavior赋予给的那个view。
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }

    @Override// 说明这个behavior对应的view依赖的是哪个view
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == targetId;
    }
}
