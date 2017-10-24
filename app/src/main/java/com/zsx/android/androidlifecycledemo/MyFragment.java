package com.zsx.android.androidlifecycledemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsx.android.androidlifecycledemo.live_data.MyLiveData;

/**
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class MyFragment extends Fragment implements LifecycleOwner {
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView=view.findViewById(R.id.fragment_text);
        MyLiveData myLiveData=new MyLiveData();
        myLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String o) {
                mTextView.setText(o);
            }
        });
        myLiveData.setmData("哈哈哈哈");
    }

}
