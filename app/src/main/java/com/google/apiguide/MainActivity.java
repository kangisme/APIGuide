package com.google.apiguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.apiguide.appcomponents.AppComponentsActivity;
import com.google.apiguide.appresources.AppResourcesActivity;
import com.google.apiguide.introduction.PermissionActivity;
import com.google.apiguide.ipc.IpcActivity;
import com.google.apiguide.userinterface.UserInterfaceActivity;

/**
 * Created by kangren on 2017/12/13.
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, PermissionActivity.class);
            }
        });
        findViewById(R.id.user_interface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, UserInterfaceActivity.class);
            }
        });
        findViewById(R.id.app_components).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, AppComponentsActivity.class);
            }
        });
        findViewById(R.id.app_resources).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, AppResourcesActivity.class);
            }
        });
        findViewById(R.id.ipc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpTo(mContext, IpcActivity.class);
            }
        });
    }
}
