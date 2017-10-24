package com.zsx.android.androidlifecycledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建者： feifan.pi 在 2017/10/24.
 */

public class TwoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        MyFragment myFragment = new MyFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, myFragment, "myFragment")
                .commitAllowingStateLoss();

    }
}
