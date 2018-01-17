package com.google.apiguide.ipc.provider;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.apiguide.BaseActivity;

/**
 * Created by kangren on 2018/1/17.
 */

public class ProviderActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = Uri.parse("content://com.google.apiguide.ipc.provider.BookProvider");
        getContentResolver().query(uri, null, null, null, null);
    }
}
