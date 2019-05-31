package com.example.mybook;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybook.DB.MyDatabaseHelper;
import com.example.mybook.DownloadTask.DownloadService;
import com.lzf.mybook.R;

public class DetailActivity extends AppCompatActivity {
    private String TAG = "DetailActivity";
    private TextView textView;
    private MyReceiver myReceiver;
    private MyDatabaseHelper databaseHelper;
    private ServiceConnection serviceConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.text1);
        myReceiver = new MyReceiver(this);
        Intent intent = new Intent(this,DownloadService.class);
        databaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        //startService(intent);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                ContentValues c = new ContentValues();
                c.put("name","The Da Vinci Code");
                c.put("author","Dan Brown");
                c.put("pages","454");
                c.put("price","16.96");
                sqLiteDatabase.insert("Book",null,c);
                Cursor cursor = sqLiteDatabase.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    String string = cursor.getString(cursor.getColumnIndex("name"));
                    Log.d(TAG, "onClick: "+string);
                    cursor.close();
                }
                getContentResolver();
                /*ContentValues c1 = new ContentValues();
                c1.put("price","19.99");
                sqLiteDatabase.update("Book",c1,"neme = ?",new String[]{"The Da Vinci Code"});*/
            }
        });
        //T();
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: "+grantResults[0]);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
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
            textView.setText(location.getLongitude() + "," + location.getLatitude());
            Toast.makeText(getApplicationContext(), "location：" + location.getLongitude() + "," + location.getLatitude(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            Intent i = new Intent();
            i.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        }
    }
    class MyReceiver extends BroadcastReceiver{
       private Activity activity;
       MyReceiver(Activity activity){
           this.activity = activity;
       }
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
