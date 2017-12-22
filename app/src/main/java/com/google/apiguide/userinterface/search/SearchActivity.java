package com.google.apiguide.userinterface.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 用户界面-搜索
 * Created by kangren on 2017/12/20.
 */

public class SearchActivity extends BaseActivity {

    private SearchManager searchManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchManager.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                Log.d("kang", "onCancel");
            }
        });
        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("kang", "onDismiss");
            }
        });
    }

    public void searchDialog(View v)
    {
        onSearchRequested();
    }

    @Override
    public boolean onSearchRequested() {
        //pause some stuff
        //...
        Bundle appData = new Bundle();
        appData.putString(SearchableActivity.APP_DATA, "传递的额外信息");
        startSearch(null, false, appData, false);
        Log.d("kang", "onShow");
        return true;
//        return super.onSearchRequested();
    }


}
