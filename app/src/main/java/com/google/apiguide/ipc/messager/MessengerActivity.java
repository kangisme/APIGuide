package com.google.apiguide.ipc.messager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;
import com.orhanobut.logger.Logger;

/**
 * Created by kangren on 2018/1/15.
 */

public class MessengerActivity extends BaseActivity {

    public static final int MSG_FROM_CLIENT = 0;

    public static final int MSG_FROM_SERVICE = 1;

    private Messenger messenger;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = Message.obtain(null, MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "This is a message from client.");
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                Logger.d("RemoteException");
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(mContext, MessengerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
