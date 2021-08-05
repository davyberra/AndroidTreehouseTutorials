package com.davyberra.musicmachine;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.logging.LogRecord;

public class DownloadHandler extends Handler {
    private static final String TAG = DownloadHandler.class.getSimpleName();
    private DownloadService service;

    @Override
    public void handleMessage(@NonNull Message msg) {
        downloadSong(msg.obj.toString());
        service.stopSelf(msg.arg1);

    }

    private void downloadSong(String song) {
        long endTime = System.currentTimeMillis() + 10*1000;
        while (endTime > System.currentTimeMillis()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, song + "downloaded");
        }
    }

    public void setService(DownloadService service) {
        this.service = service;
    }
}
