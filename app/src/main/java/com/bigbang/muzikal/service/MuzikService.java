package com.bigbang.muzikal.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bigbang.muzikal.R;

import static com.bigbang.muzikal.util.DebugLogger.logDebug;

public class MuzikService extends Service {

    private MediaPlayer muzikPlayer;

    private boolean stopped = false;

    @Override
    public void onCreate() {
        super.onCreate();

        muzikPlayer = MediaPlayer.create(getApplicationContext(), R.raw.jazz_in_paris);
        muzikPlayer.setLooping(true);

        logDebug("Service onCreate: Instantiate muzikPlayer");
    }

    public void pauseMuzik() {
        if (muzikPlayer.isPlaying())
            muzikPlayer.pause();
    }

    public void stopMuzik() {
        if (!stopped) {
            stopped = true;
            muzikPlayer.stop();
        }
    }

    public void playMuzik() {
        if (!muzikPlayer.isPlaying()) {
            if (stopped) {
                muzikPlayer = MediaPlayer.create(this, R.raw.jazz_in_paris);
                stopped = !stopped;
            }
            muzikPlayer.start();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //Not called in bound service
        logDebug("Service onStartCommand: start muzikPlayer");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        logDebug("Service onDestroy");
        muzikPlayer.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MuzikServiceBinder(); //passed into the onServiceConnected method as - IBinder service

    }

    public class MuzikServiceBinder extends Binder {
        public MuzikService getMuzikService() {
            return MuzikService.this;
        }
    }
}
