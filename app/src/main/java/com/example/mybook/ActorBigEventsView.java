package com.example.mybook;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;

public class ActorBigEventsView extends ViewFlipper {

    private OnItemClickListener listener;
    private EventsAdapter mAdapter;

    public ActorBigEventsView(Context context) {
        super(context);
    }

    public ActorBigEventsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void startAnimationEvents() {
        if (mAdapter == null || mAdapter.getCount() <= 0) {
            return;
        }
        removeAllViews();

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(getContext(), i);
            addView(view);
            if (listener != null) {
                view.setTag(i);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick((Integer) view.getTag());
                    }
                });
            }
        }

        if (mAdapter.getCount() > 1) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNext();
                    startFlipping();
                }
            }, 3000);
        }
        requestLayout();
    }

    public void setAdapter(EventsAdapter mAdapter) {
        this.mAdapter = mAdapter;
        startAnimationEvents();
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onClick(int position);
    }

    public interface EventsAdapter {

        int getCount();

        View getView(Context context, int position);
    }
}
