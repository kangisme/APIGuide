package com.google.apiguide.userinterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;
import com.google.apiguide.userinterface.menu.MenuActivity;
import com.google.apiguide.userinterface.notification.NotificationActivity;

/**
 * API指南-用户界面
 * Created by kangren on 2017/12/13.
 */

public class UserInterfaceActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinterface);
        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, NotificationActivity.class);
            }
        });
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpTo(mContext, MenuActivity.class);
            }
        });
        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(mContext);
                toast.setDuration(Toast.LENGTH_SHORT);
                View view = View.inflate(mContext, R.layout.custom_toast, null);
                TextView title = view.findViewById(R.id.toast_title);
                title.setText("This is Toast title");
                TextView subTitle = view.findViewById(R.id.toast_subtitle);
                subTitle.setText("This is Toast subtitle");
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
