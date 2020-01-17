package com.example.mybook;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mybook.DB.MyDatabaseHelper;
import com.example.mybook.Download.DownloadService;
import com.example.mybook.entity.Window;
import com.example.mybook.myview.TickView;
import com.example.mybook.utils.Book;
import com.example.mybook.utils.Constants;
import com.example.mybook.utils.MyDialogFragment;
import com.example.mybook.utils.Rx;
import com.example.mybook.utils.WeatherService;
import com.google.gson.JsonObject;
import com.lzf.mybook.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Route(path = Constants.LOGINPATH)
public class DetailActivity extends BaseActivity {
    private String TAG = "DetailActivity";

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private FloatingActionButton floatingActionButton;
    private MyReceiver myReceiver;
    private MyDatabaseHelper databaseHelper;
    private LruCache lruCache = new LruCache(2 * 1024);
    private Handler handler;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Autowired(name = "ac")
    String ac;
    @Autowired(name = "priority")
    int priority;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ARouter.getInstance().inject(this);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        floatingActionButton = findViewById(R.id.float_button);
        Log.d(TAG, "onCreate: ");
        handler = new MyHandler(this);
        myReceiver = new MyReceiver(this);
        Intent intent = new Intent(this, DownloadService.class);
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.d(TAG, "ac: " + ac);
        Log.d(TAG, "priority: " + priority);
        //startService(intent);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(1);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test();Tes
//                Rx rx = new Rx();
//                rx.request();
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(null);
                handler.sendEmptyMessage(2);

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(3);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, BasicActivity.class);
                startActivity(i);

            }
        });
        //findDeep();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    private void findDeep() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        //ViewGroup viewGroup = findViewById(android.R.id.content);
        TickView tickView = new TickView(getApplicationContext());
        Queue<View> queue = new LinkedList<>();
        queue.add(viewGroup);
        queue.add(tickView);
        int deep = 0;
        while (!queue.isEmpty()) {
            if (queue.peek() instanceof ViewGroup) {
                viewGroup = (ViewGroup) queue.poll();
                int count = viewGroup.getChildCount();
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                            queue.offer(viewGroup.getChildAt(i));
                        }
                    }
                }
            } else if (queue.peek() instanceof TickView) {
                queue.poll();
                deep++;
                if (!queue.isEmpty()) {
                    queue.offer(tickView);
                }
            }
        }
        Log.d(TAG, "findDeep: " + deep);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void test(int i) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("lzf", "run: ");
//                Looper.prepare();
//                final Handler h = new MyHandler();
//                h.sendEmptyMessage(1);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        h.sendEmptyMessage(3);
//                    }
//                }).start();
//                Looper.loop();
//            }
//        }).start();
        Log.d(TAG, "test: " + i);
    }


    class MyReceiver extends BroadcastReceiver {
        private Activity activity;

        MyReceiver(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    public List getActiveSubscriptionInfoList(Context context) {
        SubscriptionManager subscriptionManager = SubscriptionManager.from(context);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List list = subscriptionManager.getActiveSubscriptionInfoList();

        return list;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //MotionEvent.ACTION_MASK;
        if (event.getAction() != 2)
            Log.d(TAG, "onTouchEvent: " + event.getActionIndex());
        return super.onTouchEvent(event);
    }

    static class MyHandler extends Handler {

        private WeakReference<DetailActivity> weakReference;

        public MyHandler(DetailActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DetailActivity a = weakReference.get();
            HandlerThread handlerThread = new HandlerThread("");
            handlerThread.start();
            Log.d("lzf", "handleMessage: " + msg.what);
            a.test(msg.what);
//            if (msg.what == 2) {
//                Looper.myLooper().quit();
//            }
            super.handleMessage(msg);
        }
    }

}
