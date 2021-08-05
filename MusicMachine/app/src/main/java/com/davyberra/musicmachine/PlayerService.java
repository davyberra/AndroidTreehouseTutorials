package com.davyberra.musicmachine;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

public class PlayerService extends Service {
    private static final String TAG = PlayerService.class.getSimpleName();
    private MediaPlayer player;
    public Messenger messenger = new Messenger(new PlayerHandler(this));

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        player = MediaPlayer.create(this, R.raw.jingle);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = notificationBuilder.build();
        startForeground(11, notification);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
                stopForeground(true);
            }
        });
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        player.release();
        super.onDestroy();
    }

    // Client Methods
    public void play() {
        player.start();
    }

    public void pause() {
        player.pause();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }
}
