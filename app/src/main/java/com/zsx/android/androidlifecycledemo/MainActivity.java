package com.zsx.android.androidlifecycledemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zsx.android.androidlifecycledemo.adapter.MyAdapter;
import com.zsx.android.androidlifecycledemo.live_data.MyLiveData;
import com.zsx.android.androidlifecycledemo.view_model.MyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleOwner,View.OnClickListener {
    private Button myButton;
    private RecyclerView mRecyclerView;
    //数据模型
    private MyViewModel myViewModel;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.lsitView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new MyViewModel();
    }

    /**
     * 获取数据
     */
    private void initData() {
        MyLiveData<String> myLiveData=new MyLiveData<>();
        myLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                myButton.setText(s);
            }
        });
        myLiveData.setmData("自定义LiveData");
        //第一次，绑定数据源
        myViewModel.getmMutableLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                //UI线程
                if (null == myAdapter) {
                    //第一次获取
                    myAdapter = new MyAdapter(strings);
                    mRecyclerView.setAdapter(myAdapter);
                }else{
                    //加载更多
                    if (strings.size()==0){
                        Toast.makeText(mRecyclerView.getContext(),"已加载完所有数据",Toast.LENGTH_SHORT).show();
                    }
                    myAdapter.setDatas(strings);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        //加载更多
        myViewModel.getmMutableLiveData();
    }
}
