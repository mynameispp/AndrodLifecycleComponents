package com.zsx.android.androidlifecycledemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsx.android.androidlifecycledemo.R;

import java.util.List;

/**
 * 数据列表适配器
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> datas;
    private boolean isAll;//提示已经加载完所有数据
    private final int Type_NO_DATA = 10010;//无数据
    private final int Type_DATA = 10086;//数据
    private final int Type_LIST_BOTTOM = 10000;//底部提示布局

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void setDatas(List<String> datas) {
        if (datas.size() > 0) {
            int oldSize = this.datas.size();
            this.datas.addAll(datas);
            //刷新数据界面
            notifyItemChanged(oldSize, this.datas.size());
        } else {
            //返回的数据为0则表示已经没有数据了
            isAll = true;
            notifyItemChanged(this.datas.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.size() == 0) {
            //无数据
            return Type_NO_DATA;
        } else if (datas.size() > 0 && position == datas.size()) {
            //底部提示加载
            return Type_LIST_BOTTOM;
        } else {
            //数据
            return Type_DATA;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定视图
        View view;
        if (viewType == Type_DATA) {
            //数据展示
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
            return new MyHolde(view);
        } else if (viewType == Type_LIST_BOTTOM) {
            //列表底部提示 加载中
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_view_list, parent, false);
            return new ListBottomViewHolder(view);
        } else {
            //无数据
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_data_list, parent, false);
            return new ListNoDataViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置数据
        if (holder instanceof MyHolde) {
            ((MyHolde) holder).setData(datas.get(position));
        } else if (holder instanceof ListBottomViewHolder) {
            //加载中
            if (isAll) {
                //已加载完所有数据
                ((ListBottomViewHolder) holder).setMessage("亲~已经被你看的精光了~");
            }
        } else {
            //无数据
        }

    }

    @Override
    public int getItemCount() {
        if (datas.size() > 0) {
            //+1是添加列表底部提示布局的
            return datas.size() + 1;
        } else {
            //没有数据，+1是展示无数据布局
            return 1;
        }
    }

    class MyHolde extends RecyclerView.ViewHolder {
        private TextView item;

        public MyHolde(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_text);
        }

        /**
         * 设置数据
         *
         * @param data
         */
        public void setData(String data) {
            item.setText(data);
        }
    }

}
