package com.example.nguynqucvit.learnmoregamever2.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by viet on 28/07/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        Log.e(TAG,"Receiver");
    }
}
