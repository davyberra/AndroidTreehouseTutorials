package com.davyberra.musicmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_SONG = "song";
    private boolean bound = false;
    private Button downloadButton;
    private Button playButton;
    private Messenger serviceMessenger;
    private Messenger activityMessenger = new Messenger(new ActivityHandler(this));
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            bound = true;
            serviceMessenger = new Messenger(binder);
            Message message = Message.obtain();
            message.arg1 = 2;
            message.arg2 = 1;
            message.replyTo = activityMessenger;
            try {
                serviceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadButton = findViewById(R.id.downloadButton);
        playButton = findViewById(R.id.playButton);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT).show();

                // Send messages to handler for processing
                for (String song : Playlist.songs) {
                    Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bound) {
                    Intent intent = new Intent(MainActivity.this, PlayerService.class);
                    startService(intent);
                    Message message = Message.obtain();
                    message.arg1 = 2;
                    message.replyTo = activityMessenger;
                    try {
                        serviceMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void changePlayButtonText(String text) {
        playButton.setText(text);
    }

    private void downloadSong() {
        long endTime = System.currentTimeMillis() + 10*1000;
        while (endTime > System.currentTimeMillis()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Song downloaded");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(serviceConnection);
            bound = false;
        }
    }
}