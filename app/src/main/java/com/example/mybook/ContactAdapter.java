package com.example.mybook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mybook.entity.Fruit;

import java.util.List;

public class ContactAdapter extends RecyclerView {
    private List<Fruit> list = null;
    private Context mContext;

    public ContactAdapter(Context context) {
        super(context);
    }

     static  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private CheckBox checkBox;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
         }
     }

}
