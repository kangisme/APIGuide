package com.google.apiguide.appcomponents.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 模拟上传Service
 * Created by kangren on 2017/12/18.
 */

public class UploadIntentService extends IntentService {

    private static final String ACTION_UPLOAD_IMG = "com.apiguide.service";

    public static final String EXTRA_IMG_PATH = "com.apiguide.service.extra.img";

    public static void startUploadImg(Context context, String path)
    {
        Intent intent = new Intent(context, UploadIntentService.class);
        intent.setAction(ACTION_UPLOAD_IMG);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
    }


    public UploadIntentService() {
        super("UploadIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_UPLOAD_IMG.equals(action))
            {
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }

    private void handleUploadImg(String path)
    {
        try
        {
            //模拟上传耗时
            Thread.sleep(3000);

            Intent intent = new Intent(ServiceActivity.UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            sendBroadcast(intent);

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("kang", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("kang", "onCreate");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("kang", "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("kang", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("kang", "onBind");
        return super.onBind(intent);
    }
}
