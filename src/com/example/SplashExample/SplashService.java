package com.example.SplashExample;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class SplashService extends IntentService {
    private static final String TAG = SplashService.class.getSimpleName();

    public SplashService() {
        super(TAG);
    }

    public SplashService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent broadcastIntent = new Intent(SplashActivity.ACTION_BROADCAST);

        broadcastIntent.putExtra(SplashBroadcast.EXTRAS_MESSAGE, intent.getStringExtra(SplashBroadcast.EXTRAS_MESSAGE));
        sendBroadcast(broadcastIntent);
    }
}
