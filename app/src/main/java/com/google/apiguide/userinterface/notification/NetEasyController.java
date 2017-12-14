package com.google.apiguide.userinterface.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.apiguide.R;

/**
 * Created by kangren on 2017/12/14.
 */

public class NetEasyController {

    private final String MUSIC_TITLE = "我好像在哪见过你（电影《精灵王座》主题曲）";

    private final String MUSIC_ANTHOR = "薛之谦 - 初学者";

    private final String MUSIC_TICKER = "网易云音乐正在播放";

    private Context mContext;

    private Notification notification;

    private NotificationManager manager;



    public NetEasyController(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        RemoteViews bigView = new RemoteViews(mContext.getPackageName(), R.layout.notification_neteasy_player);
        //设置音乐名和歌手
        bigView.setTextViewText(R.id.music_title, MUSIC_TITLE);
        bigView.setTextViewText(R.id.music_author, MUSIC_ANTHOR);

        //API 16以下不支持BigContentView
//        if(android.os.Build.VERSION.SDK_INT >= 16) {
//            notification = builder.build();
//            notification.bigContentView = bigView;
//        }

        RemoteViews contentView = new RemoteViews(mContext.getPackageName(),  R.layout.notification_neteasy_player_small);

        RemoteViews tickerView = new RemoteViews(mContext.getPackageName(), R.mipmap.neteasy_player_small_icon);

        notification = new NotificationCompat.Builder(mContext)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.neteasy_player_small_icon)
                .setTicker(MUSIC_TICKER, tickerView)
                .setCustomContentView(contentView)
                .setCustomBigContentView(bigView)
                .build();
    }
}
