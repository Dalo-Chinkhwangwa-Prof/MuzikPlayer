package com.bigbang.muzikal.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import static com.bigbang.muzikal.util.DebugLogger.logDebug;

public class CounterIntentService extends IntentService {

    public CounterIntentService() {
        super("CounterService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        for (int counter =1; counter <= 10; counter++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logDebug(Thread.currentThread().getName()+ " Counter : "+counter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDebug("OnDestroyCalled");
    }
}
