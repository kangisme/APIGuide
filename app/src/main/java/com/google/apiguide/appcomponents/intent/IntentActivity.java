package com.google.apiguide.appcomponents.intent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.apiguide.BaseActivity;
import com.google.apiguide.JumpUtil;
import com.google.apiguide.R;

/**
 * 应用组件-Intent
 * Created by kangren on 2017/12/16.
 */

public class IntentActivity extends BaseActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    static final int REQUEST_IMAGE_GET = 2;

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        mImageView = findViewById(R.id.image_display);
        sendImplicitIntent();

        findViewById(R.id.set_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlarm("早起", 8, 15);
            }
        });

        findViewById(R.id.set_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer("计时", 60);
            }
        });

        findViewById(R.id.image_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        //以静态图像模式启动相机 = 正常启动相机
        findViewById(R.id.still_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });

        findViewById(R.id.get_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }



    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            mImageView.setVisibility(View.VISIBLE);
        }
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            mImageView.setImageBitmap(getBitmapFromUri(fullPhotoUri));
            mImageView.setVisibility(View.VISIBLE);
            // Do work with photo saved at fullPhotoUri
        }
    }

    //原图，可能会产生OOM
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置计时器
     * @param message
     * @param seconds
     */
    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * 创建闹铃
     * @param message
     * @param hour
     * @param minutes
     */
    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * 发送隐式Intent
     */
    private void sendImplicitIntent() {
        findViewById(R.id.implicit_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textMessage = "这是一个隐式Intent测试";

                // Create the text message with a string
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setAction(Intent.ACTION_EDIT);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
                sendIntent.setType("text/plain");

                // Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
            }
        });

        findViewById(R.id.force_choose_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textMessage = "这是一个强制选择应用的隐式Intent测试";
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
                sendIntent.setType("text/plain");

                // Always use string resources for UI text.
                // This says something like "Share this photo with"
                String title = "选择你要分享的应用";
                // Create intent to show the chooser dialog
                Intent chooser = Intent.createChooser(sendIntent, title);

                // Verify the original intent will resolve to at least one activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
        findViewById(R.id.intent_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpTo(mContext, IntentReceiverActivity.class);
            }
        });
    }
}
