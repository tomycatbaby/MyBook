package com.example.mybook;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lzf.mybook.R;

import java.util.concurrent.Semaphore;


public class Reregister {
    private String TAG = "Reregister";
    private static Reregister mInstance;
    private int times_counting = 5;
    private TextView textView;
    private AlertDialog dialog;
    private String confirm;
    private static Semaphore s = new Semaphore(1);

    private Reregister() {

    }

    public static Reregister getInstance() {
        if (mInstance == null) {
            synchronized (Reregister.class) {
                if (mInstance == null) {
                    mInstance = new Reregister();
                }
            }
        }
        return mInstance;
    }

    public void test( String s) {
        Log.d(TAG, "test: "+s);
    }

    public void registerDialog(Context context) {
        Log.d(TAG, "come in registerDialog: ");
        if (s.availablePermits() <= 0) {
            Log.d(TAG, "no permits");
            return;
        }
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        confirm = "确认";
        times_counting = 5;
        final Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_confirm_and_cancel);
        textView = ((TextView) window.findViewById(R.id.btnConfirm));
        ((TextView) window.findViewById(R.id.tvTitle)).setText("提示");
        ((TextView) window.findViewById(R.id.tvMsg)).setText("切换线路");
        ((TextView) window.findViewById(R.id.btnConfirm)).setText(confirm);
        window.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
                reRegisterHandler.sendEmptyMessage(2);
            }
        });
        window.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.release();
                thread.interrupt();
                dialog.dismiss();
            }
        });
        if (reRegisterHandler == null) {
            reRegisterHandler = new ReRegisterHandler();
        }
        thread = new Thread(new MyRunnable());
        thread.start();
        Log.d(TAG, " leave registerDialog ");
    }

    private Thread thread;

    private ReRegisterHandler reRegisterHandler;

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Log.d(TAG, "run count number ");
                s.acquire();
                while (times_counting > 0) {
                    reRegisterHandler.sendEmptyMessage(1);
                    Thread.sleep(1000);
                }
                reRegisterHandler.sendEmptyMessage(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class ReRegisterHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView.setText(confirm + "(" + times_counting + ")");
                    times_counting--;
                    break;
                case 2:
                    s.release();
                    dialog.dismiss();
                    break;
            }
            super.handleMessage(msg);
        }
    }
}