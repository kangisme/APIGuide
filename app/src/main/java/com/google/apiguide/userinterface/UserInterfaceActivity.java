package com.google.apiguide.userinterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;
import com.google.apiguide.userinterface.notification.NotificationActivity;

/**
 * API指南-用户界面
 * Created by kangren on 2017/12/13.
 */

public class UserInterfaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinterface);
        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(UserInterfaceActivity.this, NotificationActivity.class);
            }
        });
    }
}
