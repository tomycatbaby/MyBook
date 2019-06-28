package com.example.mybook;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybook.DB.MyDatabaseHelper;
import com.example.mybook.Download.DownloadService;
import com.example.mybook.myview.TickView;
import com.lzf.mybook.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetailActivity extends AppCompatActivity {
    private String TAG = "DetailActivity";
    private TextView textView;
    private TextView t1;
    private MyReceiver myReceiver;
    private MyDatabaseHelper databaseHelper;
    private LruCache lruCache;
    private Handler handler;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.text1);
        t1 = findViewById(R.id.t1);
        myReceiver = new MyReceiver(this);
        Intent intent = new Intent(this, DownloadService.class);
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //startService(intent);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLayout.prepare();
                List list = getActiveSubscriptionInfoList(getApplicationContext());
                /*SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                ContentValues c = new ContentValues();
                c.put("name", "The Da Vinci Code");
                c.put("author", "Dan Brown");
                c.put("pages", "454");
                c.put("price", "16.96");
                sqLiteDatabase.insert("Book", null, c);
                Cursor cursor = sqLiteDatabase.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String string = cursor.getString(cursor.getColumnIndex("name"));
                    Log.d(TAG, "onClick: " + string);
                    cursor.close();
                }*/
                /*ContentValues c1 = new ContentValues();
                c1.put("price","19.99");
                sqLiteDatabase.update("Book",c1,"neme = ?",new String[]{"The Da Vinci Code"});*/
                if (tm != null) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    String ss = tm.getSubscriberId();

                    try {
                        Method method = tm.getClass().getMethod("getSubscriberId", int.class);
                        String s = (String) method.invoke(tm, 1);
                        String s1 = (String) method.invoke(tm, 4);
                        if (ss != null) {
                            textView.setText(ss);
                        }
                        /*if (s1 != null) {
                            t1.setText(s1);
                        }*/
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }


                }
               /* Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:10086");
                intent1.setData(data);
                startActivity(intent1);*/
            }
        });
        //T();
        //findDeep();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: " + grantResults[0]);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel:10086");
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            //startActivity(intent);
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void T() {
        Log.d(TAG, "T: ");
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkSelfPermission failed: ");
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: ");
                showLoaction(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d(TAG, "onProviderEnabled: ");
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                showLoaction(locationManager.getLastKnownLocation(provider));
            }

            @Override
            public void onProviderDisabled(String provider) {
                showLoaction(null);
            }
        });
    }

    private void showLoaction(Location location) {
        if (location != null) {
            textView.setText(String.format("%s,%s", location.getLongitude(), location.getLatitude()));
            Toast.makeText(getApplicationContext(), "location：" + location.getLongitude() + "," + location.getLatitude(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            Intent i = new Intent();
            i.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        }
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

    public static void showDialog(){
        DialogFragment dialogFragment = new DialogFragment(){
            @Nullable
            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                return super.onCreateView(inflater, container, savedInstanceState);
            }
        };
    }
}
