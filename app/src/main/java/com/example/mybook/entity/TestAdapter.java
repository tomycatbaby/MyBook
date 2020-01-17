package com.example.mybook.entity;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mybook.test.OnLoadListener;
import com.lzf.mybook.R;

import java.io.File;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private String TAG = "TestAdapter";
    private Context mContext;
    private List<Fruit> mFruitList;
    private OnLoadListener onLoadListener;

    public TestAdapter(List<Fruit> fruitList, Context context, OnLoadListener onLoadListener) {
        this.mFruitList = fruitList;
        this.mContext = context;
        this.onLoadListener = onLoadListener;
    }

    public void setmFruitList(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.test_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {

        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder viewHolder, int position) {
        Fruit fruit = mFruitList.get(position);
        viewHolder.name.setText(fruit.getName());
        File f = new File(Environment.getExternalStorageDirectory() + "/lzf.png");
        Log.d(TAG, "onBindViewHolder: " + f.exists());
        RequestBuilder builder = Glide.with(mContext).load(f);
        Log.d(TAG, "onBindViewHolder: " + fruit.getName() + " position:" + position + " size:" + mFruitList.size());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ProgressBar progressBar;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fruit_name);
            progressBar = itemView.findViewById(R.id.loading);
            imageView = itemView.findViewById(R.id.test_image);
        }
    }
}
