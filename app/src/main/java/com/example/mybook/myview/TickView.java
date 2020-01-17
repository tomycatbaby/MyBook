package com.example.mybook.myview;

import android.app.IntentService;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TickView extends android.support.v7.widget.AppCompatTextView {

    public TickView(Context context) {
        super(context);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec代表一个32为int值，高2位代表测试模式（SpecMode），低两位代表某个测试模式下的测试大小（SpecSize）
        //测试模式一共三种：UNSPECIFIED,EXACTLY,AT_MOST
        //UNSPECIFIED 父容器不对View有任何限制，要多大给多大，这种情况一般用于系统内部
        //EXACTLY 父容器已经检测出了具体的数值，对应LayoutParams的match_parent和具体的数值这两种
        //AT_MOST 对应的是LayoutParams的wrap_content，父容器指定了一个SpecSize，View的大小不能大于这个值，具体大小看View的实现
        final int paddingLeft = getPaddingLeft();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
