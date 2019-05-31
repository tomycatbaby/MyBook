package com.example.mybook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;



public class MyBookApplication  extends Application{
    private String TAG = "MyBookApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 14){
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
    }
}
