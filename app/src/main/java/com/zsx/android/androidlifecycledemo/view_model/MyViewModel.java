package com.zsx.android.androidlifecycledemo.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel的最基本的用法，
 * 它负责从各个地方获取数据，
 * 然后把数据装到LiveData中，提供给UI
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<String>> mMutableLiveData;

    /**
     * 获取数据
     * @return
     */
    public MutableLiveData<List<String>> getmMutableLiveData() {
        if (null == mMutableLiveData) {
            mMutableLiveData = new MutableLiveData<>();
        }
//        mMutableLiveData.postValue(new ArrayList<String>());//模拟无数据
        loadData();
        return mMutableLiveData;
    }

    //模拟已加载完所有数据
    boolean noMore;

    /**
     * 模拟加载数据
     */
    private void loadData() {
        if (mMutableLiveData.getValue() == null) {
            //第一次获取数据
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                stringList.add("数据" + i);
            }
            mMutableLiveData.postValue(stringList);
        } else if (mMutableLiveData.getValue().size() < 10 && !noMore) {
            noMore = true;
            //获取更多
            List<String> stringList2 = new ArrayList<>();
            for (int i = 5; i < 10; i++) {
                stringList2.add("数据" + i);
            }
            mMutableLiveData.postValue(stringList2);//交给外部处理数据整合
//            mMutableLiveData.getValue().addAll(stringList2);//自己整合数据
//            mMutableLiveData.postValue(mMutableLiveData.getValue());
        } else {
            //模拟数据全部拉取完了
            mMutableLiveData.postValue(new ArrayList<String>());
        }

    }
}
