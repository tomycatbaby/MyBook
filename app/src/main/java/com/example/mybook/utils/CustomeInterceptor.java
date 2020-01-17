package com.example.mybook.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 5)
public class CustomeInterceptor implements IInterceptor{
    private String TAG = "CustomeInterceptor";
    //中断路由进程
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equals(Constants.CUSTOMPATH)){
            Log.e(TAG, CustomeInterceptor.class.getName()+"进行拦截处理");
            callback.onContinue(postcard);
        }else if (postcard.getPath().equals(Constants.LOGINPATH)){
            Log.e(TAG, CustomeInterceptor.class.getName()+"进行拦截处理");
            callback.onContinue(postcard);
        }else if (postcard.getPath().equals(Constants.TESTPATH)){
            Log.e(TAG, CustomeInterceptor.class.getName()+"进行拦截处理");
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        Log.e(TAG, CustomeInterceptor.class.getName()+"初始化");
    }
}
