package com.zsx.android.androidlifecycledemo.live_data;

import android.arch.lifecycle.LiveData;

/**
 * 自定义LiveData
 * 该数据与当前绑定的视图生命周期有关
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class MyLiveData<T> extends LiveData {
    private T mData;

    /**
     * 设置数据
     * @param mData
     */
    public void setmData(T mData) {
        this.mData = mData;
        //通知视图，数据更改
        postValue(mData);
    }

    @Override
    protected void onActive() {
        super.onActive();
        //视图开始显示
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        //视图消失
    }
}
