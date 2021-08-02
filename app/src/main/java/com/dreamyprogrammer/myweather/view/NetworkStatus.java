package com.dreamyprogrammer.myweather.view;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.dreamyprogrammer.myweather.R;


public class NetworkStatus extends BroadcastReceiver {

    private int messageId = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {
        // создать нотификацию

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.drawable.ic_wifioff)
                .setContentTitle(context.getString(R.string.broadcast_receiver))
                .setContentText("Нет сети");
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}

