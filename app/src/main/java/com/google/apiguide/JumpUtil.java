package com.google.apiguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * 跳转工具类
 * Created by kangren on 2017/12/13.
 */

public class JumpUtil {

    /**
     * Activity跳转
     * @param context 上下文
     * @param clazz 跳转Activity
     */
    public static void jumpTo(Context context, Class clazz)
    {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
