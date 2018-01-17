package com.google.apiguide.ipc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;
import com.google.apiguide.ipc.aidl.AIDLActivity;
import com.google.apiguide.ipc.messager.MessengerActivity;
import com.google.apiguide.ipc.provider.ProviderActivity;
import com.google.apiguide.ipc.socket.TCPActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 应用组件-IPC机制
 * Created by kangren on 2018/1/10.
 */

public class IpcActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.first_activity)
    void first()
    {
        JumpUtil.jumpTo(mContext, FirstActivity.class);
    }
    @OnClick(R.id.second_activity)
    void second()
    {
        JumpUtil.jumpTo(mContext, SecondActivity.class);
    }
    @OnClick(R.id.aidl)
    void aidl()
    {
        JumpUtil.jumpTo(mContext, AIDLActivity.class);
    }
    @OnClick(R.id.messenger)
    void messenger()
    {
        JumpUtil.jumpTo(mContext, MessengerActivity.class);
    }
    @OnClick(R.id.provider)
    void provider()
    {
        JumpUtil.jumpTo(mContext, ProviderActivity.class);
    }
    @OnClick(R.id.socket)
    void socket()
    {
        JumpUtil.jumpTo(mContext, TCPActivity.class);
    }
}
