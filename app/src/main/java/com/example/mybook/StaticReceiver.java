package com.example.mybook;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class StaticReceiver extends BroadcastReceiver{
   
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("lzf", "onReceive: "+intent.getAction());
        Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.lzf.mybook");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
