package com.example.mybook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.os.Handler;
import android.os.Looper;
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

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


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
        r.test();
    }

}
