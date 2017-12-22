package com.google.apiguide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kangren on 2017/12/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    //调试生命周期tag
    private final String LIFE_CIRCLE = "life circle:";

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, LIFE_CIRCLE + "onCreate");
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, LIFE_CIRCLE + "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, LIFE_CIRCLE + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, LIFE_CIRCLE + "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, LIFE_CIRCLE + "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, LIFE_CIRCLE + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, LIFE_CIRCLE + "onDestroy");
    }
}
