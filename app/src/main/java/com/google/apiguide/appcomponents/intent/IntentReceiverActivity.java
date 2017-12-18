package com.google.apiguide.appcomponents.intent;

import org.w3c.dom.Text;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 应用组件-Intent-隐式Intent接收
 * Android 会自动将 CATEGORY_DEFAULT 类别应用于传递给 startActivity() 和 startActivityForResult()
 * 的所有隐式 Intent。因此，如需 Activity 接收隐式 Intent，则必须将 "android.intent.category.DEFAULT"
 * 的类别包括在其 Intent 过滤器中
 * Created by kangren on 2017/12/18.
 */

public class IntentReceiverActivity extends BaseActivity {
    private TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_receiver);
        result = findViewById(R.id.result);
        Intent intent = getIntent();
        if ("android.intent.action.SEND".equals(intent.getAction()))
        {
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            result.setText(text);
        }
        if ("android.intent.action.EDIT".equals(intent.getAction()))
        {
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            result.setText(intent.getAction() + text);
        }
    }
}
