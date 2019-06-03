package com.example.mybook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.lzf.mybook.R;

public class MyLayout extends LinearLayout {

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
        return false;
    }

}
