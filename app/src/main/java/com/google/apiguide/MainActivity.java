package com.google.apiguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.apiguide.appcomponents.AppComponentsActivity;
import com.google.apiguide.introduction.DeviceCompatibilityActivity;
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
                JumpUtil.jumpTo(mContext, DeviceCompatibilityActivity.class);
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
    }
}
