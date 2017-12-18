package com.google.apiguide.appcomponents.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 应用组件-Service
 * Created by kangren on 2017/12/18.
 */

public class ServiceActivity extends BaseActivity {
    public static final String UPLOAD_RESULT = "com.apiguide.service.RESULT";

    private LinearLayout container;

    private int i = 0;


    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (UPLOAD_RESULT.equals(intent.getAction()))
            {
                String path = intent.getStringExtra(UploadIntentService.EXTRA_IMG_PATH);
                TextView tv = (TextView) container.findViewWithTag(path);
                tv.setText(path + " upload success ~~~ ");
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        container = findViewById(R.id.container);
        findViewById(R.id.standard_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startService(new Intent(mContext, MyService.class));
            }
        });
        registerReceiver();
    }

    public void addTask(View view)
    {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        UploadIntentService.startUploadImg(this, path);

        TextView tv = new TextView(this);
        container.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }

    private void registerReceiver()
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }
}
