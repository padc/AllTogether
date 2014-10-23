package com.example.SplashExample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author avendael
 */
public class SplashBroadcast extends BroadcastReceiver {
    public static final String EXTRAS_MESSAGE = "com.example.SplashExample.message";

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra(EXTRAS_MESSAGE), Toast.LENGTH_LONG).show();
    }
}
