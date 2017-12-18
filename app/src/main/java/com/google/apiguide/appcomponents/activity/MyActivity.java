package com.google.apiguide.appcomponents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 应用组件-Activity
 * Created by kangren on 2017/12/18.
 */

public class MyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("kang", "onNewIntent");
    }
}
