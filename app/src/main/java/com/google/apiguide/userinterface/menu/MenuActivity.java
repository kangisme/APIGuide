package com.google.apiguide.userinterface.menu;

import org.w3c.dom.Text;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.R;

/**
 * 用户界面-菜单-PopupMenu
 * Created by kangren on 2017/12/19.
 */

public class MenuActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener{

    private View anchorView;

    private TextView textView;

    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_menu);
        textView = findViewById(R.id.text);
        anchorView = findViewById(R.id.popup_menu_anchor);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Log.d("kang", "x:" + event.getX() + "y:" + event.getY());
            float heigh = event.getY();
            anchorView.setX(event.getX());
            anchorView.setY(heigh);
            PopupMenu menu = new PopupMenu(this, anchorView, Gravity.BOTTOM);
            menu.inflate(R.menu.menu_popup);
            menu.setOnMenuItemClickListener(this);
            menu.show();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.copy:
                Toast.makeText(mContext, "已将内容复制进剪贴板", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paste:
                ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item clipItem = clipData.getItemAt(0);
                textView.setText(clipItem.getText().toString());
                break;
            case R.id.clean:
                textView.setText("");
                break;
        }
        return false;
    }
}
