package com.davyberra.musicmachine;

import android.os.Looper;
import android.util.Log;

import java.util.logging.Handler;

public class DownloadThread extends Thread {
    private static final String TAG = DownloadThread.class.getSimpleName();
    public DownloadHandler handler;

    @Override
    public void run() {
        Looper.prepare();
        handler = new DownloadHandler();
        Looper.loop();
    }

}
