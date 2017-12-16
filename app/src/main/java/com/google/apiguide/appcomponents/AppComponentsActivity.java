package com.google.apiguide.appcomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;
import com.google.apiguide.appcomponents.intent.IntentActivity;

/**
 * API指南-应用组件
 * Created by kangren on 2017/12/16.
 */

public class AppComponentsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_components);
        findViewById(R.id.intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(AppComponentsActivity.this, IntentActivity.class);
            }
        });
    }
}
