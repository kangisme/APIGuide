package com.google.apiguide.appresources;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 应用资源
 * Created by kangren on 2017/12/19.
 */

public class AppResourcesActivity extends BaseActivity {

    private ImageView levelImg;

    private int level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_resources);
        Log.d("kang", "onCreate");

        levelImg = findViewById(R.id.level_list);
        levelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = (level + 1) % 5;
                levelImg.setImageLevel(level);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
