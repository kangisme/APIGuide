package com.google.apiguide.userinterface.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * 网易云音乐Notification控制服务
 * Created by kangren on 2017/12/15.
 */

public class NetEasyService extends Service {

    public static final String COMMAND = "command";

    public enum  Command{
        NULL,START,CLOSE,LIKE,PRE,PAUSE,NEXT,LYRIC,ENTER
    }

    private NetEasyController controller;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        controller = new NetEasyController(this);
        controller.startPlayer(NetEasyController.MUSIC_TITLE, NetEasyController.MUSIC_ANTHOR);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Command command = (Command) intent.getSerializableExtra(COMMAND);
        if (command == null)
        {
            Log.d("kang", "NetEasyService has not run notification");
            return super.onStartCommand(intent, flags, startId);
        }
        switch (command)
        {
            case NULL:
                break;
            case START:
                controller.startPlayer(NetEasyController.MUSIC_TITLE, NetEasyController.MUSIC_ANTHOR);
                break;
            case CLOSE:
                controller.cancelPlayer();
                //关闭服务
                stopSelf();
                break;
            case LIKE:
                Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
                break;
            case PRE:
                Toast.makeText(this, "pre", Toast.LENGTH_SHORT).show();
                break;
            case PAUSE:
                Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
                break;
            case NEXT:
                Toast.makeText(this, "next", Toast.LENGTH_SHORT).show();
                break;
            case LYRIC:
                Toast.makeText(this, "lyric", Toast.LENGTH_SHORT).show();
                break;
            case ENTER:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
