package com.zsx.android.androidlifecycledemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zsx.android.androidlifecycledemo.R;

/**
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class ListBottomViewHolder extends RecyclerView.ViewHolder{
    private TextView textView;
    public ListBottomViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.item_bottom_text);
    }
    public void setMessage(String msg){
        textView.setText(msg);
    }
}
