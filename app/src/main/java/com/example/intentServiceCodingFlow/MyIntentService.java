package com.example.intentServiceCodingFlow;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    private PowerManager.WakeLock wakeLock;

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        assert powerManager != null;
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"EXAMPLE:WAKELOCK");
        wakeLock.acquire(60000);

        Log.d(TAG, "WakeLock Acquired");

        Log.d(TAG, "onCreate");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Notification notification = new Notification.Builder(this, App.CHANNEL_ID)
                    .setContentTitle("Example IntentService")
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentText("Running...").build();

            startForeground(1,notification);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");

        String input = intent.getStringExtra("input");
        for (int i = 0; i < 10 ; i++) {
            Log.d(TAG, input + " - " + i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        wakeLock.release();
        Log.d(TAG, "WakeLock Released");
    }
}
