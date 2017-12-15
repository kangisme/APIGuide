package com.google.apiguide.introduction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.apiguide.R;

/**
 * 简介-设备兼容性
 * Created by kangren on 2017/12/15.
 */

public class DeviceCompatibilityActivity extends AppCompatActivity {
    private final String TAG = "Device Compatibility";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_compatibility);
        PackageManager pm = getPackageManager();
        //动态检查设备是否具有某功能
        if (!pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
            // This device does not have a compass, turn off the compass feature
            Log.d(TAG, "This device does not have a compass, turn off the compass feature");
        }
        else
        {
            Log.d(TAG, "This device have a compass, turn on the compass feature");
        }

        //动态判断系统API版本
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            Log.d(TAG, "The device system version lower than Android 8.0");
        }

        // 检查权限
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            Log.d(TAG, "has permission");
        }
        else
        {
            Log.d(TAG, "has not permission");
        }

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}

