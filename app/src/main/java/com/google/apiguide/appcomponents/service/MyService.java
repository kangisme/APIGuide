package com.google.apiguide.appcomponents.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 标准的Service
 * Created by kangren on 2017/12/18.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("kang", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("kang", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("kang", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("kang", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("kang", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("kang", "onRebind");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("kang", "onConfigurationChanged");
    }

}
