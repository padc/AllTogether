package com.example.SplashExample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends Activity {
    public static final String ACTION_BROADCAST = "com.example.SplashActivity.broadcast";

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.btn_broadcast);
        Button shutdownButton = (Button) findViewById(R.id.btn_shutdown);

        mBroadcastReceiver = new SplashBroadcast();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, SplashService.class);

                intent.setAction(ACTION_BROADCAST);
                intent.putExtra(SplashBroadcast.EXTRAS_MESSAGE, "It's working!™");
                startService(intent);
            }
        });
        shutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Integer, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        // Pretend that it's cleaning up
                        try {
                            for (int i : new int[] {3, 2, 1}) {
                                publishProgress(i);
                                Thread.sleep(1000);
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        Toast.makeText(getApplicationContext(), "Closing in " + values[0], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        finish();
                    }
                }.execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.registerReceiver(mBroadcastReceiver, new IntentFilter(ACTION_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(mBroadcastReceiver);
    }
}
