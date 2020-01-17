package com.example.mybook.entity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mybook.DetailActivity;
import com.example.mybook.utils.Constants;
import com.lzf.mybook.R;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static android.support.constraint.Constraints.TAG;

public class


FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private String TAG = "FruitAdapter";
    private Context mContext;
    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList, Context context) {
        this.mFruitList = fruitList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false);

        Glide.with(mContext)
                .load(fruit.getImageId())
                .apply(requestOptions)
                .into(holder.fruitImage);
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 2;
        //holder.fruitImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),fruit.getImageId(),options));
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constants.LOGINPATH)
                        .navigation();
                //Intent intent = new Intent("det");
                /*PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);
                Notification notification = new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.drawable.ic)
                        .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.apple))
                        .setAutoCancel(true)
                        .setContentTitle("通知")
                        .setContentIntent(pendingIntent)
                        .build();
                NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

                RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),R.layout.activity_personal);
                remoteViews.setImageViewResource(R.id.image1,R.drawable.movie);
                notification.contentView = remoteViews;*/
                //notification.contentIntent = pendingIntent;
                //notificationManager.notify(2,notification);
                /*int [] screenLocation = new int[2];
                holder.fruitImage.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", holder.fruitImage.getWidth()).
                        putExtra("height", holder.fruitImage.getHeight()).
                        putExtra("imageId",mFruitList.get(position).getImageId());
                mContext.startActivity(intent);
                activity.overridePendingTransition(0, 0);*/
               // test();
            }


        });
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    private void test() {
        Log.d(TAG, "test: ");
        AudioManager.OnAudioFocusChangeListener l = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                Log.d(TAG, "onAudioFocusChange: ");
            }
        };
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(l, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Start playback.
            Log.d(TAG, "Start sleep: ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "end sleep: ");
            audioManager.abandonAudioFocus(l);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        ImageView fruitImage;
        TextView fruitName;
        Button button;

        ViewHolder(View view) {
            super(view);
            cardView = view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
            button = view.findViewById(R.id.fruit_action);
        }

    }
}
