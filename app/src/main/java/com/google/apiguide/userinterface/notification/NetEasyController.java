package com.google.apiguide.userinterface.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.apiguide.R;

/**
 * Created by kangren on 2017/12/14.
 */

public class NetEasyController {

    public static final String MUSIC_TITLE = "我好像在哪见过你（电影《精灵王座》主题曲）";

    public static final String MUSIC_ANTHOR = "薛之谦 - 初学者";

    private final String MUSIC_TICKER = "网易云音乐正在播放";

    private Context mContext;

    private Notification notification;

    private NotificationManager manager;

    private RemoteViews bigView;

    private RemoteViews smallView;



    public NetEasyController(Context context) {
        mContext = context;
        init();
    }

    private void init() {

        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        bigView = new RemoteViews(mContext.getPackageName(), R.layout.notification_neteasy_player);
        smallView = new RemoteViews(mContext.getPackageName(),  R.layout.notification_neteasy_player_small);

        int requestCode = (int) SystemClock.uptimeMillis();

        Intent close = new Intent(mContext, NetEasyService.class);
        close.putExtra(NetEasyService.COMMAND, NetEasyService.Command.CLOSE);
        PendingIntent pendingClose = PendingIntent.getService(mContext, 1, close, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_close, pendingClose);
        smallView.setOnClickPendingIntent(R.id.music_close, pendingClose);

        Intent like = new Intent(mContext, NetEasyService.class);
        like.putExtra(NetEasyService.COMMAND, NetEasyService.Command.LIKE);
        PendingIntent pendingLike = PendingIntent.getService(mContext, 2, like, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_like, pendingLike);

        Intent pre = new Intent(mContext, NetEasyService.class);
        pre.putExtra(NetEasyService.COMMAND, NetEasyService.Command.PRE);
        PendingIntent pendingPre = PendingIntent.getService(mContext, 3, pre, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_pre, pendingPre);

        Intent pause = new Intent(mContext, NetEasyService.class);
        pause.putExtra(NetEasyService.COMMAND, NetEasyService.Command.PAUSE);
        PendingIntent pendingPause = PendingIntent.getService(mContext, 4, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_pause, pendingPause);
        smallView.setOnClickPendingIntent(R.id.music_pause, pendingPause);

        Intent next = new Intent(mContext, NetEasyService.class);
        next.putExtra(NetEasyService.COMMAND, NetEasyService.Command.NEXT);
        PendingIntent pendingNext = PendingIntent.getService(mContext, 5, next, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_next, pendingNext);
        smallView.setOnClickPendingIntent(R.id.music_next, pendingNext);

        Intent lyric = new Intent(mContext, NetEasyService.class);
        lyric.putExtra(NetEasyService.COMMAND, NetEasyService.Command.LYRIC);
        PendingIntent pendingLyric = PendingIntent.getService(mContext, 6, lyric, PendingIntent.FLAG_UPDATE_CURRENT);
        bigView.setOnClickPendingIntent(R.id.music_lyric, pendingLyric);
        smallView.setOnClickPendingIntent(R.id.music_lyric, pendingLyric);

        //API 16以下不支持BigContentView
//        if(android.os.Build.VERSION.SDK_INT >= 16) {
//            notification = builder.build();
//            notification.bigContentView = bigView;
//        }


        //初始化
        setLikeState(false);
        setLyricState(false);

        RemoteViews tickerView = new RemoteViews(mContext.getPackageName(), R.mipmap.neteasy_player_small_icon);

        notification = new NotificationCompat.Builder(mContext)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.neteasy_player_small_icon)
                .setTicker(MUSIC_TICKER, tickerView)
                .setCustomContentView(smallView)
                .setCustomBigContentView(bigView)
                .build();
    }

    public void startPlayer(String title, String author)
    {
        if (title == null && author == null)
        {
            return;
        }
        //设置音乐名和歌手
        bigView.setTextViewText(R.id.music_title, title);
        bigView.setTextViewText(R.id.music_author, author);
        smallView.setTextViewText(R.id.music_title, title);
        smallView.setTextViewText(R.id.music_author, author);
        if (manager != null && notification != null)
        {
            manager.notify(R.string.app_name, notification);
        }
    }

    public void cancelPlayer()
    {
        manager.cancel(R.string.app_name);
    }

    /**
     * 设置喜爱状态
     * @param isLike 是否喜爱
     */
    public void setLikeState(boolean isLike)
    {
        if (isLike)
        {
            bigView.setImageViewResource(R.id.music_like, R.mipmap.notification_like_red);
        }
        else
        {
            bigView.setImageViewResource(R.id.music_like, R.mipmap.notification_like_gray);
        }
    }

    /**
     * 设置歌词显示状态
     * @param isLyric 是否显示歌词
     */
    public void setLyricState(boolean isLyric)
    {
        if (isLyric)
        {
            bigView.setImageViewResource(R.id.music_lyric, R.mipmap.notification_lyric_red);
        }
        else
        {
            bigView.setImageViewResource(R.id.music_lyric, R.mipmap.notification_lyric_gray);
        }
    }

    /**
     * 设置歌曲信息
     * @param title 歌曲名
     * @param author 歌手名
     */
    public void setMusicInfo(String title, String author)
    {
        bigView.setTextViewText(R.id.music_title, title);
        bigView.setTextViewText(R.id.music_author, author);
        smallView.setTextViewText(R.id.music_title, title);
        smallView.setTextViewText(R.id.music_author, author);
    }
}
