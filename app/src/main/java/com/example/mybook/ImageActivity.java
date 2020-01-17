package com.example.mybook;


import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzf.mybook.R;



public class ImageActivity extends BaseActivity {
    private TextView textView;
    private ImageView imageView;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private int intentTop;
    private int intentLeft;
    private int intentWidth;
    private int intentHeight;
    private static final int DURATION = 200;
    private LinearLayout linearLayout;
    private ColorDrawable colorDrawable;
    private String TAG = "PersonalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_personal);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        finish();
        View view = viewGroup.getChildAt(0);
        Intent intent = getIntent();

        int draw = intent.getIntExtra("imageId", 0);
        /*
        intentTop = intent.getIntExtra("top", 0);
        intentLeft = intent.getIntExtra("left", 0);
        intentWidth = intent.getIntExtra("width", 0);
        intentHeight = intent.getIntExtra("height", 0);*/
//        Log.d(TAG, "intentTop " + intentTop);
//        Log.d(TAG, "intentLeft  " + intentLeft);
//        Log.d(TAG, "intentWidth  " + intentWidth);
//        Log.d(TAG, "intentHeight " + intentHeight);
        imageView = findViewById(R.id.image1);

        colorDrawable = new ColorDrawable(Color.BLACK);
        if (draw != 0) {
            imageView.setImageDrawable(getDrawable(draw));
        }
        view.setBackground(colorDrawable);
        //getWindow().getDecorView().setBackgroundDrawable(colorDrawable);
        setHalfTransparent();
        if (savedInstanceState == null) {
            ViewTreeObserver observer = imageView.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    //坐标的获取设置
                    int[] screenLocation = new int[2];
                    imageView.getLocationOnScreen(screenLocation);
                    mLeftDelta = intentLeft - screenLocation[0];
                    mTopDelta = intentTop - screenLocation[1];

                    mWidthScale = (float) intentWidth / imageView.getWidth();
                    mHeightScale = (float) intentHeight / imageView.getHeight();
                    //开启缩放动画
                    enterAnimation();

                    return true;
                }
            });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.action_settings,new WindowFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        Log.d("lzf", "onDestroy: ");
    }



    protected void setHalfTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        exitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
            }
        });
        return true;
    }

    //进入动画
    public void enterAnimation() {
        //设置imageview动画的初始值
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(mWidthScale);
        imageView.setScaleY(mHeightScale);
        imageView.setTranslationX(mLeftDelta);
        imageView.setTranslationY(mTopDelta);
        //设置动画
        TimeInterpolator sDecelerator = new DecelerateInterpolator();
        //设置imageview缩放动画
        imageView.animate().setDuration(DURATION).scaleX(1).scaleY(1).
                translationX(0).translationY(0).setInterpolator(sDecelerator);

        // 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
        bgAnim.setDuration(DURATION);
        bgAnim.start();
    }

    //退出动画
    public void exitAnimation(final Runnable endAction) {

        TimeInterpolator sInterpolator = new AccelerateInterpolator();
        //设置imageview缩放动画，以及缩放结束位置
        imageView.animate().setDuration(DURATION).scaleX(mWidthScale).scaleY(mHeightScale).
                translationX(mLeftDelta).translationY(mTopDelta)
                .setInterpolator(sInterpolator).withEndAction(endAction);

        // 设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
        bgAnim.setDuration(DURATION);
        bgAnim.start();
    }
}
