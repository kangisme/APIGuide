package com.google.apiguide.appcomponents.ipc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;

/**
 * 应用组件-IPC机制
 * Created by kangren on 2018/1/10.
 */

public class IpcActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        findViewById(R.id.first_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpTo(mContext, FirstActivity.class);
            }
        });
        findViewById(R.id.second_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpTo(mContext, SecondActivity.class);
            }
        });
    }
}
