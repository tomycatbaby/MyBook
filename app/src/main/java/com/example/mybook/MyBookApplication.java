package com.example.mybook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;


public class MyBookApplication extends Application {
    public static int flag = 0;
    private String TAG = "MyBookApplication";
    //是否开启调试
    private static boolean isDebug = true;
    public static int currentWindow;
    public static long playbackPosition;
    /**
     * 内存不够时调用
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 当终止程序时调用 但是不能保证一定调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 在内存清理时触发
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: " + level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                break;
        }
    }

    /**
     * 在配置被改变时触发
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //必须在初始化之前写入这两行
        if (isDebug) {
            //打印日志
            ARouter.openLog();
            //开始调试
            ARouter.openDebug();
        }
        //ARouter的实例化
        Log.d(TAG, "onCreate: ");
        ARouter.init(this);

        if (Build.VERSION.SDK_INT >= 14) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    Log.d(TAG, "onActivityCreated: ");
                }

                @Override
                public void onActivityStarted(Activity activity) {
                    Log.d(TAG, "onActivityStarted: ");
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    Log.d(TAG, "onActivityResumed: ");
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    Log.d(TAG, "onActivityPaused: ");
                }

                @Override
                public void onActivityStopped(Activity activity) {
                    Log.d(TAG, "onActivityStopped: ");
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    Log.d(TAG, "onActivitySaveInstanceState: ");
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    Log.d(TAG, "onActivityDestroyed: ");
                }
            });
        }
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
