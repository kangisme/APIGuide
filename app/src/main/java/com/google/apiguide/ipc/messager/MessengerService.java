package com.google.apiguide.ipc.messager;

import android.app.Service;
import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by kangren on 2018/1/15.
 */

public class MessengerService extends Service {

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case MessengerActivity.MSG_FROM_CLIENT:
                    String s = msg.getData().getString("msg");
                    Toast.makeText(MessengerService.this, s, Toast.LENGTH_SHORT).show();
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MessengerActivity.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "消息已收到，稍后回复");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return true;
        }
    });

    private final Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
