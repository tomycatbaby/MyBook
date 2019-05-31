package com.example.mybook.utils;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ImageView;

public class MatrixImageView extends android.support.v7.widget.AppCompatImageView{
    private GestureDetector mGestureDetector;
    private Matrix mMatrix = new Matrix();
    private float mImageWidth;
    private float mImageHeight;

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }
}
