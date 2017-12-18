package com.google.apiguide.appcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;
import com.google.apiguide.appcomponents.activity.MyActivity;
import com.google.apiguide.appcomponents.intent.IntentActivity;
import com.google.apiguide.appcomponents.service.ServiceActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_DOCUMENT;

/**
 * API指南-应用组件
 * Created by kangren on 2017/12/16.
 */

public class AppComponentsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_components);
        findViewById(R.id.intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, IntentActivity.class);
            }
        });
        findViewById(R.id.activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MyActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.addFlags(FLAG_ACTIVITY_NEW_DOCUMENT);
                mContext.startActivity(intent);
            }
        });
        findViewById(R.id.service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, ServiceActivity.class);
            }
        });
    }
}
