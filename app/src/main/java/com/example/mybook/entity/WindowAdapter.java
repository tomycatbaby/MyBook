package com.example.mybook.entity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzf.mybook.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WindowAdapter extends RecyclerView.Adapter<WindowAdapter.ViewHolder> {
    private Context mContext;
    private List<Window> mWindowList;

    public WindowAdapter(List<Window> windows,Context context) {
        this.mWindowList = windows;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.window_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Window window = mWindowList.get(position);
        holder.windowName.setText(window.name);
        holder.windowNumber.setText(String.valueOf(window.number));
        holder.windowRecyclerView.setItemAnimator(null);
        holder.windowRecyclerView.setNestedScrollingEnabled(false);
        holder.windowRecyclerView.setAdapter(new FruitAdapter(window.list,mContext));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.windowRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return mWindowList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView windowName;
        TextView windowNumber;
        RecyclerView windowRecyclerView;
        ViewHolder(View v) {
            super(v);
            this.view = v;
            windowName = v.findViewById(R.id.window_name);
            windowNumber = v.findViewById(R.id.window_number);
            windowRecyclerView = v.findViewById(R.id.window_recycler);
        }
    }
}
