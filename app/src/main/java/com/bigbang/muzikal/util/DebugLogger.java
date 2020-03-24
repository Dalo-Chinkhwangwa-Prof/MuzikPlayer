package com.bigbang.muzikal.util;

import android.util.Log;

import static com.bigbang.muzikal.util.Constants.*;

public class DebugLogger {
    public static void logDebug(String message) {
        Log.d(LOG_TAG, message);
    }
    public static void logError(Throwable throwable) {
        Log.d(LOG_TAG, ERROR_PREFIX + throwable.getLocalizedMessage());
    }
}
