package com.google.apiguide.ipc.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by kangren on 2018/1/17.
 */

public class TCPService extends Service {

    private boolean isServiceDestroyed = false;

    private String[] randomMessage = new String[]{
            "你好啊",
            "hello world",
            "second step",
            "third sentence"
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        new Thread(new TCPSever()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        isServiceDestroyed = true;
        super.onDestroy();
    }

    private class TCPSever implements Runnable
    {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Logger.e("establish tcp server failed,port:8688");
                e.printStackTrace();
                return;
            }

            while (!isServiceDestroyed)
            {
                try {
                    final Socket client = serverSocket.accept();
                    Logger.d("Accept");
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        //用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室！");
        while (!isServiceDestroyed)
        {
            String str = in.readLine();
            Logger.d("msg from client:" + str);
            if (str == null)
            {
                //客户端断开连接
                break;
            }
            int i = new Random().nextInt(randomMessage.length);
            out.println(randomMessage[i]);
            Logger.d("send:" + randomMessage[i]);
        }

        Logger.d("client quit.");
        if (in != null)
        {
            in.close();
        }
        if (out != null)
        {
            out.close();
        }
        client.close();
    }
}
