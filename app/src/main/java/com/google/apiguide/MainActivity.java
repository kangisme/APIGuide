package com.google.apiguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.apiguide.userinterface.JumpUtil;
import com.google.apiguide.userinterface.UserInterfaceActivity;

/**
 * Created by kangren on 2017/12/13.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.user_interface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(MainActivity.this, UserInterfaceActivity.class);
            }
        });
    }
}
