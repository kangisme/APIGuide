package com.google.apiguide.userinterface.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.google.apiguide.R;

/**
 * 用户界面-通知
 * Created by kangren on 2017/12/13.
 */

public class NotificationActivity extends AppCompatActivity {

    private NotificationManager manager;

    private Notification notification;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findViewById(R.id.send_neteasy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, NetEasyService.class);
                intent.putExtra(NetEasyService.COMMAND, NetEasyService.Command.START);
                NotificationActivity.this.startService(intent);
            }
        });

        findViewById(R.id.normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification = new Notification.Builder(NotificationActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("这是标题")
                        .setContentText("这是内容")
                        .build();
                manager.notify(1, notification);
            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}
