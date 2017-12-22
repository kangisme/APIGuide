package com.google.apiguide.userinterface.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 用户界面-搜索-搜索对话框
 * Created by kangren on 2017/12/20.
 */

public class SearchableActivity extends BaseActivity {

    public static final String APP_DATA = "app_data";

    private TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        findViewById(R.id.search_dialog_same).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchRequested();
            }
        });

        result = findViewById(R.id.result);
        handleIntent();
    }

    /**
     * 处理Intent
     */
    private void handleIntent() {
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

        //处理传递的额外信息
        Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
        if (bundle != null)
        {
            String deliver = bundle.getString(APP_DATA, "default");
            Toast.makeText(mContext, deliver, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 实际搜索操作
     * @param query
     */
    private void doMySearch(String query)
    {
        result.setText("搜索框输入的是:" + query);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }
}
