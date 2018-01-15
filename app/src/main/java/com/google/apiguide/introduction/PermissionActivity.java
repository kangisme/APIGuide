package com.google.apiguide.introduction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 简介-设备兼容性
 * Created by kangren on 2017/12/15.
 */

public class PermissionActivity extends BaseActivity {

    private boolean firstEnter;

    private final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstEnter)
        {
            if (lakePermission(PERMISSIONS))
            {

            }
        }
    }

    /**
     * 检查是否缺少权限
     * @param permissions
     * @return
     */
    private boolean lakePermission(String... permissions)
    {
        for (String permission : permissions)
        if (ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        return false;
    }
}

