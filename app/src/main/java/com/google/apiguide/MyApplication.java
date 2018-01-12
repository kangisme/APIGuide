package com.google.apiguide;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by kangren on 2018/1/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Logger工具
        FormatStrategy strategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .tag("APIGuide")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        //打印当前的进程名
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process: manager.getRunningAppProcesses()) {
            if(process.pid == pid)
            {
                processName = process.processName;
            }
        }
        Logger.d("application create in " + processName);
    }
}
