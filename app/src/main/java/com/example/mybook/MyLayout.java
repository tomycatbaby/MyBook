package com.example.mybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.mybook.entity.Weather;
import com.example.mybook.utils.Rx;
import com.example.mybook.utils.WeatherService;
import com.lzf.mybook.R;

import java.util.concurrent.TimeUnit;




public class MyLayout{
    static final ThreadLocal<MyLayout> sThreadLocal = new ThreadLocal<MyLayout>();

    public MyLayout() {
    }

    public static void prepare() {
    /*    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//设置请求超时时长
        okHttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//启用Log日志
        okHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor());
//设置缓存方式、时长、地址
        okHttpClientBuilder.addNetworkInterceptor(getCacheInterceptor());
        okHttpClientBuilder.addInterceptor(getCacheInterceptor());
        okHttpClientBuilder.cache(getCache());
//设置https访问(验证证书)
        okHttpClientBuilder.sslSocketFactory(getSSLSocketFactory(mContext, new int[]{R.raw.tomcat}));
        okHttpClientBuilder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//设置统一的header
        okHttpClientBuilder.addInterceptor(getHeaderInterceptor());*/
        Rx r = new Rx();
        //Handler的使用
        //handler消息机制是Android面试中常问的知识点，Android缺了handler就不能再正常运转了。
        //首先我们先看下handler的使用，我认为可以分为两类，第一类post(Runnable r)，第二类send(Message m)
        //大致有以下这些方法
//        Handler.createAsync()
//        new Handler().obtainMessage();
//        new Handler().post();//直接执行
//        new Handler().postAtTime()//某个时间点执行
//        new Handler().postDelayed()//延迟多少事件执行
//        new Handler().postAtFrontOfQueue()//放到消息队列前面执行
//        new Handler().sendMessageAtFrontOfQueue()
//        new Handler().sendMessageAtTime()//某个时间点执行
//        new Handler().sendEmptyMessage()//向消息队列中发送一个只带what不带其他obj的消息
//        Message.obtain().sendToTarget();//Message.obtain()是建议使用的生成Message对象的方式，Message类维护了一个消息池，复用Message对象
        //sendToTarget意思是将msg发送到消息队列中
//
        //所有的发送消息的方式最后都是走到sendMessageAtTime()，最后调用了handler.enqueueMessage方法
        //所以说所有的handler消息发送方式最后都是要走到消息队列中，而我们所说的不同指的是，post方式
        //发送会带有一个Runnable任务，send没有，runnable也就是msg对象的callback属性，我们看消息队列出来的消息
        //是如何处理的就会明白了。
        //所有的
        /*public void dispatchMessage(Message msg) {
            if (msg.callback != null) {
                handleCallback(msg);
            } else {
                if (mCallback != null) {
                    if (mCallback.handleMessage(msg)) {
                        return;
                    }
                }
                handleMessage(msg);
            }
        }*/
       /* ```
        private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
        msg.target = this;
        if (mAsynchronous) {
            msg.setAsynchronous(true);
        }
        return queue.enqueueMessage(msg, uptimeMillis);
        }

        ```*/
        //消息队列的

        r.test();
    }


}
